package com.aijia.mybatis.entity;

import com.aijia.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * mybatis 基础实体
 * @author : lin
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MybatisBaseEntity extends BaseEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4742332930298382106L;

    /**
     * 唯一编号
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 最后一次更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 最后一次更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateDate;

    /**
     * 乐观锁
     */
    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;

    /**
     * 租户编号
     */
    private String tenantId;

    /**
     * 删除标志
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteTag;
}
