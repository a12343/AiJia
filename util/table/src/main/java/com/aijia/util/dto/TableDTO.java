package com.aijia.util.dto;


import com.aijia.table.dto.BaseTableDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 自定义表 DTO
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("自定义表")
public class TableDTO extends BaseTableDTO {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4359889896871599652L;
}
