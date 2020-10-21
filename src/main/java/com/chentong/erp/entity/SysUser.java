package com.chentong.erp.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysUser)实体类
 *
 * @author Admin
 * @since 2020-10-10 10:57:07
 */
@Data
@Builder
public class SysUser implements Serializable {
    private static final long serialVersionUID = -93839798439102143L;
    /**
     * 用户id
     */
    private String id;
    /**
     * 账户名称
     */
    private String username;
    /**
     * 加密盐值
     */
    private String salt;
    /**
     * 用户密码密文
     */
    private String password;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 部门id
     */
    private String deptId;
    /**
     * 真实名称
     */
    private String realName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 邮箱(唯一)
     */
    private String email;
    /**
     * 账户状态(1.正常 2.锁定 )
     */
    private Integer status;
    /**
     * 性别(1.男 2.女)
     */
    private Integer sex;
    /**
     * 是否删除(1未删除；0已删除)
     */
    private Integer deleted;
    /**
     * 创建人
     */
    private String createId;
    /**
     * 更新人
     */
    private String updateId;
    /**
     * 创建来源(1.web 2.android 3.ios )
     */
    private Integer createWhere;
    /**
     * 创建时间
     */
    private Date createTime;

    private Date updateTime;

}