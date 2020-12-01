package com.chentong.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chentong.erp.dao.DicDictionaryDao;
import com.chentong.erp.entity.DicDictionary;
import com.chentong.erp.service.DictionaryService;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.Arrays;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/11/11 16:55
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DicDictionaryDao dicDictionaryDao;
    @Override
    public IPage<DicDictionary> dictionaryList(DicDictionary dictionary) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(dictionary.getName()),"name",dictionary.getName())
                    .eq(StringUtils.isNotBlank(dictionary.getDicKey()),"dic_key",dictionary.getDicKey())
                    .eq(StringUtils.isNotBlank(dictionary.getDivValue()),"div_value",dictionary.getDivValue());
        Page<DicDictionary> page = new Page<>(dictionary.getPageNum(), dictionary.getPageSize());
        Page page1 = dicDictionaryDao.selectPage(page, queryWrapper);
        return page1;
    }

    @Override
    public DicDictionary dictionary(String id) {
        DicDictionary dicDictionary = dicDictionaryDao.selectById(id);
        return dicDictionary;
    }

    @Override
    public void insertDictionary(DicDictionary dicDictionary) {
        dicDictionary.setCreateTime(new Date());
        dicDictionaryDao.insert(dicDictionary);
    }

    @Override
    public void deleteDictionary(String[] ids) {
        dicDictionaryDao.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public void updateDictionary(DicDictionary dicDictionary) {
        dicDictionaryDao.updateById(dicDictionary);
    }

    @Override
    public List<DicDictionary> dictionaryByName(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(StringUtils.isNotBlank(name),"name",name);
        List<DicDictionary> list = dicDictionaryDao.selectList(queryWrapper);
        return list;
    }
}
