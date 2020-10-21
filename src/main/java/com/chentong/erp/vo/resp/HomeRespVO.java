package com.chentong.erp.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/14 15:06
 */
@Data
public class HomeRespVO {
    @ApiModelProperty(value = "用户信息")
    private UserInfoRespVO userInfoVO;

    @ApiModelProperty(value = "首页菜单导航数据")
    private List<PermissionRespNodeVO> menus;
}
