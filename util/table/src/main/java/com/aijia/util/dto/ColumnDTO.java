package com.aijia.util.dto;


import com.aijia.table.dto.BaseColumnDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 列 DTO
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("列")
public class ColumnDTO extends BaseColumnDTO {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2288489018526093830L;
}
