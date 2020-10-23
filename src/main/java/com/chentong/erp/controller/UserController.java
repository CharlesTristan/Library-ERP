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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @GetMapping("getInfo")
    public DataResult getInfo(HttpServletRequest request){
        DataResult dataResult = DataResult.success();
        String header = request.getHeader(Constants.ACCESS_TOKEN);
        String userId = JwtTokenUtil.getUserId(header);
        HomeRespVO home = homeService.getHome(userId);
        dataResult.setData(home);
        return dataResult;
    }
    @GetMapping("userInfo")
    public DataResult userInfo(UserQueryVO userQueryVO){
        DataResult dataResult = DataResult.success();
        Page<SysUser> sysUserPage = userService.userInfo(userQueryVO);
        dataResult.setData(sysUserPage);
        return dataResult;
    }

    @PutMapping("changeUserStatus")
    public DataResult changeUserStatus(@RequestBody UserQueryVO userQueryVO){
        DataResult dataResult = DataResult.success();
         userService.changeUserStatus(userQueryVO);
        return dataResult;
    }
}
