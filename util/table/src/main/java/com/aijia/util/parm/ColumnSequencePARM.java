package com.aijia.util.parm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 列序列入参
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ApiModel("列序列参数")
public class ColumnSequencePARM implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7662047436136240195L;

    /**
     * 列编号
     */
    @ApiModelProperty(name = "id", value = "列编号")
    private String id;

    /**
     * 序列
     */
    @ApiModelProperty(name = "sequence", value = "序列")
    private Integer sequence;
}
