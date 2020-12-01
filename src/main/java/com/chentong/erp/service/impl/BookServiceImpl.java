package com.chentong.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chentong.erp.dao.BokBookDao;
import com.chentong.erp.entity.BokBook;
import com.chentong.erp.service.BokBookService;
import com.chentong.erp.vo.req.BookQueryVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/11/16 10:18
 */
@Service
public class BookServiceImpl implements BokBookService {
    @Autowired
    private BokBookDao bookDao;

    @Override
    public IPage<BokBook> bookList(BookQueryVO bookQueryVO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(bookQueryVO.getName()),"name",bookQueryVO.getName())
                .like(StringUtils.isNotBlank(bookQueryVO.getAuthor()),"author",bookQueryVO.getAuthor())
                .eq(StringUtils.isNotBlank(bookQueryVO.getType()),"type",bookQueryVO.getType());
        Page<BokBook> bokBookPage = new Page<>(bookQueryVO.getPageNum(),bookQueryVO.getPageSize());
        IPage page = bookDao.selectPage(bokBookPage, queryWrapper);
        return page;
    }

    @Override
    public BokBook findById(String id) {
        BokBook bokBook = bookDao.selectById(id);
        return bokBook;
    }

    @Override
    public void add(BokBook bokBook) {
        bookDao.insert(bokBook);
    }

    @Override
    public void modify(BokBook bokBook) {
        bookDao.updateById(bokBook);
    }

    @Override
    public void removeById(String[] ids) {
        bookDao.deleteBatchIds(Arrays.asList(ids));
    }
}
