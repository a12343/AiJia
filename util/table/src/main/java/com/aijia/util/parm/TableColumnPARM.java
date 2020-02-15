package com.aijia.util.parm;


import com.aijia.util.dto.ColumnDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 表关联的列 PARM
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@ApiModel("表关联的列")
public class TableColumnPARM implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6357605899897605057L;

    /**
     * 自定义列名
     */
    @ApiModelProperty(name = "columnName", value = "自定义列名")
    private String columnName;

    /**
     * 值
     */
    @ApiModelProperty(name = "columnValue", value = "值")
    private String columnValue;

    /**
     * 列表是否显示
     */
    @ApiModelProperty(name = "displayed", value = "列表是否显示")
    private Boolean displayed;

    /**
     * 列信息
     */
    @ApiModelProperty(name = "column", value = "列信息")
    private ColumnDTO column;
}
