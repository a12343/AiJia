package com.aijia.util.service;

import com.aijia.util.dto.FileInfoDTO;
import com.aijia.util.entity.FileInfo;
import com.lin.aijia.util.entity.DTOConvert;
import com.lin.aijia.util.entity.ListData;
import com.lin.aijia.util.entity.SearchCriteria;
import com.yyfly.common.service.BaseService;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件 service
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public interface FileInfoService extends BaseService<FileInfo>, DTOConvert<FileInfo, FileInfoDTO> {

    /**
     * Find page list list data.
     *
     * @param searchCriteria the search criteria
     * @return the list data
     * @author : yangjunqing / 2019-02-14
     */
    ListData<FileInfo> findPageList(SearchCriteria searchCriteria);

    /**
     * 更新关联编号
     *
     * @param ids      the ids
     * @param relateId the relate id
     * @author : yangjunqing / 2019-01-18
     */
    void updateRelateId(List<String> ids, String relateId);

    /**
     * 通过关联编号查询文件.
     *
     * @param relateId the relate id
     * @return the list
     * @author : yangjunqing / 2019-02-14
     */
    List<FileInfo> findByRelateId(String relateId);

    /**
     * 通过关联编号查询文件id.
     *
     * @param relateId the relate id
     * @return the list
     * @author : yangjunqing / 2019-02-14
     */
    List<String> findIdsByRelateId(String relateId);

    /**
     * 查询限定天数外无效的文件.
     *
     * @param day the day
     * @return the list
     * @author : yangjunqing / 2019-01-30
     */
    List<FileInfo> findInvalidByLimitDay(int day);

    /**
     * 上传单个文件(不删除旧文件)
     *
     * @param multipartFile the multipart file
     * @param path          the path
     * @param relateId      the relate id
     * @return the upload
     * @author : yangjunqing / 2019-01-16
     */
    FileInfo uploadFile(MultipartFile multipartFile, @Nullable String path, @Nullable String relateId);


    /**
     * 上传单个文件
     *
     * @param multipartFile the multipart file
     * @param path          the path
     * @param relateId      the relate id
     * @param delete        the delete true -> 删除旧文件
     * @return the upload
     * @author : yangjunqing / 2019-01-16
     */
    FileInfo uploadFile(MultipartFile multipartFile, @Nullable String path, @Nullable String relateId, boolean delete);


    /**
     * 上传多个文件(不删除旧文件)
     *
     * @param multipartFiles the multipart files
     * @param path           the path
     * @param relateId       the relate id
     * @return the list
     * @author : yangjunqing / 2019-01-16
     */
    List<FileInfo> uploadFile(List<MultipartFile> multipartFiles, @Nullable String path, @Nullable String relateId);


    /**
     * 上传头像.
     *
     * @param multipartFile the multipart file
     * @return the upload
     * @author : yangjunqing / 2019-01-17
     */
    FileInfo uploadHeadPicture(MultipartFile multipartFile);

    /**
     * 生成二维码.
     *
     * @param relateId the relate id
     * @param content  the content
     * @return the file info
     * @author : yangjunqing / 2019-02-21
     */
    FileInfo generateQRCode(String relateId, String content);

    /**
     * 删除
     *
     * @param fileInfos the fileInfos
     * @author : yangjunqing / 2019-01-17
     */
    void deleteList(List<FileInfo> fileInfos);

    /**
     * 预览文件.
     *
     * @param id       the id
     * @param response the response
     * @author : yangjunqing / 2019-01-17
     */
    void preview(String id, HttpServletResponse response);

    /**
     * 获取头像
     *
     * @param username the username
     * @param response the response
     * @author : yangjunqing / 2019-01-17
     */
    void headPicture(String username, HttpServletResponse response);

    /**
     * 下载文件.
     *
     * @param id       the id
     * @param response the response
     * @author : yangjunqing / 2019-01-17
     */
    void download(String id, HttpServletResponse response);
  }
