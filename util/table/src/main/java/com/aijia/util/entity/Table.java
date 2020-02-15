package com.aijia.util.entity;


import com.aijia.table.entity.JpaTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * 自定义表
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@javax.persistence.Table(name = "imms_table")
public class Table extends JpaTable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 493975656502717907L;
}
