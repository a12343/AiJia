package com.aijia.util.dao;


import com.aijia.repository.JpaBaseRepository;
import com.aijia.util.entity.TableColumn;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 表关联的列 repository
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Repository
public interface TableColumnDao extends JpaBaseRepository<TableColumn> {


    /**
     * 通过表名，数据编号，列名查询信息.
     *
     * @param tableName  the table name
     * @param tableId    the table id
     * @param columnName the column name
     * @return the table column
     * @author : yangjunqing / 2019-04-17
     */
    @Query("select t from TableColumn t where t.tableName = :tableName and t.tableId = :tableId and t.columnName = :columnName and t.deleteTag = 0")
    TableColumn getTableColumn(@Param("tableName") String tableName, @Param("tableId") String tableId, @Param("columnName") String columnName);

    /**
     * 通过表名，数据编号查询信息.
     *
     * @param tableName the table name
     * @param tableId   the table id
     * @return the list
     * @author : yangjunqing / 2019-04-17
     */
    @Query(value =  "select c.name as columnName, " +
                    "       t.name as tableName, " +
                    "       tc.column_value as columnValue, " +
                    "       c.displayed as displayed " +
                    "from imms_table t " +
                    "  inner join imms_column c on c.table_name = t.name and c.enabled = 1 and c.delete_tag = 0 " +
                    "  left join imms_table_column tc on tc.table_name = t.name and tc.column_name = c.name and tc.delete_tag = 0 and tc.table_id = :tableId " +
                    "where t.name = :tableName", nativeQuery = true)
    List<Map<String, Object>> findTableColumn(@Param("tableName") String tableName, @Param("tableId") String tableId);

    /**
     * 通过表名和数据编号删除.
     *
     * @param tableName the table name
     * @param tableId   the table id
     * @return the integer
     * @author : yangjunqing / 2019-04-17
     */
    @Modifying
    @Query("delete from TableColumn t where t.tableName = :tableName and t.tableId = :tableId and t.deleteTag = 0")
    Integer deleteByTableNameAndDataId(@Param("tableName") String tableName, @Param("tableId") String tableId);

    /**
     * 通过表名和数据编号删除.
     *
     * @param tableName the table name
     * @param tableIds  the table ids
     * @return the integer
     * @author : yangjunqing / 2019-04-17
     */
    @Modifying
    @Query("delete from TableColumn t where t.tableName = :tableName and t.tableId in :tableIds and t.deleteTag = 0")
    Integer deleteByTableNameAndDataIds(@Param("tableName") String tableName, @Param("tableIds") Set<String> tableIds);
}
