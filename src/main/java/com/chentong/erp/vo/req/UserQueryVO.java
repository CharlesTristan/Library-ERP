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
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date beginTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date endTime;
    private Integer pageNum;
    private Integer pageSize;

}
