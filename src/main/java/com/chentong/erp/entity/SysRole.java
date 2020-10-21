package com.chentong.erp.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysRole)实体类
 *
 * @author Admin
 * @since 2020-10-12 15:05:49
 */
@Data
@Builder
public class SysRole implements Serializable {
    private static final long serialVersionUID = 187740173682853706L;
    /**
     * 主键
     */
    private String id;
    /**
     * 角色名称
     */
    private String name;

    private String description;
    /**
     * 状态(1:正常0:弃用)
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否删除(1未删除；0已删除)
     */
    private Integer deleted;

}