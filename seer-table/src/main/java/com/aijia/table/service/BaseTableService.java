package com.aijia.table.service;

import org.springframework.lang.NonNull;

/**
 * 基础表 service
 *
 * @param <T> the type parameter
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public interface BaseTableService<T> {

    /**
     * 启用自定义列功能.
     *
     * @param name the name
     * @return the boolean
     * @author : yangjunqing / 2019-04-16
     */
    Boolean enableCustomization(@NonNull String name);

    /**
     * 关闭自定义列功能.
     *
     * @param name the name
     * @return the boolean
     * @author : yangjunqing / 2019-04-16
     */
    Boolean disableCustomization(@NonNull String name);

    /**
     * 通过表名查询表.
     *
     * @param name the name
     * @return the t
     * @author : yangjunqing / 2019-04-16
     */
    T getTableByName(@NonNull String name);
}
