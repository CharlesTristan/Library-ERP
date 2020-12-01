package com.chentong.erp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chentong.erp.entity.SysNews;
import com.chentong.erp.entity.SysNotice;
import com.chentong.erp.service.SysNewService;
import com.chentong.erp.service.SysNoticeService;
import com.chentong.erp.vo.resp.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/11/18 17:17
 */
@RestController
    @RequestMapping("/news")
public class SysNewServiceController {
    @Autowired
    private SysNewService sysNewService;
        @GetMapping("/list")
    public DataResult listWithCondition(){
        DataResult dataResult = DataResult.success();
        List<SysNews> sysNews = sysNewService.newsList();
        dataResult.setData(sysNews);
        return dataResult;
    }
    @GetMapping("/active")
    public DataResult selectNotice(){
        DataResult dataResult = DataResult.success();
        List<SysNews> sysNews = sysNewService.activeList();
        dataResult.setData(sysNews);
        return dataResult;
    }
}
