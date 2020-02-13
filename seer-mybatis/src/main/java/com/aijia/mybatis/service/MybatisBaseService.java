package com.aijia.mybatis.service;

import com.aijia.mybatis.search.MybatisListData;
import com.aijia.mybatis.search.MybatisSearchCriteria;
import com.aijia.mybatis.support.LambdaDeleteWrapperChain;
import com.aijia.support.DTOConvert;
import com.aijia.support.PARMConvert;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * mybatis 基础 service
 *
 * @param <T> the type parameter
 * @param <P> the type parameter
 * @param <D> the type parameter
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public interface MybatisBaseService<T, P, D> extends IService<T>, PARMConvert<T, P>, DTOConvert<T, D> {

    /**
     * 分页查询.
     *
     * @param searchCriteria the search criteria
     * @return the mybatis list data
     * @author : yangjunqing / 2019-04-15
     */
    MybatisListData page(MybatisSearchCriteria searchCriteria);

    /**
     * Delete lambda delete wrapper chain.
     *
     * @return the lambda delete wrapper chain
     * @author : yangjunqing / 2019-04-23
     */
    default LambdaDeleteWrapperChain<T, P, D> delete() {
        return new LambdaDeleteWrapperChain<>(this);
    }
}
