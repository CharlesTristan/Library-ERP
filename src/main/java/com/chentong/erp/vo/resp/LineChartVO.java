package com.chentong.erp.vo.resp;

import lombok.Data;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/11/20 8:50
 */
@Data
public class LineChartVO {
    private String[] xAxisData;
    private Integer[] seriesData;
}