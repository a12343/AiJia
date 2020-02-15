package com.aijia.util.dao;


import com.aijia.repository.JpaBaseRepository;


import com.aijia.util.entity.Column;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 列 repository
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Repository
public interface ColumnDao extends JpaBaseRepository<Column> {


    /**
     * 通过表名查询有效的自定义列.
     *
     * @param tableName the table name
     * @return the list
     * @author : yangjunqing / 2019-04-17
     */
    @Query("select c from Column c where c.tableName = :tableName and c.enabled = 1 and c.deleteTag = 0")
    List<Column> findByTableName(@Param("tableName") String tableName);

    /**
     * 通过表名和列名获取列信息.
     *
     * @param tableName  the table name
     * @param columnName the column name
     * @return the column
     * @author : yangjunqing / 2019-04-17
     */
    @Query("select c from Column c where c.tableName = :tableName and c.name = :columnName and c.enabled = 1 and c.deleteTag = 0")
    Column getByTableNameAndName(@Param("tableName") String tableName, @Param("columnName") String columnName);

    /**
     * 根据表名和列名查询符合数量.
     *
     * @param tableName  the table name
     * @param columnName the column name
     * @return the integer
     * @author : yangjunqing / 2019-04-17
     */
    @Query(value = "select count(c.name) from imms_column c where c.table_name = :tableName and c.name = :columnName and c.enabled = 1 and c.delete_tag = 0", nativeQuery = true)
    Integer countByTableNameAndName(@Param("tableName") String tableName, @Param("columnName") String columnName);

    /**
     * 更新是否必填属性.
     *
     * @param id      the id
     * @param require the require
     * @return the integer
     * @author : yangjunqing / 2019-04-17
     */
    @Modifying
    @Query("update Column c set c.required = :require where c.id = :id and c.deleteTag = 0")
    Integer updateRequired(@Param("id") String id, @Param("require") Boolean require);

    /**
     * 更新是否列表显示属性.
     *
     * @param id      the id
     * @param display the display
     * @return the integer
     * @author : yangjunqing / 2019-04-17
     */
    @Modifying
    @Query("update Column c set c.displayed = :display where c.id = :id and c.deleteTag = 0")
    Integer updateDisplayed(@Param("id") String id, @Param("display") Boolean display);

    /**
     * 更新是否启用属性.
     *
     * @param id     the id
     * @param enable the enable
     * @return the integer
     * @author : yangjunqing / 2019-04-17
     */
    @Modifying
    @Query("update Column c set c.enabled = :enable where c.id = :id and c.deleteTag = 0")
    Integer updateEnabled(@Param("id") String id, @Param("enable") Boolean enable);

    /**
     * 更新序号.
     *
     * @param id       the id
     * @param sequence the sequence
     * @return the integer
     * @author : yangjunqing / 2019-04-17
     */
    @Modifying
    @Query("update Column c set c.sequence = :sequence where c.id = :id and c.deleteTag = 0")
    Integer updateSequence(@Param("id") String id, @Param("sequence") Integer sequence);
}
