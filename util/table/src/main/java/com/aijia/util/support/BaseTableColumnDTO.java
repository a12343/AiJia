package com.aijia.util.support;


import com.aijia.util.parm.TableColumnPARM;
import com.yyfly.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 基础自定义列 DTO
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("基础自定义列")
public class BaseTableColumnDTO extends BaseDTO {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -327622396978079987L;

    /**
     * 自定义列
     */
    @ApiModelProperty(name = "tableColumns", value = "自定义列")
    private List<TableColumnPARM> tableColumns;
}
