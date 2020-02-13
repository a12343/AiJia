package com.aijia.table.service;

import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 基础表关联列 service
 *
 * @param <T> the type parameter
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public interface BaseTableColumnService<T> {

    /**
     * 保存.
     *
     * @param tableName the table name
     * @param tableId   the table id
     * @param t         the t
     * @return the boolean
     * @author : yangjunqing / 2019-04-17
     */
    Boolean save(@NonNull String tableName, @NonNull String tableId, T t);

    /**
     * 保存.
     *
     * @param tableName   the table name
     * @param tableId     the table id
     * @param tCollection the t collection
     * @return the boolean
     * @author : yangjunqing / 2019-04-17
     */
    Boolean save(@NonNull String tableName, @NonNull String tableId, @NonNull Collection<T> tCollection);

    /**
     * 根据表名和数据编号删除.
     *
     * @param tableName the table name
     * @param id        the id
     * @return the boolean
     * @author : yangjunqing / 2019-04-17
     */
    Boolean deleteByTableNameAndTableId(@NonNull String tableName, @NonNull String id);

    /**
     * 根据表名和数据编号删除.
     *
     * @param tableName the table name
     * @param ids       the ids
     * @return the boolean
     * @author : yangjunqing / 2019-04-17
     */
    Boolean deleteByTableNameAndTableId(@NonNull String tableName, @NonNull Set<String> ids);

    /**
     * 获取指定表指定数据指定列信息.
     *
     * @param tableName  the table name
     * @param tableId    the table id
     * @param columnName the column name
     * @return the t
     * @author : yangjunqing / 2019-04-17
     */
    T getTableColumn(@NonNull String tableName, @NonNull String tableId, @NonNull String columnName);

    /**
     *获取指定表指定数据下列信息.
     *
     * @param tableName the table name
     * @param tableId   the table id
     * @return the list
     * @author : yangjunqing / 2019-04-17
     */
    List<T> findTableColumn(@NonNull String tableName, @NonNull String tableId);
}
