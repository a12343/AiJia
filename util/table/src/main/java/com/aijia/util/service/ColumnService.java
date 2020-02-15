package com.aijia.util.service;


import com.aijia.service.JpaBaseService;
import com.aijia.table.service.BaseColumnService;
import com.aijia.util.dto.ColumnDTO;
import com.aijia.util.entity.Column;
import com.aijia.util.parm.ColumnPARM;
import com.aijia.util.parm.ColumnSequencePARM;
import org.springframework.lang.NonNull;

import java.util.Set;

/**
 * 列 service
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public interface ColumnService extends JpaBaseService<Column, ColumnPARM, ColumnDTO>, BaseColumnService<Column> {

    /**
     * 根据表名和列名查询符合数量.
     *
     * @param tableName  the table name
     * @param columnName the column name
     * @return the boolean
     * @author : yangjunqing / 2019-04-17
     */
    Boolean existsByName(@NonNull String tableName, @NonNull String columnName);

    /**
     * 更新排序号.
     *
     * @param parms the parms
     * @return the boolean
     * @author : yangjunqing / 2019-04-17
     */
    Boolean updateSequence(@NonNull Set<ColumnSequencePARM> parms);
}
