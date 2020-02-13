package com.aijia.mybatis.support;

import com.aijia.constant.Constants;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * entity填默认充策略(审计)
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public class DefaultMetaObjectHandler implements MetaObjectHandler {

    /**
     * 创建人
     */
    private final String createBy = "createBy";
    /**
     * 创建时间
     */
    private final String createDate = "createDate";
    /**
     * 最后一次更新人
     */
    private final String updateBy = "updateBy";
    /**
     * 最后一次更新时间
     */
    private final String updateDate = "updateDate";
    /**
     * 版本号
     */
    private final String version = "version";
    /**
     * 删除标记
     */
    private final String deleteTag = "deleteTag";

    /**
     * 版本号默认值
     */
    private final static Integer DEFAULT_VERSION = 1;
    /**
     * 删除标记
     */
    private final static Integer DEFAULT_DELETE_TAG = 0;


    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String username = Constants.ANONYMOUS_USER;

        setInsertFieldValByName(createBy, username, metaObject);
        setInsertFieldValByName(createDate, localDateTime, metaObject);
        setInsertFieldValByName(updateBy, username, metaObject);
        setInsertFieldValByName(updateDate, localDateTime, metaObject);
        setInsertFieldValByName(version, DEFAULT_VERSION, metaObject);
        setInsertFieldValByName(deleteTag, DEFAULT_DELETE_TAG, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setUpdateFieldValByName(updateBy, Constants.ANONYMOUS_USER, metaObject);
        setUpdateFieldValByName(updateDate, LocalDateTime.now(), metaObject);
    }
}
