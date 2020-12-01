package com.chentong.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chentong.erp.common.util.JwtTokenUtil;
import com.chentong.erp.dao.SysNoticeDao;
import com.chentong.erp.entity.SysNotice;
import com.chentong.erp.service.SysNoticeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/11/9 16:27
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService {
    @Autowired
    private SysNoticeDao sysNoticeDao;
    @Override
    public IPage<SysNotice> listWithCondition(SysNotice sysNotice) {
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.like(StringUtils.isNotBlank(sysNotice.getTitle()),"title",sysNotice.getTitle())
//                .eq(StringUtils.isNotBlank(sysNotice.getType()),"type",sysNotice.getType())
//                .eq(StringUtils.isNotBlank(sysNotice.getStatus()),"status",sysNotice.getStatus())
//                .eq(StringUtils.isNotBlank(sysNotice.getUserId()),"user_id",sysNotice.getUserId());
        Page<SysNotice> sysNoticePage = new Page<>(sysNotice.getPageNum(), sysNotice.getPageSize());
        IPage<SysNotice> sysNoticeIPage = sysNoticeDao.listWithCondition(sysNoticePage, sysNotice);
        return sysNoticeIPage;
    }

    @Override
    public SysNotice selectSysNotice(String id) {
        SysNotice sysNotice = sysNoticeDao.selectById(id);
        return sysNotice;
    }

    @Override
    public void insertSysNotice(SysNotice sysNotice,String token) {
        sysNotice.setCreateTime(new Date());
        sysNotice.setUserId(JwtTokenUtil.getUserId(token));
        sysNoticeDao.insert(sysNotice);
    }

    @Override
    public void deleteSysNotice(String[] ids) {
        sysNoticeDao.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public void updateSysNotice(SysNotice sysNotice, String token) {
        sysNotice.setUserId(JwtTokenUtil.getUserId(token));
        sysNoticeDao.updateById(sysNotice);
    }
}
