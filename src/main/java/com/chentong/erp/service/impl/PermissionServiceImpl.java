package com.chentong.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chentong.erp.dao.SysPermissionDao;
import com.chentong.erp.entity.SysPermission;
import com.chentong.erp.service.PermissionService;
import com.chentong.erp.vo.req.PermissionQueryVO;
import com.chentong.erp.vo.resp.MetaVO;
import com.chentong.erp.vo.resp.PermissionRespNodeVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/12 15:15
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private SysPermissionDao permissionDao;

    @Override
    public List<PermissionRespNodeVO> permissionWithButtonList(PermissionQueryVO permissionQueryVO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(permissionQueryVO.getName()),"name",permissionQueryVO.getName())
                .like(StringUtils.isNotBlank(permissionQueryVO.getPerms()),"perms",permissionQueryVO.getPerms())
                .eq(StringUtils.isNotBlank(permissionQueryVO.getStatus()),"status",permissionQueryVO.getStatus());
        List<SysPermission> list=permissionDao.selectList(queryWrapper);
        return getTree(list,false);
    }

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

    @Override
    public List<SysPermission> selectList(PermissionQueryVO permissionQueryVO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(permissionQueryVO.getName()),"name",permissionQueryVO.getName())
                .like(StringUtils.isNotBlank(permissionQueryVO.getPerms()),"perms",permissionQueryVO.getPerms())
                .ge(permissionQueryVO.getBeginTime()!=null,"create_time",permissionQueryVO.getBeginTime())
                .le(permissionQueryVO.getEndTime()!=null,"create_time",permissionQueryVO.getEndTime());
//        Page<SysPermission> page = new Page<>(permissionQueryVO.getPageNum(), permissionQueryVO.getPageSize());
//        Page page1 = permissionDao.selectPage(page, queryWrapper);
        List<SysPermission> list = permissionDao.selectList(queryWrapper);
        return list;
    }

    @Override
    public SysPermission getPermission(String id) {
        SysPermission sysPermission = permissionDao.selectById(id);
        return sysPermission;
    }

    @Override
    public void insertPermission(SysPermission sysPermission) {
        sysPermission.setCreateTime(new Date());
        sysPermission.setDeleted(1);

        permissionDao.insert(sysPermission);
    }

    @Override
    public void updatePermission(SysPermission sysPermission) {
        permissionDao.updateById(sysPermission);
    }

    @Override
    public void deletePermission(String[] ids) {
        permissionDao.deleteBatchIds(Arrays.asList(ids));
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
                respNodeVO.setPath(sysPermission.getPath());
                respNodeVO.setComponent(sysPermission.getComponent());
                respNodeVO.setName(sysPermission.getName());
                respNodeVO.setId(sysPermission.getId());
                respNodeVO.setIcon(sysPermission.getIcon());
                respNodeVO.setPerms(sysPermission.getPerms());
                respNodeVO.setType(sysPermission.getType());
                respNodeVO.setStatus(sysPermission.getStatus());
                respNodeVO.setCreateTime(sysPermission.getCreateTime());
                MetaVO metaVO = new MetaVO();
                metaVO.setIcon(sysPermission.getIcon());
                metaVO.setTitle(sysPermission.getName());
                respNodeVO.setMeta(metaVO);
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
            if(s.getPid().equals(id) && !"3".equals(s.getType())){
                PermissionRespNodeVO respNodeVO=new PermissionRespNodeVO();
                respNodeVO.setPath(s.getPath());
                respNodeVO.setComponent(s.getComponent());
                respNodeVO.setName(s.getName());
                respNodeVO.setId(s.getId());
                MetaVO metaVO = new MetaVO();
                metaVO.setIcon(s.getIcon());
                metaVO.setTitle(s.getName());
                respNodeVO.setMeta(metaVO);
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
                respNodeVO.setPath(s.getPath());
                respNodeVO.setComponent(s.getComponent());
                respNodeVO.setName(s.getName());
                respNodeVO.setId(s.getId());
                respNodeVO.setIcon(s.getIcon());
                respNodeVO.setPerms(s.getPerms());
                respNodeVO.setType(s.getType());
                respNodeVO.setStatus(s.getStatus());
                respNodeVO.setCreateTime(s.getCreateTime());
                MetaVO metaVO = new MetaVO();
                metaVO.setIcon(s.getIcon());
                metaVO.setTitle(s.getName());
                respNodeVO.setMeta(metaVO);
                respNodeVO.setChildren(getChild(s.getId(),all));
                list.add(respNodeVO);
            }
        }
        return list;
    }
}
