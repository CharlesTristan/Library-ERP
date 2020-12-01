package com.chentong.erp.service;

import com.chentong.erp.entity.SysNews;

import java.util.List;

public interface SysNewService {
    List<SysNews> newsList();
    List<SysNews> activeList();
}
