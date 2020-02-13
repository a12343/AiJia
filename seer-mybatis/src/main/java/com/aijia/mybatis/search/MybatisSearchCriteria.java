package com.aijia.mybatis.search;

import com.aijia.search.BaseSearchCriteria;
import com.aijia.search.SearchParam;
import com.aijia.utils.LocalDateUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * 查询对象
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public class MybatisSearchCriteria extends BaseSearchCriteria {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8630867858577408179L;


    /**
     * 范围分隔符
     */
    private final static String IN_SPLIT = ",";


    /**
     * 构建分页条件.
     *
     * @param <T> the type parameter
     * @return the page
     * @author : yangjunqing / 2019-04-15
     */
    public <T> Page<T> buildPage(){
        Page<T> page;

        page = new Page<>(getPage(), getSize());

        //是否排序
        if (!Strings.isNullOrEmpty(getField()) && null != getOrder()){
            if (Order.asc == getOrder()){
                page.setAsc(getField());
            }
            if (Order.desc == getOrder()){
                page.setDesc(getField());
            }
        }

        return page;
    }


    /**
     * 构建查询条件.
     *
     * @param <T>   the type parameter
     * @param query the query
     * @return the query chain wrapper
     * @author : yangjunqing / 2019-04-08
     */
    public <T> QueryChainWrapper<T> buildQuery(QueryChainWrapper<T> query){
        Set<SearchParam> searchParams = getSearchParams();
        if (null != searchParams && searchParams.size() > 0){
            searchParams.forEach(searchParam -> {
                if (!Strings.isNullOrEmpty(searchParam.getFieldName())){
                    switch (searchParam.getOperator()){
                        //等于
                        case eq:
                            if (!Strings.isNullOrEmpty(searchParam.getValue())){
                                query.eq(searchParam.getFieldName(), searchParam.getValue());
                            }
                            break;

                        //不等于
                        case ne:
                            if (!Strings.isNullOrEmpty(searchParam.getValue())) {
                                query.ne(searchParam.getFieldName(), searchParam.getValue());
                            }
                            break;

                        //大于
                        case gt:
                            if (!Strings.isNullOrEmpty(searchParam.getValue())){
                                query.gt(searchParam.getFieldName(), searchParam.getValue());
                            }
                            break;

                        //大于等于
                        case ge:
                            if (!Strings.isNullOrEmpty(searchParam.getValue())){
                                query.ge(searchParam.getFieldName(), searchParam.getValue());
                            }
                            break;

                        //小于
                        case lt:
                            if (!Strings.isNullOrEmpty(searchParam.getValue())){
                                query.lt(searchParam.getFieldName(), searchParam.getValue());
                            }
                            break;

                        //小于等于
                        case le:
                            if (!Strings.isNullOrEmpty(searchParam.getValue())){
                                query.le(searchParam.getFieldName(), searchParam.getValue());
                            }
                            break;

                        //区间(仅日期查询使用)
                        case between:
                            if (!Strings.isNullOrEmpty(searchParam.getValue())){
                                String startDate = searchParam.getValue();
                                query.apply("date_format("+ searchParam.getFieldName() +",'"+ LocalDateUtils.getMySqlDateFormatByTime(startDate) +"') >= {0}", startDate);
                            }

                            if (!Strings.isNullOrEmpty(searchParam.getValue1())){
                                String endDate = searchParam.getValue1();
                                query.apply("date_format("+ searchParam.getFieldName() +",'"+ LocalDateUtils.getMySqlDateFormatByTime(endDate) +"') <= {0}", endDate);
                            }
                            break;

                        //区间(仅日期查询使用)
                        case notBetween:
                            if (!Strings.isNullOrEmpty(searchParam.getValue())){
                                String startDate = searchParam.getValue();
                                query.apply("date_format("+ searchParam.getFieldName() +",'"+ LocalDateUtils.getMySqlDateFormatByTime(startDate) +"') <= {0}", startDate);
                            }

                            if (!Strings.isNullOrEmpty(searchParam.getValue1())){
                                String endDate = searchParam.getValue1();
                                query.apply("date_format("+ searchParam.getFieldName() +",'"+ LocalDateUtils.getMySqlDateFormatByTime(endDate) +"') >= {0}", endDate);
                            }
                            break;

                        //模糊查询(值左右两边模糊匹配查询)
                        case like:
                            if (!Strings.isNullOrEmpty(searchParam.getValue())){
                                query.like(searchParam.getFieldName(), searchParam.getValue());
                            }
                            break;

                        //模糊查询取不符合条件
                        case notLike:
                            if (!Strings.isNullOrEmpty(searchParam.getValue())){
                                query.notLike(searchParam.getFieldName(), searchParam.getValue());
                            }
                            break;

                        //模糊查询(值左边模糊匹配查询)
                        case likeLeft:
                            if (!Strings.isNullOrEmpty(searchParam.getValue())){
                                query.likeLeft(searchParam.getFieldName(), searchParam.getValue());
                            }
                            break;

                        //模糊查询(值右边模糊匹配查询)
                        case likeRight:
                            if (!Strings.isNullOrEmpty(searchParam.getValue())){
                                query.likeRight(searchParam.getFieldName(), searchParam.getValue());
                            }
                            break;

                        //指定范围查询(值以','隔开,；例如 id1,id2,id3...)
                        case in:
                            if (!Strings.isNullOrEmpty(searchParam.getValue())){
                                query.in(searchParam.getFieldName(), Lists.newArrayList(StringUtils.split(searchParam.getValue(), IN_SPLIT)));
                            }
                            break;

                        //指定范围查询(值以','隔开,；例如 id1,id2,id3...)取不符合条件
                        case notIn:
                            if (!Strings.isNullOrEmpty(searchParam.getValue())){
                                query.notIn(searchParam.getFieldName(), searchParam.getFieldName(), Lists.newArrayList(StringUtils.split(searchParam.getValue(), IN_SPLIT)));
                            }
                            break;

                        default:
                            break;
                    }
                }
            });
        }

        return query;
    }
}
