package com.chentong.erp.service;

import com.chentong.erp.vo.resp.HomeRespVO;

public interface HomeService {
    HomeRespVO getHome(String userId);
}
