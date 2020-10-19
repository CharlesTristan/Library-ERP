package com.chentong.erp.service.impl;

import com.chentong.erp.dao.SysPermissionDao;
import com.chentong.erp.entity.SysPermission;
import com.chentong.erp.service.PermissionService;
import com.chentong.erp.vo.resp.PermissionRespNodeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/12 15:15
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private SysPermissionDao permissionDao;
    @Override
    public List<PermissionRespNodeVO> permissionTreeList(String userId) {
        List<SysPermission> list=getPermissions(userId);
        return getTree(list,true);
    }

    @Override
    public List<SysPermission> getPermissions(String userId) {
        List<SysPermission> result=permissionDao.getPermissionByUserId(userId);
        return result;
    }

    @Override
    public List<String> getPermissionByUserId(String userId) {
        List<String> result=permissionDao.getPermissionStrByUserId(userId);
        return result;
    }

    /**
     * type=true 递归遍历到菜单
     * type=false 递归遍历到按钮
     */
    private List<PermissionRespNodeVO> getTree(List<SysPermission> all,boolean type){

        List<PermissionRespNodeVO> list=new ArrayList<>();
        if(all==null||all.isEmpty()){
            return list;
        }
        for(SysPermission sysPermission:all){
            if(sysPermission.getPid().equals("0")){
                PermissionRespNodeVO respNodeVO=new PermissionRespNodeVO();
                BeanUtils.copyProperties(sysPermission,respNodeVO);
                respNodeVO.setName(sysPermission.getName());
                if(type){
                    respNodeVO.setChildren(getChildExBtn(sysPermission.getId(),all));
                }else {
                    respNodeVO.setChildren(getChild(sysPermission.getId(),all));
                }

                list.add(respNodeVO);
            }
        }
        return list;
    }

    /**
     * 只递归到菜单
     */
    private List<PermissionRespNodeVO> getChildExBtn(String id,List<SysPermission> all){
        List<PermissionRespNodeVO> list=new ArrayList<>();
        for (SysPermission s : all) {
            if(s.getPid().equals(id) && s.getType()!=3){
                PermissionRespNodeVO respNodeVO=new PermissionRespNodeVO();
                BeanUtils.copyProperties(s,respNodeVO);
                respNodeVO.setName(s.getName());
                respNodeVO.setChildren(getChildExBtn(s.getId(),all));
                list.add(respNodeVO);
            }
        }
        return list;
    }

    /**
     * 递归遍历所有数据
     */
    private List<PermissionRespNodeVO> getChild(String id,List<SysPermission> all){
        List<PermissionRespNodeVO> list=new ArrayList<>();
        for (SysPermission s : all) {
            if(s.getPid().equals(id)){
                PermissionRespNodeVO respNodeVO=new PermissionRespNodeVO();
                BeanUtils.copyProperties(s,respNodeVO);
                respNodeVO.setName(s.getName());
                respNodeVO.setChildren(getChild(s.getId(),all));
                list.add(respNodeVO);
            }
        }
        return list;
    }
}
