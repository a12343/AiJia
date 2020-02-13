package com.lin.aijia.dto;

import com.yyfly.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 数据字典 DTO
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("数据字典")
public class DictDTO extends BaseDTO {
    private static final long serialVersionUID = -8126535923592752274L;

    /**
     * pid
     */
    @ApiModelProperty(name = "pid", value = "上一级")
    private String pid;

    /**
     * 名称
     */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    /**
     * 类名
     */
    @ApiModelProperty(name = "entityName", value = "类名")
    private String entityName;

    /**
     * 字段名
     */
    @ApiModelProperty(name = "filed", value = "字段名")
    private String filed;

    /**
     * Code
     */
    @ApiModelProperty(name = "code", value = "编码")
    private String code;

    /**
     * 值
     */
    @ApiModelProperty(name = "value", value = "值")
    private String value;

    /**
     * 层级
     */
    @ApiModelProperty(name = "hierarchy", value = "层级")
    private int hierarchy;
}
