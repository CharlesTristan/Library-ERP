package com.chentong.erp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chentong.erp.common.util.JwtTokenUtil;
import com.chentong.erp.constant.Constants;
import com.chentong.erp.entity.SysUser;
import com.chentong.erp.service.HomeService;
import com.chentong.erp.service.UserService;
import com.chentong.erp.vo.req.UserQueryVO;
import com.chentong.erp.vo.resp.DataResult;
import com.chentong.erp.vo.resp.HomeRespVO;
import com.chentong.erp.vo.resp.LoginRespVO;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/15 16:25
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private HomeService homeService;
    @Autowired
    private UserService userService;

    /**
     * 得到当前登录用户的信息，包括菜单栏信息
     * @param request
     * @return
     */
    @GetMapping("getInfo")
    public DataResult getInfo(HttpServletRequest request){
        DataResult dataResult = DataResult.success();
        String header = request.getHeader(Constants.ACCESS_TOKEN);
        String userId = JwtTokenUtil.getUserId(header);
        HomeRespVO home = homeService.getHome(userId);
        dataResult.setData(home);
        return dataResult;
    }

    /**
     * 条件查询用户列表
     * @param userQueryVO
     * @return
     */
    @GetMapping("userInfo")
    public DataResult userInfo(UserQueryVO userQueryVO){
        DataResult dataResult = DataResult.success();
        Page<SysUser> sysUserPage = userService.userInfo(userQueryVO);
        dataResult.setData(sysUserPage);
        return dataResult;
    }

    /**
     * 启用，禁用用户
     * @param userQueryVO
     * @return
     */
    @PutMapping("changeUserStatus")
    public DataResult changeUserStatus(@RequestBody UserQueryVO userQueryVO){
        DataResult dataResult = DataResult.success();
         userService.changeUserStatus(userQueryVO);
        return dataResult;
    }

    @GetMapping("/{id}")
    public DataResult getUser(@PathVariable("id") String id){
        DataResult dataResult = new DataResult();
        SysUser sysUser = userService.getUser(id);
        dataResult.setData(sysUser);
        return dataResult;
    }
    @PostMapping("")
    public DataResult insertUser(@RequestBody SysUser sysUser){
        DataResult dataResult = new DataResult();
        userService.insertUser(sysUser);
        return dataResult;
    }
    @DeleteMapping("/{ids}")
    public DataResult deleteUser(@PathVariable("ids")String[] ids){
        DataResult dataResult = new DataResult();
        userService.deleteUser(ids);
        return dataResult;
    }
    @PutMapping("")
    public DataResult updateUser(@RequestBody SysUser sysUser){
        DataResult dataResult = new DataResult();
        userService.updateUser(sysUser);
        return dataResult;
    }
    @GetMapping("/refreshToken")
    public DataResult refreshToken(HttpServletRequest request){
        System.out.println("执行了refreshToken接口");
        String refreshToken = request.getHeader(Constants.REFRESH_TOKEN);
        LoginRespVO loginRespVO = userService.refreshToken(refreshToken);
        DataResult dataResult = DataResult.success();
        dataResult.setData(loginRespVO);
        return dataResult;
    }
}
