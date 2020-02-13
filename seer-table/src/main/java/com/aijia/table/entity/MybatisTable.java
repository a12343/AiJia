package com.aijia.table.entity;


import com.aijia.mybatis.entity.MybatisBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础表实体类
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MybatisTable extends MybatisBaseEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5101050855031851886L;

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
}
