package com.chentong.erp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chentong.erp.entity.SysUser;
import com.chentong.erp.vo.req.LoginReqVO;
import com.chentong.erp.vo.req.UserQueryVO;
import com.chentong.erp.vo.resp.LoginRespVO;

public interface UserService {
    LoginRespVO login(LoginReqVO loginReqVO);
    Page<SysUser> userInfo(UserQueryVO userQueryVO);

    void changeUserStatus(UserQueryVO userQueryVO);

    void insertUser(SysUser sysUser);

    void deleteUser(String[] ids);

    void updateUser(SysUser sysUser);

    SysUser getUser(String id);

    LoginRespVO refreshToken(String refreshToken);
}
