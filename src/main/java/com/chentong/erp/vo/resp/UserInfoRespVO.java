package com.chentong.erp.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/14 15:27
 */
@Data
public class UserInfoRespVO {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "部门id")
    private String deptId;
    @ApiModelProperty(value = "所属部门名称")
    private String deptName;
}
