package com.aijia.mybatis.config.properties;

import lombok.Data;

/**
 * MybatisPlus 属性配置
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
public class SeerMybatisPlusProperties {

    /**
     * 是否开启租户方案
     */
    private Boolean enableTenant = false;

    /**
     * 是否开启逻辑删除
     */
    private Boolean enableLogicDelete = false;
}
