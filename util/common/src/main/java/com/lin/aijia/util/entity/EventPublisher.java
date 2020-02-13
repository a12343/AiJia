package com.lin.aijia.util.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 事件发布
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Component
public class EventPublisher {

    @Autowired
    private ApplicationContext applicationContext;


    /**
     * 发布事件.
     *
     * @param source the source
     * @author : yangjunqing / 2019-02-20
     */
    public void publish(Object source){
        applicationContext.publishEvent(new Event(source));
    }

    /**
     * 发布事件.
     *
     * @param source the source
     * @param param  the param
     * @author : yangjunqing / 2019-02-20
     */
    public void publish(Object source, Object param){
        applicationContext.publishEvent(new Event(source, param));
    }

    /**
     * 发布事件.
     *
     * @param source the source
     * @param params the params
     * @author : yangjunqing / 2019-02-20
     */
    public void publish(Object source, Map<String, Object> params){
        applicationContext.publishEvent(new Event(source, params));
    }
}
