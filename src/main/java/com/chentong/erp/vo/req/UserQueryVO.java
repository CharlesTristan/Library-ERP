package com.chentong.erp.vo.req;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/22 16:05
 */
@Data
public class UserQueryVO {
    /**
     * 用户id
     */
    private String id;
    /**
     * 账户名称
     */
    private String username;
    /**
     * 手机号码
     */
    /**
     * 账户状态(1.正常 2.锁定 )
     */
    private String status;
    /**
     * 手机号
     */
    private String phone;

    private Date beginTime;
    private Date endTime;
    private Integer pageNum;
    private Integer pageSize;

}
