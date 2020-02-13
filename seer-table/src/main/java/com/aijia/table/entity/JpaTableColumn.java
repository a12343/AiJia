package com.aijia.table.entity;


import com.aijia.entity.JpaBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 基础表关联列中间表
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class JpaTableColumn extends JpaBaseEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -9101412793649038037L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表数据编号
     */
    private String tableId;

    /**
     * 自定义列名
     */
    private String columnName;

    /**
     * 列值
     */
    private String columnValue;


    /**
     * Get table name string.
     *
     * @return the string
     * @author : yangjunqing / 2019-04-17
     */
    @Column
    public String getTableName() {
        return tableName;
    }

    /**
     * Get table id string.
     *
     * @return the string
     * @author : yangjunqing / 2019-04-17
     */
    @Column
    public String getTableId() {
        return tableId;
    }

    /**
     * Get column name string.
     *
     * @return the string
     * @author : yangjunqing / 2019-04-17
     */
    @Column
    public String getColumnName() {
        return columnName;
    }

    /**
     * Get column value string.
     *
     * @return the string
     * @author : yangjunqing / 2019-04-17
     */
    @Column
    public String getColumnValue() {
        return columnValue;
    }
}
