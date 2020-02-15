package com.aijia.util.dto;

import com.yyfly.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 文件 DTO
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("文件")
public class FileInfoDTO extends BaseDTO {
    private static final long serialVersionUID = 7988449918155567365L;

    /**
     * 文件名
     */
    @ApiModelProperty(name = "fileName", value = "文件名")
    private String fileName;

    /**
     * 文件原始名称
     */
    @ApiModelProperty(name = "fileOriginName", value = "文件原始名称")
    private String fileOriginName;

    /**
     * 文件类型
     */
    @ApiModelProperty(name = "fileType", value = "文件类型")
    private String fileType;

    /**
     * 文件路径
     */
    @ApiModelProperty(name = "filePath", value = "文件路径")
    private String filePath;

    /**
     * 文件大小
     */
    @ApiModelProperty(name = "size", value = "文件大小")
    private Long size;

    /**
     * 关联编号
     */
    @ApiModelProperty(name = "relateId", value = "关联编号")
    private String relateId;
}
