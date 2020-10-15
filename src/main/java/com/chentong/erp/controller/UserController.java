package com.chentong.erp.controller;

import com.chentong.erp.common.util.JwtTokenUtil;
import com.chentong.erp.constant.Constants;
import com.chentong.erp.service.HomeService;
import com.chentong.erp.vo.resp.DataResult;
import com.chentong.erp.vo.resp.HomeRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("getInfo")
    public DataResult getInfo(HttpServletRequest request){
        DataResult dataResult = DataResult.success();
        String header = request.getHeader(Constants.ACCESS_TOKEN);
        String userId = JwtTokenUtil.getUserId(header);
        HomeRespVO home = homeService.getHome(userId);
        dataResult.setData(home);
        return dataResult;
    }
}
