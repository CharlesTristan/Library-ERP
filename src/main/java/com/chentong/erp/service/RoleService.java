package com.chentong.erp.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {
    List<String> getRoleByUserId(String id);
}
