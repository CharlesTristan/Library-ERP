package com.chentong.erp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chentong.erp.entity.BokBook;
import com.chentong.erp.service.BokBookService;
import com.chentong.erp.vo.req.BookQueryVO;
import com.chentong.erp.vo.resp.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/11/16 10:16
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BokBookService bookService;

    @GetMapping("/list")
    public DataResult dictionaryList(BookQueryVO bookQueryVO){
        DataResult dataResult = DataResult.success();
        IPage<BokBook> bokBookIPage = bookService.bookList(bookQueryVO);
        dataResult.setData(bokBookIPage);
        return dataResult;
    }
    @GetMapping("/{id}")
    public DataResult dictionary(@PathVariable("id") String id){
        DataResult dataResult = DataResult.success();
        BokBook byId = bookService.findById(id);
        dataResult.setData(byId);
        return dataResult;
    }
    @PostMapping("")
    public DataResult insertDictionary(@RequestBody BokBook bokBook){
        DataResult dataResult = DataResult.success();
        bookService.add(bokBook);
        return dataResult;
    }
    @PutMapping("")
    public DataResult updateDictionary(@RequestBody BokBook bokBook){
        DataResult dataResult = DataResult.success();
        bookService.modify(bokBook);
        return dataResult;
    }
    @DeleteMapping("/{ids}")
    public DataResult deleteDictionary(@PathVariable("ids") String[] ids){
        DataResult dataResult = DataResult.success();
        bookService.removeById(ids);
        return dataResult;
    }
}
