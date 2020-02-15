package com.lin.util.entity;

import com.yyfly.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "imms_role")
public class Role extends BaseEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 710246654760750363L;

    /**
     * 数据字典(groupCode)
     */
    public static final String DICT_GROUP  = "groupCode";

    /**
     * 管理组
     */
    public static final String GROUP_MANAGERCODE = "0";
    /**
     * 维修组
     */
    public static final String GROUP_MAINTAIN_CODE = "1";
    /**
     * 生产组
     */
    public static final String GROUP_PRODUCE_CODE = "2";

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色组编码
     */
    private String groupCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 资源
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "imms_t_role_resource",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id")}
    )
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id ASC")
    private Set<Resource> resources;


    /**
     * Get resource ids set.
     *
     * @return the set
     * @author : yangjunqing / 2019-01-15
     */
    public Set<String> getResourceIds(){
        Set<String> resourceIds = new HashSet<>();
        if (resources != null && resources.size() > 0){
            for (Resource resource : resources){
                resourceIds.add(resource.getId());
            }
        }
        return resourceIds;
    }

    /**
     * Get resource names set.
     *
     * @return the set
     * @author : yangjunqing / 2019-01-19
     */
    public Set<String> getResourceNames(){
        Set<String> resourceNames = new HashSet<>();
        if (resources != null && resources.size() > 0){
            for (Resource resource : resources){
                resourceNames.add(resource.getResourceName());
            }
        }
        return resourceNames;
    }

    /**
     * Get permissions set.
     *
     * @return the set
     * @author : yangjunqing / 2019-01-15
     */
    public Set<String> getPermissions(){
        Set<String> permissions = null;
        if (resources != null && resources.size() > 0){
            permissions = new HashSet<>(resources.size());
            for (Resource resource : resources){
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }
}
