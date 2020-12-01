package com.chentong.erp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (DicDictionary)实体类
 *
 * @author Admin
 * @since 2020-11-11 16:44:31
 */
@Data
public class DicDictionary implements Serializable {
    private static final long serialVersionUID = 926612612162008547L;

    private String id;

    private String name;

    private String dicKey;

    private String divValue;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String remark;
    @TableField(exist = false)
    private Integer pageNum;
    @TableField(exist = false)
    private Integer pageSize;
}