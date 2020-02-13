package com.aijia.table.parm;


import com.aijia.parm.BasePARM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础表关联列中间表 PARM
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseTableColumnPARM extends BasePARM {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8006221463387073821L;

    /**
     * 自定义列名
     */
    private String columnName;

    /**
     * 列值
     */
    private String columnValue;

    /**
     * 版本
     */
    private Long version;
}
