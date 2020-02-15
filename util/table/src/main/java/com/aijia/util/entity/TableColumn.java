package com.aijia.util.entity;


import com.aijia.table.entity.JpaTableColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 表关联的列
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "imms_table_column")
public class TableColumn extends JpaTableColumn {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7145164876331070519L;

    /**
     * 列表是否显示
     */
    private Boolean displayed;


    /**
     * 获取列表是否显示.
     *
     * @return the boolean
     * @author : yangjunqing / 2019-04-18
     */
    @Transient
    public Boolean getDisplayed() {
        return displayed;
    }
}
