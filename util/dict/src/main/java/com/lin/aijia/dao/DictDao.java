package com.lin.aijia.dao;


import com.lin.aijia.entity.Dict;
import com.yyfly.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据字典 repository
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Repository
public interface DictDao extends BaseRepository<Dict> {

    /**
     * 通过类名，字段名查询字典信息.
     *
     * @param entityName the entity name
     * @param filed      the filed
     * @return the list
     * @author : yangjunqing / 2019-02-15
     */
    @Query("select d from Dict d where d.entityName = :entityName and d.filed = :filed and d.status <> 9999")
    List<Dict> findByEntityNameAndFiled(@Param("entityName") String entityName, @Param("filed") String filed);

    /**
     * 通过类名,字段名,code查询字典信息.
     *
     * @param entityName the entity name
     * @param filed      the filed
     * @param code       the code
     * @return the dict
     * @author : yangjunqing / 2019-02-15
     */
    @Query("select d from Dict d where d.entityName = :entityName and d.filed = :filed and d.code = :code and d.status <> 9999")
    Dict findByEntityNameAndFiledAndCode(@Param("entityName") String entityName, @Param("filed") String filed, @Param("code") String code);

    /**
     * 通过id获取层级值.
     *
     * @param id the id
     * @return the int
     * @author : yangjunqing / 2019-02-20
     */
    @Query("select d.hierarchy from Dict d where d.id = :id and d.status <> 9999")
    int getHierarchyById(@Param("id") String id);

    /**
     * 通过类名,字段名,code查询value.
     *
     * @param entityName the entity name
     * @param filed      the filed
     * @param code       the code
     * @return the string
     * @author : yangjunqing / 2019-02-16
     */
    @Query("select d.value from Dict d where d.entityName = :entityName and d.filed = :filed and d.code = :code and d.status <> 9999")
    String getValueByEntityNameAndFiledAndCode(@Param("entityName") String entityName, @Param("filed") String filed, @Param("code") String code);
}
