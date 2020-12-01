package com.chentong.erp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chentong.erp.entity.SysPermission;
import com.chentong.erp.vo.req.PermissionQueryVO;
import com.chentong.erp.vo.resp.PermissionRespNodeVO;

import java.util.List;

public interface PermissionService {
    // 树形结构带buttion
    List<PermissionRespNodeVO> permissionWithButtonList(PermissionQueryVO permissionQueryVO);
    List<PermissionRespNodeVO> permissionTreeList(String userId);
    List<SysPermission> getPermissions(String userId);
    List<String> getPermissionByUserId(String userId);

    List<SysPermission> selectList(PermissionQueryVO permissionQueryVO);

    SysPermission getPermission(String id);

    void insertPermission(SysPermission sysPermission);

    void updatePermission(SysPermission sysPermission);

    void deletePermission(String[] ids);
}
