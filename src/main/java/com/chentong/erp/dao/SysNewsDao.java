package com.chentong.erp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chentong.erp.entity.SysNews;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (SysNews)表数据库访问层
 *
 * @author Admin
 * @since 2020-11-18 16:57:50
 */
@Repository
@Mapper
public interface SysNewsDao extends BaseMapper<SysNews> {

}