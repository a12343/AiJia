package com.aijia.util.support.annotation;

import java.lang.annotation.*;

/**
 * 自定义表操作信息
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableColumn {

    /**
     * 实体类class.
     *
     * @return the class
     * @author : yangjunqing / 2019-04-17
     */
    Class entity() default Object.class;

    /**
     * 类型.
     *
     * @return the type
     * @author : yangjunqing / 2019-04-17
     */
    type type();


    /**
     * 类型
     *
     * @author : yangjunqing / yangjunqing@seerbigdata.com
     * @version : 1.0
     */
    enum type{

        /**
         * 写.
         */
        wirte,

        /**
         * 删除.
         */
        delete
    }
}
