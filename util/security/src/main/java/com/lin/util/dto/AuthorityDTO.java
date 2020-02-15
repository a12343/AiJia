package com.lin.util.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 权限 DTO
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ApiModel("权限DTO")
public class AuthorityDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -97540456654027710L;

    /**
     * 菜单
     */
    @ApiModelProperty(name = "menu", value = "菜单")
    private String menu;

    /**
     * 功能权限
     */
    @ApiModelProperty(name = "permission", value = "功能权限")
    private Set<String> permission;
}
