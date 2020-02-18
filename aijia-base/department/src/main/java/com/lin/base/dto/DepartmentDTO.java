package com.lin.base.dto;


import com.aijia.util.support.BaseTableColumnDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 部门 DTO
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("部门")
public class DepartmentDTO extends BaseTableColumnDTO {
    private static final long serialVersionUID = 6973208280261796691L;

    /**
     * 父id
     */
    @ApiModelProperty(name = "pid", value = "上一级部门编号")
    private String pid;

    /**
     * 名称
     */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty(name = "note", value = "备注")
    private String note;

    /**
     * 是否启用
     */
    @ApiModelProperty(name = "enabled", value = "是否启用")
    private boolean enabled;
}
