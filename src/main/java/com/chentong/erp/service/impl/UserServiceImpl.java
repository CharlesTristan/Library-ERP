package com.chentong.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chentong.erp.common.util.JwtTokenUtil;
import com.chentong.erp.common.util.PasswordEncoder;
import com.chentong.erp.common.util.PasswordUtils;
import com.chentong.erp.constant.Constants;
import com.chentong.erp.dao.SysPermissionDao;
import com.chentong.erp.dao.SysRoleDao;
import com.chentong.erp.dao.SysUserDao;
import com.chentong.erp.dao.SysUserRoleDao;
import com.chentong.erp.entity.SysUser;
import com.chentong.erp.entity.SysUserRole;
import com.chentong.erp.exception.BusinessException;
import com.chentong.erp.exception.code.BaseResponseCode;
import com.chentong.erp.service.UserService;
import com.chentong.erp.vo.req.LoginReqVO;
import com.chentong.erp.vo.req.UserQueryVO;
import com.chentong.erp.vo.resp.LoginRespVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.*;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/9 17:44
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysPermissionDao sysPermissionDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Override
    public LoginRespVO login(LoginReqVO loginReqVO) {
        // 判断用户是否存在
        // 判断用户是否被锁定
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",loginReqVO.getUsername());
        List list = sysUserDao.selectList(queryWrapper);

        SysUser sysUser = sysUserDao.selectOne(queryWrapper);
        if(sysUser == null || sysUser.getDeleted()==0){
            throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
        }
        if(sysUser.getStatus().equals("2")) {
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        if(!PasswordUtils.matches(sysUser.getSalt(),loginReqVO.getPassword(),sysUser.getPassword())){
            throw new BusinessException(BaseResponseCode.ACCOUNT_PASSWORD_ERROR);
        }
        LoginRespVO loginRespVO = new LoginRespVO();
        loginRespVO.setId(sysUser.getId());
        loginRespVO.setUsername(sysUser.getUsername());
        loginRespVO.setPhone(sysUser.getPhone());
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.ROLES_INFOS_KEY,getRoleByUserId(sysUser.getId()));
        claims.put(Constants.PERMISSIONS_INFOS_KEY,getPermissionByUserId(sysUser.getId()));
        claims.put(Constants.JWT_USER_NAME,sysUser.getUsername());

        //得到accessToken
        String accessToken = JwtTokenUtil.getAccessToken(sysUser.getId(),claims);
        //refreshtoken
        String refreshToken = null;
        if(loginReqVO.getType().equals("1")){
            refreshToken = JwtTokenUtil.getRefreshToken(sysUser.getId(),claims);
        }else if(loginReqVO.getType().equals("2")){
            refreshToken = JwtTokenUtil.getRefreshAppToken(sysUser.getId(), claims);
        }
        loginRespVO.setAccessToken(accessToken);
        loginRespVO.setRefreshToken(refreshToken);
        return loginRespVO;
    }

    @Override
    public Page<SysUser> userInfo(UserQueryVO userQueryVO) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
       if(null != userQueryVO){
           queryWrapper.like(StringUtils.isNotBlank(userQueryVO.getUsername()),"username",userQueryVO.getUsername())
                   .like(StringUtils.isNotBlank(userQueryVO.getPhone()),"phone",userQueryVO.getPhone())
                   .ge(userQueryVO.getBeginTime()!=null,"create_time",userQueryVO.getBeginTime())
                   .le(userQueryVO.getEndTime()!=null,"create_time",userQueryVO.getEndTime());
       }
        Page<SysUser> objectPage = new Page<>(userQueryVO.getPageNum(), userQueryVO.getPageSize());
        Page<SysUser> sysUserPage = sysUserDao.selectPage(objectPage, queryWrapper);
        return sysUserPage;
    }

    @Override
    public void changeUserStatus(UserQueryVO userQueryVO) {
        UpdateWrapper<SysUser> sysUserUpdateWrapper = new UpdateWrapper<>();
        sysUserUpdateWrapper.set("status",userQueryVO.getStatus()).eq(StringUtils.isNotBlank(userQueryVO.getId()),"id",userQueryVO.getId());
        sysUserDao.update(null,sysUserUpdateWrapper);
    }

    @Override
    public void insertUser(SysUser sysUser) {
        if(StringUtils.isNotBlank(sysUser.getPassword())){
            String substring = UUID.randomUUID().toString().substring(0, 15);
            sysUser.setSalt(substring);
            String encode = PasswordUtils.encode(sysUser.getPassword(),substring );
            sysUser.setPassword(encode);
        }
        sysUser.setCreateTime(new Date());
        sysUserDao.insert(sysUser);
        String id = sysUser.getId();
        String[] roleIds = sysUser.getRoleIds();
        for (String roleId: roleIds) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(roleId);
            sysUserRole.setUserId(id);
            sysUserRole.setCreateTime(new Date());
            sysUserRoleDao.insert(sysUserRole);
        }
    }

    @Override
    public void deleteUser(String[] ids) {
        sysUserDao.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public void updateUser(SysUser sysUser) {
        if("undefined".equals(sysUser.getPassword())){
            sysUser.setPassword(null);
        }
        if(StringUtils.isNotBlank(sysUser.getPassword())){
            String substring = UUID.randomUUID().toString().substring(0, 15);
            sysUser.setSalt(substring);
            String encode = PasswordUtils.encode(sysUser.getPassword(),substring );
            sysUser.setPassword(encode);
        }
        sysUserDao.updateById(sysUser);
        String[] roleIds = sysUser.getRoleIds();
        QueryWrapper deleteWrapper = new QueryWrapper();
        deleteWrapper.eq("user_id",sysUser.getId());
        sysUserRoleDao.delete(deleteWrapper);
        for (String roleId: roleIds) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(roleId);
            sysUserRole.setUserId(sysUser.getId());
            sysUserRole.setCreateTime(new Date());
            sysUserRoleDao.insert(sysUserRole);
        }
    }

    @Override
    public SysUser getUser(String id) {
        SysUser sysUser = sysUserDao.selectById(id);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",id);
        List<SysUserRole> list = sysUserRoleDao.selectList(queryWrapper);
        List<String> ids = new ArrayList<>();
        for (SysUserRole sysUserRole: list) {
            ids.add(sysUserRole.getRoleId());
        }
        String[] rol = new String[list.size()];
        ids.toArray(rol);
        sysUser.setRoleIds(rol);
        return sysUser;
    }

    /**
     * 查询用户拥有的角色信息
     * @param userId
     * @return
     */
    private List<String> getRoleByUserId(String userId){
        List<String> roleByUserId = sysRoleDao.getRoleByUserId(userId);
        return roleByUserId;
    }

    /**
     * 查询用户的权限信息
     * @param userId
     * @return
     */
    private List<String> getPermissionByUserId(String userId){
        List<String> permissionByUserId = sysPermissionDao.getPermissionStrByUserId(userId);
        return permissionByUserId;
    }
}
