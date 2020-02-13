package com.aijia.table.parm;


import com.aijia.parm.BasePARM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础表实体类 parm
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseTablePARM extends BasePARM {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5844789153376688544L;

    /**
     * 表名
     */
    private String name;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否允许定制
     */
    private Boolean custom;

    /**
     * 版本
     */
    private Long version;
}
