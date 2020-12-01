package com.chentong.erp.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/14 15:07
 */
@Data
public class PermissionRespNodeVO {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "跳转地址")
    private String path;
    private String perms;
    private String type;
    private String status;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createTime;
    private String icon;
    @ApiModelProperty(value = "菜单权限名称")
    private String name;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "子集集合")
    private List<?> children;

    private MetaVO meta;
}
