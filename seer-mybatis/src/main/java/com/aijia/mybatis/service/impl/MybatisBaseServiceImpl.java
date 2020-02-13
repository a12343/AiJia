package com.aijia.mybatis.service.impl;

import com.aijia.mybatis.mapper.MybatisBaseMapper;
import com.aijia.mybatis.search.MybatisListData;
import com.aijia.mybatis.search.MybatisSearchCriteria;
import com.aijia.mybatis.service.MybatisBaseService;
import com.aijia.search.BaseSearchCriteria;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * mybatis 基础 service impl
 * @param <T> the type parameter
 * @param <M> the type parameter
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Slf4j
public class MybatisBaseServiceImpl<T, P, D, M extends MybatisBaseMapper<T>> extends ServiceImpl<M, T> implements MybatisBaseService<T, P, D> {


    @Override
    public MybatisListData page(MybatisSearchCriteria searchCriteria) {
        IPage<T> page;

        if (null == searchCriteria.getPaging() || searchCriteria.getPaging()){
            page = searchCriteria.buildQuery(query()).page(searchCriteria.buildPage());
        }
        else {
            page = searchCriteria.buildQuery(query())
                    .orderByAsc(BaseSearchCriteria.Order.asc == searchCriteria.getOrder(), searchCriteria.getField())
                    .orderByDesc(BaseSearchCriteria.Order.desc == searchCriteria.getOrder(), searchCriteria.getField())
                    .page(new Page<>(1L, -1L));
            page.setSize(page.getRecords().size());
        }

        return MybatisListData.of(page);
    }


    @Override
    public D entityToDTO(T t, Class<D> clazz) {
        D d = null;

        try {
            d = clazz.newInstance();
            BeanUtils.copyProperties(t, d);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return d;
    }

    @Override
    public T dtoToEntity(D d, Class<T> clazz) {
        T t = null;

        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(d, t);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return t;
    }

    @Override
    public List<D> entityToDTO(List<T> tList, Class<D> clazz) {
        List<D> dList = Lists.newLinkedList();

        if (null != tList && tList.size() > 0){
            tList.forEach(t -> {
                D d = entityToDTO(t, clazz);
                dList.add(d);
            });
        }
        return dList;
    }

    @Override
    public List<T> dtoToEntity(List<D> dList, Class<T> clazz) {
        List<T> tList = Lists.newArrayList();

        if (null != dList && dList.size() > 0){
            dList.forEach(d -> {
                T t = dtoToEntity(d, clazz);
                tList.add(t);
            });
        }
        return tList;
    }

    @Override
    public T parmToEntity(P d, Class<T> clazz) {
        T t = null;

        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(d, t);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return t;
    }

    @Override
    public List<T> parmToEntity(List<P> pList, Class<T> clazz) {
        List<T> tList = Lists.newArrayList();

        if (null != pList && pList.size() > 0){
            pList.forEach(p -> {
                T t = parmToEntity(p, clazz);
                tList.add(t);
            });
        }
        return tList;
    }

    @Override
    public P entityToPARM(T t, Class<P> clazz) {
        P p = null;

        try {
            p = clazz.newInstance();
            BeanUtils.copyProperties(t, p);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return p;
    }

    @Override
    public List<P> entityToPARM(List<T> tList, Class<P> clazz) {
        List<P> pList = Lists.newLinkedList();

        if (null != tList && tList.size() > 0){
            tList.forEach(t -> {
                P p = entityToPARM(t, clazz);
                pList.add(p);
            });
        }
        return pList;
    }
}
