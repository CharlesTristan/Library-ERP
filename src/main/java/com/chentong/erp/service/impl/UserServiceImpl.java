package com.chentong.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chentong.erp.common.util.JwtTokenUtil;
import com.chentong.erp.common.util.PasswordUtils;
import com.chentong.erp.constant.Constants;
import com.chentong.erp.dao.SysPermissionDao;
import com.chentong.erp.dao.SysRoleDao;
import com.chentong.erp.dao.SysUserDao;
import com.chentong.erp.entity.SysUser;
import com.chentong.erp.exception.BusinessException;
import com.chentong.erp.exception.code.BaseResponseCode;
import com.chentong.erp.service.UserService;
import com.chentong.erp.vo.req.LoginReqVO;
import com.chentong.erp.vo.req.UserQueryVO;
import com.chentong.erp.vo.resp.LoginRespVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/9 17:44
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysPermissionDao sysPermissionDao;
    @Override
    public LoginRespVO login(LoginReqVO loginReqVO) {
        // 判断用户是否存在
        // 判断用户是否被锁定
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",loginReqVO.getUsername());
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
                   .le(userQueryVO.getBeginTime()!=null,"create_time",userQueryVO.getBeginTime())
                   .ge(userQueryVO.getEndTime()!=null,"create_time",userQueryVO.getEndTime());
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
