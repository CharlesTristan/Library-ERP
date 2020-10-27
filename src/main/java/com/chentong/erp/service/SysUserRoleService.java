package com.chentong.erp.service;

import com.chentong.erp.entity.SysUserRole;

/**
 * (SysUserRole)表服务接口
 *
 * @author Admin
 * @since 2020-10-27 13:33:40
 */
public interface SysUserRoleService {
    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    void add(SysUserRole sysUserRole);
}