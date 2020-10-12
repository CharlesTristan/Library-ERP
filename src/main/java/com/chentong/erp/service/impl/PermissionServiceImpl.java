package com.chentong.erp.service.impl;

import com.chentong.erp.dao.SysPermissionDao;
import com.chentong.erp.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/12 15:15
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private SysPermissionDao permissionDao;
    @Override
    public List<String> getPermissionByUserId(String id) {
        List<String> permissionByUserId = permissionDao.getPermissionByUserId(id);
        return permissionByUserId;
    }
}
