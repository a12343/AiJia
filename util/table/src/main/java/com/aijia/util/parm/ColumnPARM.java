package com.aijia.util.parm;


import com.aijia.table.parm.BaseColumnPARM;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 列 PARM
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("列")
public class ColumnPARM extends BaseColumnPARM {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2288489018526093830L;
}
