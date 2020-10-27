package com.chentong.erp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chentong.erp.vo.req.RoleQueryVO;

import java.util.List;

public interface RoleService {
    List<String> getRoleByUserId(String id);

    IPage roleList(RoleQueryVO roleQueryVO);
}
