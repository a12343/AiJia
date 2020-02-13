package com.aijia.table.entity;


import com.aijia.entity.JpaBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 基础表实体类
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class JpaTable extends JpaBaseEntity {
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

    /**
     * Get name string.
     *
     * @return the string
     * @author : yangjunqing / 2019-04-17
     */
    @Column(name = "name", length = 100)
    public String getName() {
        return name;
    }

    /**
     * Get module name string.
     *
     * @return the string
     * @author : yangjunqing / 2019-04-17
     */
    @Column(name = "module_name", length = 32)
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Get description string.
     *
     * @return the string
     * @author : yangjunqing / 2019-04-17
     */
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    /**
     * Get custom boolean.
     *
     * @return the boolean
     * @author : yangjunqing / 2019-04-17
     */
    @Column(name = "custom")
    public Boolean getCustom() {
        return custom;
    }
}
