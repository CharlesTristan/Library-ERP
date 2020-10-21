package com.chentong.erp.shiro;

import com.chentong.erp.common.util.JwtTokenUtil;
import com.chentong.erp.constant.Constants;
import com.chentong.erp.service.PermissionService;
import com.chentong.erp.service.RedisService;
import com.chentong.erp.service.RoleService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/12 13:42
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RedisService redisService;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomUsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 直接从token里面拿出用户的权限
        String accessToken= (String) principalCollection.getPrimaryPrincipal();
        Claims claimsFromToken = JwtTokenUtil.getClaimsFromToken(accessToken);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        String userId=JwtTokenUtil.getUserId(accessToken);
        log.info("userId={}",userId);
        // 当因为更新权限，删除权限，更新角色，删除角色，首页更新密码，后台管理员删除用户，管理员赋予用户角色所导致的清空授权数据缓存时
        // 就代表权限已经改变，需要再重新查询放到缓存里
        if(redisService.hasKey(Constants.JWT_REFRESH_KEY+userId)&&redisService.getExpire(Constants.JWT_REFRESH_KEY+userId, TimeUnit.MILLISECONDS)>JwtTokenUtil.getRemainingTime(accessToken)){
            List<String> roles=roleService.getRoleByUserId(userId);
            if(roles!=null&&!roles.isEmpty()){
                info.addRoles(roles);
            }
            List<String> permissionByUserId = permissionService.getPermissionByUserId(userId);
            if(permissionByUserId!=null&&!permissionByUserId.isEmpty()){
                info.addStringPermissions(permissionByUserId);
            }

        }else {     // 第一次鉴权
            if(claimsFromToken.get(Constants.PERMISSIONS_INFOS_KEY)!=null){
                info.addStringPermissions((Collection<String>) claimsFromToken.get(Constants.PERMISSIONS_INFOS_KEY));
            }
            if(claimsFromToken.get(Constants.ROLES_INFOS_KEY)!=null){
                info.addRoles((Collection<String>) claimsFromToken.get(Constants.ROLES_INFOS_KEY));
            }
        }

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomUsernamePasswordToken customUsernamePasswordToken = (CustomUsernamePasswordToken) authenticationToken;
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(customUsernamePasswordToken.getPrincipal(), customUsernamePasswordToken.getCredentials(),CustomRealm.class.getName());
        return info;
    }
}
