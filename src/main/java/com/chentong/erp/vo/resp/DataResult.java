package com.chentong.erp.vo.resp;

import com.chentong.erp.exception.code.BaseResponseCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.chentong.erp.exception.code.ResponseCodeInterface;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/10 17:44
 */
@Data
public class DataResult<T>{

    /**
     * 请求响应code， 0表示请求成功 其它表示失败
     */
    @ApiModelProperty(value = "请求响应code，0为成功 其他为失败")
    private int code;

    /**
     * 响应客户端的提示
     */
    @ApiModelProperty(value = "响应异常码详细信息")
    private String msg;

    /**
     * 响应客户端内容
     */
    @ApiModelProperty(value = "响应客户端内容")
    private T data;


    public DataResult() {
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg=BaseResponseCode.SUCCESS.getMsg();
        this.data=null;
    }

    public DataResult(T data) {
        this.data = data;
        this.code=BaseResponseCode.SUCCESS.getCode();
        this.msg=BaseResponseCode.SUCCESS.getMsg();
    }

    /**
     * 操作成功 data为null
     */
    public static DataResult success(){
        return new DataResult();
    }
    /**
     * 操作成功 data 不为null
     */
    public static <T>DataResult success(T data){
        return new DataResult(data);
    }

    /**
     * 自定义返回 入参是异常code枚举    data为空
     */
    public static DataResult getResult(ResponseCodeInterface responseCode){
        DataResult dataResult = new DataResult();
        dataResult.setCode(responseCode.getCode());
        dataResult.setMsg(responseCode.getMsg());
        return dataResult;
    }
    /**
     * 自定义返回 入参异常code枚举   data 可控
     */
    public static <T>DataResult getResult(ResponseCodeInterface responseCode, T data){
        DataResult dataResult = new DataResult();
        dataResult.setCode(responseCode.getCode());
        dataResult.setMsg(responseCode.getMsg());
        dataResult.setData(data);
        return dataResult;
    }
    public static <T>DataResult getResult(int code,String msg){
        DataResult dataResult = new DataResult();
        dataResult.setCode(code);
        dataResult.setMsg(msg);
        return dataResult;
    }
    public static <T>DataResult getResult(int code,String msg,T data){
        DataResult dataResult = new DataResult();
        dataResult.setCode(code);
        dataResult.setMsg(msg);
        dataResult.setData(data);
        return dataResult;
    }

}
