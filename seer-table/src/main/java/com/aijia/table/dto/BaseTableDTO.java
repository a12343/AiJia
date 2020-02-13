package com.aijia.table.dto;


import com.aijia.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础表实体类 dto
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseTableDTO extends BaseDTO {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8885315284081448732L;

    /**
     * 表名
     */
    private String name;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否允许定制
     */
    private Boolean custom;

    /**
     * 版本
     */
    private Long version;
}
