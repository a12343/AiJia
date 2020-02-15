package com.aijia.util.dto;


import com.aijia.table.dto.BaseTableColumnDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 表关联的列 DTO
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("表关联的列")
public class TableColumnDTO extends BaseTableColumnDTO {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6357605899897605057L;

    /**
     * 列表是否显示
     */
    @ApiModelProperty(name = "displayed", value = "列表是否显示")
    private Boolean displayed;
}
