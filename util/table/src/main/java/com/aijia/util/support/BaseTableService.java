package com.aijia.util.support;


import com.aijia.util.entity.TableColumn;
import com.aijia.util.service.TableColumnService;
import com.yyfly.common.service.BaseService;

import java.util.List;
import java.util.Set;

/**
 * @param <T> the type parameter
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public interface BaseTableService<T> extends BaseService<T> {

    /**
     * 获取TableColumnService.
     *
     * @return the table column service
     * @author : yangjunqing / 2019-04-17
     */
    TableColumnService getTableColumnService();

    /**
     * 获取实体数据库表名.
     *
     * @return the string
     * @author : yangjunqing / 2019-04-17
     */
    String getEntityTableName();

    /**
     * 保存.
     *
     * @param t            the t
     * @param tableColumns the table columns
     * @return the t
     * @author : yangjunqing / 2019-04-17
     */
    T save(T t, List<TableColumn> tableColumns);

    /**
     * 删除.
     *
     * @param ids the ids
     * @author : yangjunqing / 2019-04-17
     */
    void deleteByIds(Set<String> ids);
}
