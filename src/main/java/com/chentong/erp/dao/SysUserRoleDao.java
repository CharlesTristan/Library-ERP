package com.chentong.erp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chentong.erp.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (SysUserRole)表数据库访问层
 *
 * @author Admin
 * @since 2020-10-27 13:33:40
 */
@Repository
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {

}