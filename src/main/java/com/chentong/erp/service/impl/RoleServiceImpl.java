package com.chentong.erp.service.impl;

import com.chentong.erp.dao.SysRoleDao;
import com.chentong.erp.service.RoleService;
import org.apache.ibatis.annotations.Param;
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
public class RoleServiceImpl implements RoleService {
    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public List<String> getRoleByUserId(String id) {
        List<String> roleByUserId = sysRoleDao.getRoleByUserId(id);
        return roleByUserId;
    }
}
