package com.chentong.erp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chentong.erp.common.util.JwtTokenUtil;
import com.chentong.erp.constant.Constants;
import com.chentong.erp.entity.SysRole;
import com.chentong.erp.service.RoleService;
import com.chentong.erp.vo.req.RoleQueryVO;
import com.chentong.erp.vo.resp.DataResult;
import com.chentong.erp.vo.resp.HomeRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/26 15:40
 */
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("/roleList")
    public DataResult roleList(RoleQueryVO roleQueryVO){
        DataResult dataResult = DataResult.success();
        IPage iPage = roleService.roleList(roleQueryVO);
        dataResult.setData(iPage);
        return dataResult;
    }
}
