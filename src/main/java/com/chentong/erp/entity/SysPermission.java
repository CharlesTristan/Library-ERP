package com.chentong.erp.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysPermission)实体类
 *
 * @author Admin
 * @since 2020-10-12 15:05:48
 */
@Data
@Builder
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 119679100511636167L;
    /**
     * 主键
     */
    private String id;
    /**
     * 菜单权限编码
     */
    private String code;
    /**
     * 菜单权限名称
     */
    private String name;
    /**
     * 授权(如：sys:user:add)
     */
    private String perms;
    /**
     * 访问地址URL
     */
    private String url;
    /**
     * 资源请求类型
     */
    private String method;
    /**
     * 父级菜单权限名称
     */
    private String pid;
    /**
     * 排序
     */
    private Integer orderNum;
    /**
     * 菜单权限类型(1:目录;2:菜单;3:按钮)
     */
    private Integer type;
    /**
     * 状态1:正常 0：禁用
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