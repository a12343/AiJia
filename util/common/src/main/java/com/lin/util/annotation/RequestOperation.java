package com.lin.util.annotation;



import com.lin.util.constant.Operation;

import java.lang.annotation.*;

/**
 * 请求操作注解
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestOperation {

    /**
     * 备注.
     *
     * @return the string
     * @author : yangjunqing / 2019-03-09
     */
    String note() default "";

    /**
     * 工单操作.
     *
     * @return the action . work order action
     * @author : yangjunqing / 2019-03-09
     */
    Operation.WorkOrderOperate workOrderOperate() default Operation.WorkOrderOperate.NONE;

    /**
     * 维修单操作.
     *
     * @return the action . maintain order action
     * @author : yangjunqing / 2019-03-09
     */
    Operation.MaintainOrderOperate maintainOrderOperate() default Operation.MaintainOrderOperate.NONE;

    /**
     * 评论操作.
     *
     * @return the operation . comment operate
     * @author : yangjunqing / 2019-03-20
     */
    Operation.CommentOperate commentOperate() default Operation.CommentOperate.NONE;
}
