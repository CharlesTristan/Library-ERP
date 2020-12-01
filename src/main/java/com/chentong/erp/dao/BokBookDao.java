package com.chentong.erp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chentong.erp.entity.BokBook;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (BokBook)表数据库访问层
 *
 * @author Admin
 * @since 2020-11-16 10:26:18
 */
@Repository
@Mapper
public interface BokBookDao extends BaseMapper<BokBook> {

}