package com.chentong.erp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chentong.erp.entity.DicDictionary;
import com.chentong.erp.service.DictionaryService;
import com.chentong.erp.vo.resp.DataResult;
import com.chentong.erp.vo.resp.LineChartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.time.LocalDate;
import java.util.*;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/11/19 17:24
 */
@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @GetMapping("lineChart")
    public DataResult dictionaryList(DicDictionary dicDictionary){
        DataResult dataResult = DataResult.success();
        LineChartVO lineChartVO = new LineChartVO();
        List<String> list = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        list.add(localDate.plusDays(-6).toString());
        list.add(localDate.plusDays(-5).toString());
        list.add(localDate.plusDays(-4).toString());
        list.add(localDate.plusDays(-3).toString());
        list.add(localDate.plusDays(-2).toString());
        list.add(localDate.plusDays(-1).toString());
        list.add(localDate.toString());
        lineChartVO.setXAxisData(list.toArray(new String[list.size()]));
        List<Integer> seriesList = new ArrayList<>();
//        Random random = new Random();
        seriesList.add(50);
        seriesList.add(72);
        seriesList.add(34);
        seriesList.add(63);
        seriesList.add(110);
        seriesList.add(20);
        seriesList.add(89);
        lineChartVO.setSeriesData(seriesList.toArray(new Integer[list.size()]));
        dataResult.setData(lineChartVO);
        return dataResult;
    }
}
