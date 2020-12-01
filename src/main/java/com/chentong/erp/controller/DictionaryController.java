package com.chentong.erp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chentong.erp.entity.DicDictionary;
import com.chentong.erp.entity.SysNotice;
import com.chentong.erp.service.DictionaryService;
import com.chentong.erp.vo.resp.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Dictionary;
import java.util.List;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/11/11 16:38
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;
    @GetMapping("list")
    public DataResult dictionaryList(DicDictionary dicDictionary){
        DataResult dataResult = DataResult.success();
        IPage<DicDictionary> dictionaryIPage = dictionaryService.dictionaryList(dicDictionary);
        dataResult.setData(dictionaryIPage);
        return dataResult;
    }
    @GetMapping("/{id}")
    public DataResult dictionary(@PathVariable("id") String id){
        DataResult dataResult = DataResult.success();
        DicDictionary dictionary = dictionaryService.dictionary(id);
        dataResult.setData(dictionary);
        return dataResult;
    }
    @PostMapping("")
    public DataResult insertDictionary(@RequestBody DicDictionary dicDictionary){
        DataResult dataResult = DataResult.success();
        dictionaryService.insertDictionary(dicDictionary);
        return dataResult;
    }
    @PutMapping("")
    public DataResult updateDictionary(@RequestBody DicDictionary dicDictionary){
        DataResult dataResult = DataResult.success();
        dictionaryService.updateDictionary(dicDictionary);
        return dataResult;
    }
    @DeleteMapping("/{ids}")
    public DataResult deleteDictionary(@PathVariable("ids") String[] ids){
        DataResult dataResult = DataResult.success();
        dictionaryService.deleteDictionary(ids);
        return dataResult;
    }
    @GetMapping("/dictionary/{name}")
    public DataResult dictionaryByName(@PathVariable("name") String name){
        DataResult dataResult = DataResult.success();
        List<DicDictionary> dictionaryList = dictionaryService.dictionaryByName(name);
        dataResult.setData(dictionaryList);
        return dataResult;
    }
}
