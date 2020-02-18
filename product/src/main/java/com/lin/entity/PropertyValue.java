package com.lin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lin
 * @since 2020-02-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PropertyValue implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String propertyValue;

    private String propertyId;

    private LocalDateTime createDate;

    private String createBy;

    private LocalDateTime updateDate;

    private String updateBy;

    private Integer status;

    private Long version;


}
