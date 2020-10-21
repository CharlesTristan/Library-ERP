package com.chentong.erp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chentong.erp.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (SysUser)表数据库访问层
 *
 * @author Admin
 * @since 2020-10-10 10:57:07
 */
@Repository
@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {
}