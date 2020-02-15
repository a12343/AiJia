package com.aijia.util.support.aspect;


import com.aijia.util.service.TableColumnService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义表切面
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Component
@Aspect
@Slf4j
public class TableColumnAspect {

    /**
     * Table column service
     */
    @Autowired
    private TableColumnService tableColumnService;

    /**
     * 切点.
     *
     * @author : yangjunqing / 2019-04-17
     */
    @Pointcut("@annotation(com.seerbigdata.util.support.annotation.TableColumn)")
    public void TableColumnPoincut(){ }
}
