package com.lin.util.dao;


import com.lin.util.entity.Resource;
import com.yyfly.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 资源 repository
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
//id默认给我传的就是string了
@Repository
public interface ResourceDao extends BaseRepository<Resource> {

    /**
     * 通过用户名查询菜单权限.
     *
     * @param username the username
     * @return the list
     * @author : yangjunqing / 2019-03-18
     */
    @Query(value = "select rs.* " +
                " from imms_user u " +
                "  left join imms_t_user_role ur on u.id = ur.user_id " +
                "  left join imms_role r on r.id = ur.role_id " +
                "  left join imms_t_role_resource rr on rr.role_id = r.id " +
                "  left join imms_resource rs on rs.id = rr.resource_id " +
                "where u.status <> 9999 " +
                " and r.status <> 9999 " +
                " and rs.status <> 9999 " +
                " and rs.type = '1' " +
                " and u.username = :username", nativeQuery = true)
    Set<Resource> findMenuByUsername(@Param("username") String username);

    /**
     * 根据用户和菜单编号查询功能资源.
     *
     * @param pid the pid
     * @return the list
     * @author : yangjunqing / 2019-03-18
     */
    @Query(value = "select rs.* " +
            " from imms_user u " +
            "  left join imms_t_user_role ur on u.id = ur.user_id " +
            "  left join imms_role r on r.id = ur.role_id " +
            "  left join imms_t_role_resource rr on rr.role_id = r.id " +
            "  left join imms_resource rs on rs.id = rr.resource_id " +
            " where u.status <> 9999 " +
            " and r.status <> 9999 " +
            " and rs.status <> 9999 " +
            " and rs.type = '2' " +
            " and rs.pid = :pid " +
            " and u.username = :username", nativeQuery = true)
    List<Resource> findPermissionByPidAndUsername(@Param("pid") String pid, @Param("username") String username);


}
