package com.chentong.erp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chentong.erp.entity.BokBook;
import com.chentong.erp.vo.req.BookQueryVO;

/**
 * (BokBook)表服务接口
 *
 * @author Admin
 * @since 2020-11-16 10:26:18
 */
public interface BokBookService {
    /**
     * 多条件查询
     */
    IPage<BokBook> bookList(BookQueryVO bookQueryVO);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BokBook findById(String id);

    /**
     * 新增数据
     *
     * @param bokBook 实例对象
     * @return 实例对象
     */
    void add(BokBook bokBook);

    /**
     * 修改数据
     *
     * @param bokBook 实例对象
     * @return 实例对象
     */
    void modify(BokBook bokBook);

    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    void removeById(String[] ids);

}