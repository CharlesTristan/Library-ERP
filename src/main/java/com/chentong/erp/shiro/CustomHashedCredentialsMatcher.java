package com.chentong.erp.shiro;

import com.chentong.erp.common.util.JwtTokenUtil;
import com.chentong.erp.constant.Constants;
import com.chentong.erp.exception.BusinessException;
import com.chentong.erp.exception.code.BaseResponseCode;
import com.chentong.erp.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/13 11:20
 */
@Slf4j
public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {
    @Autowired
    private RedisService redisService;

    /**
     * doCredentialsMatch方法返回true代表认证通过，返回false表示失败
     * 默认的是校验传来的账号密码和数据库查出来的账号密码是否一致，所以不适合本系统，要重写
     * 校验token是否是系统签发出去的，并且是否符合要求，比如过期 是否删除 是否被禁用
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomUsernamePasswordToken customUsernamePasswordToken= (CustomUsernamePasswordToken) token;
        String accessToken= (String) customUsernamePasswordToken.getCredentials();
        String userId= JwtTokenUtil.getUserId(accessToken);
        log.info("doCredentialsMatch....userId={}",userId);
        //判断用户是否被删除
        if(redisService.hasKey(Constants.DELETED_USER_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_HAS_DELETED_ERROR);
        }
        //判断是否被锁定
        if(redisService.hasKey(Constants.ACCOUNT_LOCK_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        /**
         * 判断用户是否退出登录
         */
        if(redisService.hasKey(Constants.JWT_ACCESS_TOKEN_BLACKLIST+accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }

        //校验token
        if(!JwtTokenUtil.validateToken(accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
        }
        /**
         * 判断用户是否被标记了
         */
        if(redisService.hasKey(Constants.JWT_REFRESH_KEY+userId)){
            /**
             * 判断用户是否已经刷新过
             */
            if(redisService.getExpire(Constants.JWT_REFRESH_KEY+userId, TimeUnit.MILLISECONDS)>JwtTokenUtil.getRemainingTime(accessToken)){
                throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
            }
        }

        return true;
    }
}
