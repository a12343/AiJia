package com.aijia.table.service;

import org.springframework.lang.NonNull;

import java.util.List;

/**
 * 基础列 service
 *
 * @param <T> the type parameter
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public interface BaseColumnService<T> {

    /**
     * 通过表名查询有效的自定义列.
     *
     * @param tableName the table name
     * @return the list
     * @author : yangjunqing / 2019-04-16
     */
    List<T> findColumnsByTableName(@NonNull String tableName);

    /**
     * 通过表名和列名获取列信息.
     *
     * @param tableName  the table name
     * @param columnName the column name
     * @return the t
     * @author : yangjunqing / 2019-04-17
     */
    T getColumnByTableNameAndColumnName(@NonNull String tableName, @NonNull String columnName);

    /**
     * 更新是否必填属性.
     *
     * @param id      the id
     * @param require the require
     * @return the boolean
     * @author : yangjunqing / 2019-04-16
     */
    Boolean updateRequired(@NonNull String id, @NonNull Boolean require);

    /**
     * 更新是否列表显示属性.
     *
     * @param id      the id
     * @param display the display
     * @return the boolean
     * @author : yangjunqing / 2019-04-16
     */
    Boolean updateDisplayed(@NonNull String id, @NonNull Boolean display);

    /**
     * 更新是否启用属性.
     *
     * @param id     the id
     * @param enable the enable
     * @return the boolean
     * @author : yangjunqing / 2019-04-17
     */
    Boolean updateEnabled(@NonNull String id, @NonNull Boolean enable);

    /**
     * 更新序号.
     *
     * @param id       the id
     * @param sequence the sequence
     * @return the boolean
     * @author : yangjunqing / 2019-04-17
     */
    Boolean updateSequence(@NonNull String id, @NonNull Integer sequence);
}
