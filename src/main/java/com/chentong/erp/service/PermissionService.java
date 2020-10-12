package com.chentong.erp.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionService {
    List<String> getPermissionByUserId(String id);
}
