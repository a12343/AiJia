package com.aijia.mybatis.search;

import com.aijia.search.BaseListData;
import com.baomidou.mybatisplus.core.metadata.IPage;


/**
 * 分页数据
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public class MybatisListData extends BaseListData {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6462050228907141182L;

    /**
     * 第一页
     */
    private final long FIRST_PAGE = 1;


    /**
     * Page
     */
    protected IPage page;

    /**
     * Mybatis list data.
     *
     * @param page the page
     */
    public MybatisListData(IPage page) {
        this.page = page;
    }

    @Override
    public long getPage() {
        return page.getCurrent();
    }

    @Override
    public long getSize() {
        return page.getSize();
    }

    @Override
    public long getTotalPages() {
        return page.getPages();
    }

    @Override
    public long getTotalElements() {
        return page.getTotal();
    }

    @Override
    public Boolean getHasPrevious() {
        return FIRST_PAGE == page.getCurrent() ? false : true;
    }

    @Override
    public Boolean getHasNext() {
        return page.getCurrent() < page.getPages();
    }

    @Override
    public Boolean isFirst() {
        return FIRST_PAGE == page.getCurrent();
    }

    @Override
    public Boolean isLast() {
        return page.getCurrent() == page.getPages();
    }

    /**
     * Of mybatis list data.
     *
     * @param page the page
     * @return the mybatis list data
     * @author : yangjunqing / 2019-04-15
     */
    public static MybatisListData of(IPage page){
        MybatisListData listData = new MybatisListData(page);
        listData.setData(page.getRecords());
        return listData;
    }
}
