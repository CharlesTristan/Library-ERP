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
 * @date 2020/10/26 15:46
 */
@Data
public class RoleQueryVO {
    private String id;
    private String name;
    private String description;
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
