package com.chentong.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chentong.erp.dao.SysPermissionDao;
import com.chentong.erp.dao.SysRoleDao;
import com.chentong.erp.dao.SysRolePermissionDao;
import com.chentong.erp.entity.SysPermission;
import com.chentong.erp.entity.SysRole;
import com.chentong.erp.entity.SysRolePermission;
import com.chentong.erp.entity.SysUser;
import com.chentong.erp.service.RoleService;
import com.chentong.erp.vo.req.RoleQueryVO;
import io.jsonwebtoken.lang.Collections;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/12 15:15
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;
    @Autowired
    private SysPermissionDao sysPermissionDao;

    @Override
    public List<String> getRoleByUserId(String id) {
        List<String> roleByUserId = sysRoleDao.getRoleByUserId(id);
        return roleByUserId;
    }

    @Override
    public Page<SysRole> roleList(RoleQueryVO roleQueryVO) {
        if(roleQueryVO.getPageNum()==null && roleQueryVO.getPageSize()==null){
            roleQueryVO.setPageNum(1);
            roleQueryVO.setPageSize(20);
        }
        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.like(StringUtils.isNotBlank(roleQueryVO.getName()),"name",roleQueryVO.getName())
                .eq(StringUtils.isNotBlank(roleQueryVO.getStatus()),"status",roleQueryVO.getStatus())
                .ge(roleQueryVO.getBeginTime()!=null,"create_time",roleQueryVO.getBeginTime())
                .le(roleQueryVO.getEndTime()!=null,"create_time",roleQueryVO.getEndTime());
        Page<SysRole> page = new Page<>(roleQueryVO.getPageNum(), roleQueryVO.getPageSize());
        Page<SysRole> iPage = sysRoleDao.selectPage(page, queryWrapper);
        return iPage;
    }

    @Override
    public void insertRole(SysRole sysRole) {
        Date date = new Date();
        sysRole.setCreateTime(date);
        sysRole.setDeleted(1);
        sysRole.setStatus("1");
        sysRoleDao.insert(sysRole);
        String id = sysRole.getId();
        if(sysRole.getPermissionIds().length>0){
            for (String permission : sysRole.getPermissionIds()) {
                SysRolePermission sysRolePermission = new SysRolePermission();
                sysRolePermission.setPermissionId(permission);
                sysRolePermission.setRoleId(id);
                sysRolePermission.setCreateTime(date);
                sysRolePermissionDao.insert(sysRolePermission);
            }
        }
    }

    @Override
    public void updateRole(SysRole sysRole) {
        Date date = new Date();
        sysRole.setUpdateTime(date);
        sysRoleDao.updateById(sysRole);
        String id = sysRole.getId();
        QueryWrapper deleteWrapper = new QueryWrapper();
        deleteWrapper.eq("role_id",sysRole.getId());
        sysRolePermissionDao.delete(deleteWrapper);
        if(sysRole.getPermissionIds().length>0){
            for (String permission : sysRole.getPermissionIds()) {
                SysRolePermission sysRolePermission = new SysRolePermission();
                sysRolePermission.setPermissionId(permission);
                sysRolePermission.setRoleId(id);
                sysRolePermission.setCreateTime(date);
                sysRolePermissionDao.insert(sysRolePermission);
            }
        }
    }

    @Override
    public void deleteRole(String[] ids) {
        sysRoleDao.deleteBatchIds(Collections.arrayToList(ids));
    }

    @Override
    public SysRole getRole(String id) {
        SysRole sysRole = sysRoleDao.selectById(id);
        SysPermission[] permissionByRoleId = sysPermissionDao.getPermissionByRoleId(id);
        sysRole.setSysPermissions(permissionByRoleId);
        return sysRole;
    }
}
