package com.chentong.erp.vo.resp;

import com.chentong.erp.entity.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/14 15:29
 */
@Data
public class UserOwnRoleRespVO {
    @ApiModelProperty(value = "拥有角色集合")
    private List<String> ownRoles;

    @ApiModelProperty(value = "所有角色列表")
    private List<SysRole> allRole;
}
