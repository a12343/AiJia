package com.aijia.util.support.annotation;

import java.lang.annotation.*;

/**
 * 实体信息
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableEntity {

    /**
     * 实体信息.
     *
     * @return the class
     * @author : yangjunqing / 2019-04-17
     */
    Class entity();
}
