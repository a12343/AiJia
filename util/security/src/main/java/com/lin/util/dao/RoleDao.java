package com.lin.util.dao;


import com.lin.util.entity.Role;
import com.yyfly.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 角色 repository
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Repository
public interface RoleDao extends BaseRepository<Role> {


    /**
     * 通过用户名查询角色编号.
     *
     * @param username the username
     * @return the list
     * @author : yangjunqing / 2019-03-18
     */
    @Query(value = "select r.id " +
                    " from imms_user u " +
                    "  left join imms_t_user_role ur on u.id = ur.user_id " +
                    "  left join imms_role r on r.id = ur.role_id " +
                    "where u.status <> 9999 " +
                    " and r.status <> 9999 " +
                    " and u.username = :username", nativeQuery = true)
    List<String> findRoleIdsByUsername(@Param("username") String username);

    /**
     * 角色名称是否存在.
     *
     * @param roleName the role name
     * @return the boolean
     * @author : yangjunqing / 2019-02-16
     */
    @Query(value = "select count(1) from imms_role r where r.role_name = :roleName and r.status <> 9999", nativeQuery = true)
    int countByRoleName(String roleName);

    /**
     * 角色名称是否存在(忽略id).
     *
     * @param roleName the role name
     * @param id       the id
     * @return the boolean
     * @author : yangjunqing / 2019-02-20
     */
    @Query(value = "select count(1) from imms_role r where r.role_name = :roleName and r.id <> :id and r.status <> 9999", nativeQuery = true)
    int countByRoleNameWithOutId(@Param("roleName") String roleName, @Param("id") String id);

    /**
     * 批量更新状态.
     *
     * @param ids    the ids
     * @param status the status
     * @return the integer
     * @author : yangjunqing / 2019-03-15
     */
    @Modifying
    @Query("update Role r set r.status = :status where r.id in :ids")
    Integer updateStatusByIds(@Param("ids") Set<String> ids, @Param("status") int status);
}
