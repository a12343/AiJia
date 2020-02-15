package com.aijia.util.entity;


import com.aijia.table.entity.JpaColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 列
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "imms_column")
public class Column extends JpaColumn {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6665477276014041688L;
}
