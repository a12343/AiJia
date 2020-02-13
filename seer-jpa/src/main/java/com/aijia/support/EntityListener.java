package com.aijia.support;

import com.aijia.constant.Constants;
import com.aijia.entity.BaseEntity;
import com.google.common.base.Strings;


import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * 实体监听类
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public class EntityListener {

    /**
     * 实体执行save前执行的方法.
     *
     * @param entity the entity
     * @author : yangjunqing / 2019-04-15
     */
    @PrePersist
    public void prePersist(BaseEntity entity){
        if (Strings.isNullOrEmpty(entity.getId())){
            entity.setDeleteTag(Constants.UNDELETE_TAG);
        }
    }

    /**
     * 实体执行更新前执行的方法.
     *
     * @param entity the entity
     * @author : yangjunqing / 2019-04-16
     */
    @PreUpdate
    public void preUpdate(BaseEntity entity){
        if (null == entity.getDeleteTag()){
            entity.setDeleteTag(Constants.UNDELETE_TAG);
        }
    }
}
