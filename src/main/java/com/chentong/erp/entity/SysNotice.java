package com.chentong.erp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysNotice)实体类
 *
 * @author Admin
 * @since 2020-11-09 16:22:41
 */
@Data
public class SysNotice implements Serializable {
    private static final long serialVersionUID = 779944397019437472L;
    /**
     * 主键
     */
    private String id;
    /**
     * 公告标题
     */
    private String title;
    /**
     * 1公告2通知
     */
    private String type;
    /**
     * 1正常 2弃用
     */
    private String status;
    private String noticeContent;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 创建者
     */
    private String userId;
    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private Integer pageNum;
    @TableField(exist = false)
    private Integer pageSize;
}