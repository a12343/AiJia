package com.aijia.util.dao;

import com.aijia.repository.JpaBaseRepository;
import com.aijia.util.entity.Table;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 自定义表 repository
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Repository
public interface TableDao extends JpaBaseRepository<Table> {

    /**
     * 根据表名查询表信息.
     *
     * @param name the name
     * @return the table
     * @author : yangjunqing / 2019-04-17
     */
    @Query("select t from Table t where t.name = :name and t.deleteTag = 0")
    Table getByName(@Param("name") String name);

    /**
     * 根据表名查询符合数量.
     *
     * @param name the name
     * @return the integer
     * @author : yangjunqing / 2019-04-17
     */
    @Query(value = "select count(t.name) from imms_table t where t.name = :name and t.delete_tag = 0", nativeQuery = true)
    Integer countByName(@Param("name") String name);

    /**
     * 更新是否允许自定义属性.
     *
     * @param name   the name
     * @param custom the custom
     * @return the table
     * @author : yangjunqing / 2019-04-17
     */
    @Modifying
    @Query("update Table t set t.custom = :custom where t.name = :name and t.deleteTag = 0")
    Integer updateCustomization(@Param("name") String name, @Param("custom") Boolean custom);
}
