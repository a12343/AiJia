package com.aijia.util.parm;


import com.aijia.table.parm.BaseTablePARM;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 自定义表 PARM
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("自定义表")
public class TablePARM extends BaseTablePARM {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4359889896871599652L;
}
