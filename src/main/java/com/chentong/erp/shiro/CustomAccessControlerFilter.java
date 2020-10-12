package com.chentong.erp.shiro;

import com.chentong.erp.constant.Constants;
import com.chentong.erp.exception.BusinessException;
import com.chentong.erp.exception.code.BaseResponseCode;
import com.chentong.erp.vo.resp.DataResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/10 16:35
 */
@Slf4j
public class CustomAccessControlerFilter extends AccessControlFilter {
    /**
     * 这个方法必须返回false,如果返回true就流转到下一过滤器，不执行onAccessDenoed方法了
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    /**
     * 如果返回true，就流转到下一个链式调用
     *  false就不会流转到下一个链式调用
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        log.info("request 接口地址：{}",request.getRequestURI());
        log.info("request 接口的请求方式{}",request.getMethod());
        String accessToken=request.getHeader(Constants.ACCESS_TOKEN);
        try {
            // TOKEN_NOT_NULL(4010001,"token 不能为空"),
            if(StringUtils.isEmpty(accessToken)){
                throw new BusinessException(BaseResponseCode.TOKEN_NOT_NULL);
            }
            CustomUsernamePasswordToken customUsernamePasswordToken=new CustomUsernamePasswordToken(accessToken);
            getSubject(servletRequest,servletResponse).login(customUsernamePasswordToken);
        }catch (BusinessException e){
             // 这个异常是捕获的上面抛出的token不能为空的异常
            if(e.getCause() instanceof BusinessException){
                BusinessException businessException = (BusinessException) e.getCause();
                customResponse(servletResponse,e.getCode(),e.getMsg());
            }else {
                customResponse(servletResponse,BaseResponseCode.TOKEN_ERROR.getCode(), BaseResponseCode.TOKEN_ERROR.getMsg());
                return false;
            }
            return true;
        }catch (AuthenticationException e){
            //
            customResponse(servletResponse,BaseResponseCode.SYSTEM_ERROR.getCode(), BaseResponseCode.SYSTEM_ERROR.getMsg());
            return false;
        }catch (Exception e){

        }
        return false;
    }

    /**
     * 自定义响应前端
     */
    private void customResponse(ServletResponse response,int code ,String msg){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DataResult result= DataResult.getResult(code,msg);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setCharacterEncoding("UTF-8");
            String str= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            OutputStream outputStream=response.getOutputStream();
            outputStream.write(str.getBytes("UTF-8"));
            outputStream.flush();
        } catch (IOException e) {
            log.error("customResponse...error:{}",e);
        }

    }
}
























