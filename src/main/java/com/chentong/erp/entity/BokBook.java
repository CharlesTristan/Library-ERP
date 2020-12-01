package com.chentong.erp.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * (BokBook)实体类
 *
 * @author Admin
 * @since 2020-11-16 10:26:17
 */
@Data
public class BokBook implements Serializable {
    private static final long serialVersionUID = 426776182909137318L;

    private String id;
    /**
     * 书名
     */
    private String name;
    /**
     * 页码
     */
    private String pagination;
    /**
     * 封面地址
     */
    private String cover;
    /**
     * 作者
     */
    private String author;
    /**
     * 书籍简介
     */
    private String remark;
    /**
     * 书籍类型
     */
    private String type;
    /**
     * 借阅状态
     */
    private String status;

}