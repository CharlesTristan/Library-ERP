package com.chentong.erp.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * (SysNews)实体类
 *
 * @author Admin
 * @since 2020-11-18 16:57:45
 */
@Data
@Builder
public class SysNews implements Serializable {
    private static final long serialVersionUID = -22209033287080870L;

    private String id;
    /**
     * 新闻内容
     */
    private String content;
    /**
     * 新闻类型 1.新闻 2.活动
     */
    private String type;

}