package com.aijia.table.entity;


import com.aijia.entity.JpaBaseEntity;
import com.aijia.table.contant.ColumnType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 基础列实体类
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class JpaColumn extends JpaBaseEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3348831011129179996L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 列名
     */
    private String name;

    /**
     * 别名
     */
    private String lable;

    /**
     * 类型
     */
    private ColumnType type;

    /**
     * 是否必填项
     */
    private Boolean required;

    /**
     * 列表是否显示
     */
    private Boolean displayed;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 长度
     */
    private String length;

    /**
     * 序列
     */
    private Integer sequence;


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
     * Get name string.
     *
     * @return the string
     * @author : yangjunqing / 2019-04-17
     */
    @Column
    public String getName() {
        return name;
    }

    /**
     * Get lable string.
     *
     * @return the string
     * @author : yangjunqing / 2019-04-17
     */
    @Column
    public String getLable() {
        return lable;
    }

    /**
     * Get type column type.
     *
     * @return the column type
     * @author : yangjunqing / 2019-04-17
     */
    @Column
    public ColumnType getType() {
        return type;
    }

    /**
     * Get required boolean.
     *
     * @return the boolean
     * @author : yangjunqing / 2019-04-17
     */
    @Column
    public Boolean getRequired() {
        return required;
    }

    /**
     * Get displayed boolean.
     *
     * @return the boolean
     * @author : yangjunqing / 2019-04-17
     */
    @Column
    public Boolean getDisplayed() {
        return displayed;
    }

    /**
     * Get enabled boolean.
     *
     * @return the boolean
     * @author : yangjunqing / 2019-04-17
     */
    @Column
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * Get default value string.
     *
     * @return the string
     * @author : yangjunqing / 2019-04-17
     */
    @Column
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Get length string.
     *
     * @return the string
     * @author : yangjunqing / 2019-04-17
     */
    @Column
    public String getLength() {
        return length;
    }

    /**
     * Get sequence integer.
     *
     * @return the integer
     * @author : yangjunqing / 2019-04-17
     */
    @Column
    public Integer getSequence() {
        return sequence;
    }
}
