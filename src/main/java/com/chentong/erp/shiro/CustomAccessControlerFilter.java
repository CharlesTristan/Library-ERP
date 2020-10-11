package com.chentong.erp.shiro;

import com.chentong.erp.constant.Constants;
import com.chentong.erp.exception.BusinessException;
import com.chentong.erp.exception.code.BaseResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

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

        }catch (AuthenticationException e){
            //

        }catch (Exception e){

        }
        return false;
    }
}
























