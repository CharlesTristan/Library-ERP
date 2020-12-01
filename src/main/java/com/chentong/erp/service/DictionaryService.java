package com.chentong.erp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chentong.erp.dao.DicDictionaryDao;
import com.chentong.erp.entity.DicDictionary;

import java.util.Dictionary;
import java.util.List;

public interface DictionaryService {
    IPage<DicDictionary> dictionaryList(DicDictionary dictionary);
    DicDictionary dictionary(String id);
    void insertDictionary(DicDictionary dicDictionary);
    void deleteDictionary(String[] ids);
    void updateDictionary(DicDictionary dicDictionary);
    List<DicDictionary> dictionaryByName(String name);
}
