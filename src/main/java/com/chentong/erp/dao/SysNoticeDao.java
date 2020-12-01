package com.chentong.erp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chentong.erp.entity.SysNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (SysNotice)表数据库访问层
 *
 * @author Admin
 * @since 2020-11-09 16:23:21
 */
@Repository
@Mapper
public interface SysNoticeDao extends BaseMapper<SysNotice> {
    IPage<SysNotice> listWithCondition(IPage<SysNotice> page,@Param("sysNotice") SysNotice sysNotice);
}