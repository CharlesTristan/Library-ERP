package com.chentong.erp.service;

import com.chentong.erp.entity.SysPermission;
import com.chentong.erp.vo.resp.PermissionRespNodeVO;

import java.util.List;

public interface PermissionService {
    List<PermissionRespNodeVO> permissionTreeList(String userId);
    List<SysPermission> getPermissions(String userId);
    List<String> getPermissionByUserId(String userId);
}
