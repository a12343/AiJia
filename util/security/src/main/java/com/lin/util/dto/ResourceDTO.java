package com.lin.util.dto;


import com.lin.util.entity.Resource;
import com.yyfly.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * 资源 Dto
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("资源")
public class ResourceDTO extends BaseDTO {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8474800623699114063L;

    /**
     * 资源名称
     */
    @ApiModelProperty(name = "resourceName", value = "资源名称")
    private String resourceName;

    /**
     * path
     */
    @ApiModelProperty(name = "path", value = "path")
    private String path;

    /**
     * 资源类型
     * <ul>
     * <li> 1 - 菜单</li>
     * <li> 2 - 功能</li>
     * </ul>
     */
    @ApiModelProperty(name = "type", value = "资源类型")
    private String type;

    /**
     * 资源类型名称
     */
    @ApiModelProperty(name = "typeName", value = "资源类型名称")
    private String typeName;

    /**
     * 权限标识
     */
    @ApiModelProperty(name = "permission", value = "权限标识")
    private String permission;

    /**
     * 上级
     */
    @ApiModelProperty(name = "pid", value = "上级")
    private String pid;

    /**
     * 备注
     */
    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    /**
     * 是否启用
     */
    @ApiModelProperty(name = "enabled", value = "是否启用")
    private boolean enabled;

    /**
     * Get type name string.
     *
     * @return the string
     * @author : yangjunqing / 2019-01-19
     */
    public String getTypeName() {
        switch (StringUtils.trimToEmpty(type)){
            case Resource.TYPE_NONE:
                return "无";
            case Resource.TYPE_MENU:
                return "菜单";
            case Resource.TYPE_FUNCTION:
                return "功能";
            default:
                return "无";
        }
    }
}
