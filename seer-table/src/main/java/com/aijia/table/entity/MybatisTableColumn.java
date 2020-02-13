package com.aijia.table.entity;


import com.aijia.mybatis.entity.MybatisBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础表关联列中间表
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MybatisTableColumn extends MybatisBaseEntity {
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
}
