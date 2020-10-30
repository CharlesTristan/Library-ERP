package com.chentong.erp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chentong.erp.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (SysPermission)表数据库访问层
 *
 * @author Admin
 * @since 2020-10-12 15:05:48
 */
@Repository
@Mapper
public interface SysPermissionDao extends BaseMapper<SysPermission> {
    List<SysPermission> getPermissionByUserId(@Param("id") String id);
    List<String> getPermissionStrByUserId(@Param("id") String id);
    SysPermission[] getPermissionByRoleId(@Param("id") String id);
}