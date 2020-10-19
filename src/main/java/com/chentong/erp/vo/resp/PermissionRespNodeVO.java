package com.chentong.erp.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
//    @ApiModelProperty(value = "主键id")
//    private String id;

    @ApiModelProperty(value = "跳转地址")
    private String path;

    @ApiModelProperty(value = "菜单权限名称")
    private String name;

    @ApiModelProperty(value = "子集集合")
    private List<?> children;

    @ApiModelProperty(value = "组件")
    private String component;

//    @ApiModelProperty(value = "默认展开")
//    private boolean spread=true;

//    @ApiModelProperty(value = "节点是否选中")
//    private boolean checked;
}
