package com.chentong.erp.service.impl;

import com.chentong.erp.dao.SysUserDao;
import com.chentong.erp.dao.SysUserRoleDao;
import com.chentong.erp.entity.SysUserRole;
import com.chentong.erp.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/27 13:34
 */
@Service
@Transactional
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Override
    public void add(SysUserRole sysUserRole) {
        sysUserRoleDao.insert(sysUserRole);
    }
}
