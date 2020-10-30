package com.chentong.erp.controller;

import com.chentong.erp.service.PermissionService;
import com.chentong.erp.vo.resp.DataResult;
import com.chentong.erp.vo.resp.PermissionRespNodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public DataResult treeSelect(){
        DataResult dataResult = DataResult.success();
        List<PermissionRespNodeVO> permissionRespNodeVOS = permissionService.permissionWithButtonList();
        dataResult.setData(permissionRespNodeVOS);
        return dataResult;
    }
}
