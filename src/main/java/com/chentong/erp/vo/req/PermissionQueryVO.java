package com.chentong.erp.vo.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/11/2 14:48
 */
@Data
public class PermissionQueryVO {
    /**
     * 主键
     */
    private String id;
    /**
     * 菜单权限名称
     */
    private String name;
    /**
     * 授权(如：sys:user:add)
     */
    private String perms;
    /**
     * 资源请求类型
     */
    private String method;
    /**
     * 状态1:正常 0：禁用
     */
    private String status;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date beginTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date endTime;
    private Integer pageNum;
    private Integer pageSize;
}
