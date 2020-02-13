package com.lin.aijia.entity;

import com.yyfly.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 数据字典
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "imms_dict")
public class Dict extends BaseEntity {
    private static final long serialVersionUID = -5530098292370171570L;

    /**
     * pid
     */
    private String pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 类名
     */
    private String entityName;

    /**
     * 字段名
     */
    private String filed;

    /**
     * Code
     */
    private String code;

    /**
     * Value
     */
    private String value;

    /**
     * 层级
     */
    private int hierarchy;
}
