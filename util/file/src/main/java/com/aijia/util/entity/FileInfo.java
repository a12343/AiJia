package com.aijia.util.entity;

import com.yyfly.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 文件
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "imms_file_info")
public class FileInfo extends BaseEntity {
    private static final long serialVersionUID = 555519987449523183L;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件原始名称
     */
    private String fileOriginName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 关联编号
     */
    private String relateId;
}
