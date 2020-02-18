package com.lin.util.service;



import com.lin.util.dto.RoleDTO;
import com.lin.util.entity.DTOConvert;
import com.lin.util.entity.ListData;
import com.lin.util.entity.Role;
import com.lin.util.entity.SearchCriteria;
import com.yyfly.common.service.BaseService;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Set;

/**
 * 角色 service
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public interface RoleService extends BaseService<Role>, DTOConvert<Role, RoleDTO> {

    /**
     * Find page list list data.
     *
     * @param searchCriteria the search criteria
     * @return the list data
     * @author : yangjunqing / 2019-01-08
     */
    ListData<Role> findPageList(SearchCriteria searchCriteria);

    /**
     * 角色名称是否存在.
     *
     * @param roleName the role name
     * @return the boolean
     * @author : yangjunqing / 2019-02-16
     */
    boolean existsByRoleName(String roleName);

    /**
     * 角色名称是否存在(忽略id).
     *
     * @param roleName the role name
     * @param id       the id
     * @return the boolean
     * @author : yangjunqing / 2019-02-20
     */
    boolean existsByRoleNameWithOutId(String roleName, String id);

    /**
     * 批量更新状态.
     *
     * @param ids    the ids
     * @param status the status
     * @return the boolean
     * @author : yangjunqing / 2019-03-15
     */
    boolean updateStatusByIds(Set<String> ids, int status);


    /**
     * 通过用户名查询角色编号.
     *
     * @param username the username
     * @return the list
     * @author : yangjunqing / 2019-03-18
     */
    List<String> findRoleIdsByUsername(@NonNull String username);
}
