package com.chentong.erp.vo.req;

import lombok.Data;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/11/16 10:24
 */
@Data
public class BookQueryVO {
    /**
     * 书名
     */
    private String name;
    /**
     * 作者
     */
    private String author;
    /**
     * 书籍类型
     */
    private String type;
    private Integer pageNum;
    private Integer pageSize;
}
