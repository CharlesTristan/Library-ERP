package com.chentong.erp.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysRolePermission)实体类
 *
 * @author Admin
 * @since 2020-10-29 16:39:28
 */
@Data
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = 652708909262751558L;
    /**
     * 主键
     */
    private String id;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 菜单权限id
     */
    private String permissionId;
    /**
     * 创建时间
     */
    private Date createTime;

}