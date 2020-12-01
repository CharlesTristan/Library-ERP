package com.chentong.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chentong.erp.dao.SysNewsDao;
import com.chentong.erp.entity.SysNews;
import com.chentong.erp.service.SysNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/11/18 16:59
 */
@Service
public class SysNewServiceImpl implements SysNewService {
    @Autowired
    private SysNewsDao sysNewsDao;
    @Override
    public List<SysNews> newsList() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("type","1");
        List<SysNews> list = sysNewsDao.selectList(queryWrapper);
        return list;
    }

    @Override
    public List<SysNews> activeList() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("type","2");
        List<SysNews> list = sysNewsDao.selectList(queryWrapper);
        return list;
    }
}
