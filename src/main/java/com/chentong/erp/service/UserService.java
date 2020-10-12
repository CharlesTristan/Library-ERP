package com.chentong.erp.service;

import com.chentong.erp.vo.req.LoginReqVO;
import com.chentong.erp.vo.resp.LoginRespVO;

public interface UserService {
    LoginRespVO login(LoginReqVO loginReqVO);
}
