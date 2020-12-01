package com.chentong.erp.controller;

import com.chentong.erp.entity.SysPermission;
import com.chentong.erp.service.PermissionService;
import com.chentong.erp.vo.req.PermissionQueryVO;
import com.chentong.erp.vo.resp.DataResult;
import com.chentong.erp.vo.resp.PermissionRespNodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/28 13:40
 */
@RestController
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("treeSelect")
    public DataResult treeSelect(PermissionQueryVO permissionQueryVO){
        DataResult dataResult = DataResult.success();
        List<PermissionRespNodeVO> permissionRespNodeVOS = permissionService.permissionWithButtonList(permissionQueryVO);
        dataResult.setData(permissionRespNodeVOS);
        return dataResult;
    }
    @GetMapping("list")
    public DataResult permissionList(PermissionQueryVO permissionQueryVO){
        DataResult dataResult = DataResult.success();
        List<SysPermission> sysPermissions = permissionService.selectList(permissionQueryVO);
        dataResult.setData(sysPermissions);
        return dataResult;
    }
    @GetMapping("/{id}")
    public DataResult getPermisstion(@PathVariable("id") String id){
        DataResult dataResult = DataResult.success();
        SysPermission sysPermission = permissionService.getPermission(id);
        dataResult.setData(sysPermission);
        return dataResult;
    }
    @PostMapping("")
    public DataResult insertPermission(@RequestBody SysPermission sysPermission){
        DataResult dataResult = DataResult.success();
        permissionService.insertPermission(sysPermission);
        return dataResult;
    }
    @PutMapping("")
    public DataResult updatePermission(@RequestBody SysPermission sysPermission){
        DataResult dataResult = DataResult.success();
        permissionService.updatePermission(sysPermission);
        return dataResult;
    }
    @DeleteMapping("/{ids}")
    public DataResult deletePermission(@PathVariable("ids") String[] ids){
        DataResult dataResult = DataResult.success();
        permissionService.deletePermission(ids);
        return dataResult;
    }
}
