package com.chentong.erp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chentong.erp.entity.SysNotice;

import javax.servlet.http.HttpServletRequest;

public interface SysNoticeService {
    IPage<SysNotice> listWithCondition(SysNotice sysNotice);
    SysNotice selectSysNotice(String id);
    void insertSysNotice(SysNotice sysNotice,String token);
    void deleteSysNotice(String[] ids);
    void updateSysNotice(SysNotice sysNotice, String token);
}
