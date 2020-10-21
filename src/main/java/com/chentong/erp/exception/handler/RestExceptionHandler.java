package com.chentong.erp.exception.handler;

import com.chentong.erp.exception.BusinessException;
import com.chentong.erp.exception.code.BaseResponseCode;
import com.chentong.erp.vo.resp.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.List;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/10 16:11
 */
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public DataResult exception(Exception e){
        log.error("Exception,{},{}",e.getLocalizedMessage(),e);
        return DataResult.getResult(BaseResponseCode.SYSTEM_ERROR);
    }

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public DataResult businessException(BusinessException e){
        log.error("businessException,{},{}",e.getLocalizedMessage(),e);
        return DataResult.getResult(e.getCode(),e.getMsg());
    }
    /**
     * 处理validation 框架异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    <T> DataResult<T> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

        log.error("methodArgumentNotValidExceptionHandler bindingResult.allErrors():{},exception:{}", e.getBindingResult().getAllErrors(), e);
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        return createValidExceptionResp(errors);
    }
    private <T> DataResult<T> createValidExceptionResp(List<ObjectError> errors) {
        String[] msgs = new String[errors.size()];
        int i = 0;
        for (ObjectError error : errors) {
            msgs[i] = error.getDefaultMessage();
            log.info("msg={}",msgs[i]);
            i++;
        }
        return DataResult.getResult(BaseResponseCode.METHOD_IDENTITY_ERROR.getCode(), msgs[0]);
    }

    /**
     * 处理shiro没有该url权限的异常
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public DataResult unauthorizedException(UnauthorizedException e){
        log.error("UnauthorizedException,{},{}",e.getLocalizedMessage(),e);
        return DataResult.getResult(BaseResponseCode.NOT_PERMISSION);
    }

    /**
     * 上传文件过大异常
     * @param e
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public DataResult maxUploadSizeExceededException(MaxUploadSizeExceededException e){
        log.error("MaxUploadSizeExceededException,{},{}",e,e.getLocalizedMessage());
        return DataResult.getResult(BaseResponseCode.FILE_TOO_LARGE);
    }
}
