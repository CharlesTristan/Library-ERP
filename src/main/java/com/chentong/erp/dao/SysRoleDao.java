package com.chentong.erp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chentong.erp.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (SysRole)表数据库访问层
 *
 * @author Admin
 * @since 2020-10-12 15:05:49
 */
@Repository
@Mapper
public interface SysRoleDao extends BaseMapper<SysRole> {
    List<String> getRoleByUserId(@Param("id") String id);
}