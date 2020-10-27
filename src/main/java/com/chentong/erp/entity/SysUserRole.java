package com.chentong.erp.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysUserRole)实体类
 *
 * @author Admin
 * @since 2020-10-27 13:30:54
 */
@Data
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -24013795450004413L;
    /**
     * 主键
     */
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 创建时间
     */
    private Date createTime;

}