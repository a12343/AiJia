package com.aijia.util.dao;

import com.aijia.util.entity.FileInfo;
import com.yyfly.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件 repository
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Repository
public interface FileInfoDao extends BaseRepository<FileInfo> {


    /**
     * 通过关联编号查询文件.
     *
     * @param relateId the relate id
     * @return the list
     * @author : yangjunqing / 2019-02-14
     */
    @Query("select f from FileInfo f where f.relateId = :relateId and f.status <> 9999")
    List<FileInfo> findByRelateId(@Param("relateId") String relateId);

    /**
     * 通过关联编号查询文件id.
     *
     * @param relateId the relate id
     * @return the list
     * @author : yangjunqing / 2019-02-14
     */
    @Query("select f.id from FileInfo f where f.relateId = :relateId and f.status <> 9999")
    List<String> findIdsByRelateId(@Param("relateId") String relateId);

    /**
     * 更新关联编号.
     *
     * @param id       the id
     * @param relateId the relate id
     * @author : yangjunqing / 2019-02-14
     */
    @Modifying
    @Query("update FileInfo f set f.relateId = :relateId where f.id = :id and f.status <> 9999")
    void updateRelateId(@Param("id") String id, @Param("relateId") String relateId);

    /**
     * 查询限定天数外无效的文件.
     *
     * @param day the day
     * @return the list
     * @author : yangjunqing / 2019-02-14
     */
    @Query(value = "select f.* " +
            " from imms_file_info f " +
            "where TRIM(IFNULL(f.relate_id, '')) = '' " +
            " and TO_DAYS(NOW()) - TO_DAYS(f.update_date) >= :day", nativeQuery = true)
    List<FileInfo> findInvalidByLimitDay(@Param("day") int day);
}
