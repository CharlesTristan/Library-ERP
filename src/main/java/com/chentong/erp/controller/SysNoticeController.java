package com.chentong.erp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chentong.erp.constant.Constants;
import com.chentong.erp.entity.SysNotice;
import com.chentong.erp.service.SysNoticeService;
import com.chentong.erp.vo.resp.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/11/9 16:29
 */
@RestController
@RequestMapping("/notice")
public class SysNoticeController {
    @Autowired
    private SysNoticeService sysNoticeService;
    @GetMapping("/list")
    public DataResult listWithCondition(SysNotice sysNotice){
        DataResult dataResult = DataResult.success();
        IPage<SysNotice> sysNoticePage = sysNoticeService.listWithCondition(sysNotice);
        dataResult.setData(sysNoticePage);
        return dataResult;
    }
    @GetMapping("/{id}")
    public DataResult selectNotice(@PathVariable("id")String id){
        DataResult dataResult = DataResult.success();
        SysNotice sysNotice = sysNoticeService.selectSysNotice(id);
        dataResult.setData(sysNotice);
        return dataResult;
    }
    @PostMapping("")
    public DataResult insertNotice(@RequestBody SysNotice sysNotice, HttpServletRequest request){
        DataResult dataResult = DataResult.success();
        String header = request.getHeader(Constants.ACCESS_TOKEN);
        sysNoticeService.insertSysNotice(sysNotice,header);
        return dataResult;
    }
    @DeleteMapping("/{ids}")
    public DataResult deleteNotice(@PathVariable("ids") String[] ids){
        DataResult dataResult = DataResult.success();
        sysNoticeService.deleteSysNotice(ids);
        return dataResult;
    }
    @PutMapping("")
    public DataResult updateNotice(@RequestBody SysNotice sysNotice, HttpServletRequest request){
        DataResult dataResult = DataResult.success();
        String header = request.getHeader(Constants.ACCESS_TOKEN);
        sysNoticeService.updateSysNotice(sysNotice,header);
        return dataResult;
    }
}
