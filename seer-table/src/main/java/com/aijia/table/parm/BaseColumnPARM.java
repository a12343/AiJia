package com.aijia.table.parm;


import com.aijia.parm.BasePARM;
import com.aijia.table.contant.ColumnType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础列实体类 parm
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseColumnPARM extends BasePARM {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2667107128537394517L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 列名
     */
    private String name;

    /**
     * 别名
     */
    private String lable;

    /**
     * 类型
     */
    private ColumnType type;

    /**
     * 是否必填项
     */
    private Boolean required;

    /**
     * 列表是否显示
     */
    private Boolean displayed;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 长度
     */
    private String length;

    /**
     * 序列
     */
    private Integer sequence;

    /**
     * 版本
     */
    private Long version;
}
