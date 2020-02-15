package com.aijia.util.support;


import com.aijia.util.entity.TableColumn;
import com.yyfly.common.entity.BaseEntity;
import com.yyfly.common.service.impl.BaseServiceImpl;

import java.util.List;
import java.util.Set;

/**
 * @param <T> the type parameter
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public abstract class BaseTableServiceImpl<T extends BaseEntity> extends BaseServiceImpl<T> implements BaseTableService<T> {

    @Override
    public T save(T t, List<TableColumn> tableColumns) {
        //保存实体信息
        t = save(t);
        //保存自定义表
        getTableColumnService().save(getEntityTableName(), t.getId(), tableColumns);
        return t;
    }

    @Override
    public void deleteByIds(Set<String> ids) {
        //删除实体数据
        List<T> tList = findAll(ids);
        deleteAll(tList);
        //删除实体对应的自定义数据
        getTableColumnService().deleteByTableNameAndTableId(getEntityTableName(), ids);
    }
}
