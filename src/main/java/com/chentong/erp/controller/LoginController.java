package com.chentong.erp.controller;

import com.chentong.erp.service.UserService;
import com.chentong.erp.service.impl.UserServiceImpl;
import com.chentong.erp.vo.req.LoginReqVO;
import com.chentong.erp.vo.resp.DataResult;
import com.chentong.erp.vo.resp.LoginRespVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * TODO
 *  登录
 * @author Administrator
 * @version 1.0
 * @date 2020/10/9 16:09
 */
@RestController
@RequestMapping("api")
@Slf4j
@Api(tags = "用户操作接口")
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping("login")
    public DataResult login(@RequestBody @Valid LoginReqVO loginReqVO){
        DataResult dataResult = DataResult.success();
        dataResult.setData(userService.login(loginReqVO));
        return dataResult;
    }
    @PostMapping("aaa")
    @RequiresPermissions("test:list")
    public DataResult aaa(){
        DataResult dataResult = DataResult.success();
        dataResult.setData("aaa");
        return dataResult;
    }

    @PostMapping("bbb")
    @RequiresPermissions("test:add")
    public DataResult bbb(){
        DataResult dataResult = DataResult.success();
        dataResult.setData("bbb");
        return dataResult;
    }

    @PostMapping("ccc")
    @RequiresPermissions("test:del")
    public DataResult ccc(){
        DataResult dataResult = DataResult.success();
        dataResult.setData("ccc");
        return dataResult;
    }
}
