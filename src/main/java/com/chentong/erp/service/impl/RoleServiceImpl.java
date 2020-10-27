package com.chentong.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chentong.erp.dao.SysRoleDao;
import com.chentong.erp.entity.SysRole;
import com.chentong.erp.service.RoleService;
import com.chentong.erp.vo.req.RoleQueryVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/12 15:15
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public List<String> getRoleByUserId(String id) {
        List<String> roleByUserId = sysRoleDao.getRoleByUserId(id);
        return roleByUserId;
    }

    @Override
    public IPage roleList(RoleQueryVO roleQueryVO) {
        if(roleQueryVO.getPageNum()==null && roleQueryVO.getPageSize()==null){
            roleQueryVO.setPageNum(1);
            roleQueryVO.setPageSize(20);
        }
        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.like(StringUtils.isNotBlank(roleQueryVO.getName()),"name",roleQueryVO.getName())
                .eq(StringUtils.isNotBlank(roleQueryVO.getStatus()),"status",roleQueryVO.getStatus())
                .ge(roleQueryVO.getBeginTime()!=null,"createTime",roleQueryVO.getBeginTime())
                .le(roleQueryVO.getEndTime()!=null,"createTime",roleQueryVO.getEndTime());
        Page<SysRole> page = new Page<>(roleQueryVO.getPageNum(), roleQueryVO.getPageSize());
        IPage iPage = sysRoleDao.selectPage(page, queryWrapper);
        return iPage;
    }
}
