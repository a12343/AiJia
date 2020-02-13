package com.lin.aijia.util.entity;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * 事件
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
public class Event extends ApplicationEvent {

    /**
     * Params
     */
    Map<String, Object> params;

    /**
     * Param
     */
    Object param;

    /**
     * Event.
     *
     * @param source the source
     */
    public Event(Object source) {
        super(source);
    }

    /**
     * Event.
     *
     * @param source the source
     * @param param  the param
     */
    public Event(Object source, Object param) {
        super(source);
        this.param = param;
    }

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     * @param params the params
     */
    public Event(Object source, Map<String, Object> params) {
        super(source);
        this.params = params;
    }
}
