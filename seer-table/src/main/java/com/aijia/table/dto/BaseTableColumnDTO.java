package com.aijia.table.dto;


import com.aijia.dto.BaseDTO;

/**
 * 基础表关联列中间表 dto
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public class BaseTableColumnDTO extends BaseDTO {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7049032826231252366L;

    /**
     * 自定义列名
     */
    private String columnName;

    /**
     * 列值
     */
    private String columnValue;

    /**
     * 版本
     */
    private Long version;
}
