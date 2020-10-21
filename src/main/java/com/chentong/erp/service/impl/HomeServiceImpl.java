package com.chentong.erp.service.impl;

import com.chentong.erp.dao.SysPermissionDao;
import com.chentong.erp.dao.SysUserDao;
import com.chentong.erp.entity.SysPermission;
import com.chentong.erp.entity.SysUser;
import com.chentong.erp.service.HomeService;
import com.chentong.erp.service.PermissionService;
import com.chentong.erp.vo.resp.HomeRespVO;
import com.chentong.erp.vo.resp.PermissionRespNodeVO;
import com.chentong.erp.vo.resp.UserInfoRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/14 15:37
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private PermissionService permissionService;

    @Override
    public HomeRespVO getHome(String userId) {
        HomeRespVO homeRespVO = new HomeRespVO();
        List<PermissionRespNodeVO> list=permissionService.permissionTreeList(userId);
        homeRespVO.setMenus(list);
        SysUser sysUser = sysUserDao.selectById(userId);
        UserInfoRespVO vo=new UserInfoRespVO();
        if(sysUser!=null){
            BeanUtils.copyProperties(sysUser,vo);
            vo.setDeptName("迎学教育总公司");
        }
        homeRespVO.setUserInfoVO(vo);
        return homeRespVO;
    }
}
