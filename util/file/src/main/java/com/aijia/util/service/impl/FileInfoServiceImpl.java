package com.aijia.util.service.impl;


import com.aijia.util.dao.FileInfoDao;
import com.aijia.util.dto.FileInfoDTO;
import com.aijia.util.entity.FileInfo;
import com.aijia.util.properties.FileManageProperties;
import com.aijia.util.service.FileInfoService;

import com.lin.aijia.util.entity.ListData;
import com.lin.aijia.util.entity.SearchCriteria;
import com.lin.aijia.util.utils.QRcodeUtils;
import com.lin.aijia.util.utils.ToolUtils;
import com.lin.util.utils.SecurityUtils;
import com.yyfly.common.exception.GlobalException;
import com.yyfly.common.repository.BaseRepository;
import com.yyfly.common.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件 service impl
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Service
public class FileInfoServiceImpl extends BaseServiceImpl<FileInfo> implements FileInfoService {

    @Autowired
    private FileInfoDao fileInfoDao;
    @Autowired
    private FileManageProperties fileManageProperties;


    @Override
    protected BaseRepository<FileInfo> getDao() {
        return fileInfoDao;
    }


    @Override
    public ListData<FileInfo> findPageList(SearchCriteria searchCriteria) {
        return ListData.of(findAll(searchCriteria.buildSearchParams(), searchCriteria.buildPageRequest()));
    }


    @Override
    public void updateRelateId(List<String> ids, String relateId) {
        if (ids != null && ids.size() > 0){
            ids.forEach(id -> {
                fileInfoDao.updateRelateId(id, relateId);
            });
        }
    }

    @Override
    public List<FileInfo> findByRelateId(String relateId) {
        return fileInfoDao.findByRelateId(relateId);
    }

    @Override
    public List<String> findIdsByRelateId(String relateId) {
        return fileInfoDao.findIdsByRelateId(relateId);
    }

    @Override
    public List<FileInfo> findInvalidByLimitDay(int day) {
        return fileInfoDao.findInvalidByLimitDay(day);
    }

    @Override
    public FileInfo uploadFile(MultipartFile multipartFile, String path, String relateId) {
        return uploadFile(multipartFile, path, relateId, false);
    }

    @Override
    public FileInfo uploadFile(MultipartFile multipartFile, String path, String relateId, boolean delete) {
        FileInfo fileInfo = getFileInfo(multipartFile, path, relateId);

        //保存文件到指定目录
        try {
            File file = getFile(fileInfo);
            transferTo(multipartFile, file);
        } catch (IOException e) {
            throw new GlobalException("文件发生错误 :" + fileInfo.getFilePath());
        }

        //是否删除旧文件
        if (delete && StringUtils.isNotEmpty(relateId)){
            List<FileInfo> oldFileInfos= findByRelateId(relateId);
            deleteList(oldFileInfos);
        }

        //将文件信息保存到到数据库
        fileInfo = save(fileInfo);
        return fileInfo;
    }

    @Override
    public List<FileInfo> uploadFile(List<MultipartFile> multipartFiles, String path, String relateId) {
        if (multipartFiles == null && multipartFiles.size() == 0){
            throw new GlobalException("文件不能为空!");
        }

        List<FileInfo> fileInfos = new ArrayList<>(multipartFiles.size());
        multipartFiles.forEach(multipartFile -> {
            fileInfos.add(uploadFile(multipartFile, path, relateId));
        });
        return fileInfos;
    }

    @Override
    public FileInfo uploadHeadPicture(MultipartFile multipartFile) {
        String userName = SecurityUtils.getUserName();
        return uploadFile(multipartFile, "/headPicture", userName, true);
    }

    @Override
    public FileInfo generateQRCode(String relateId, String content) {
        File file;
        String path = getUploadPath() + "/qrcode";
        String absoluteFilePath = ToolUtils.getProjectPath() + path;
        try {
            file = QRcodeUtils.generate(content, fileManageProperties.getQrCodeLogoPath(), absoluteFilePath);
        } catch (Exception e) {
            throw new GlobalException("生成二维码失败!");
        }

        //先删除旧的二维码
        List<FileInfo> oldFileInfos= findByRelateId(relateId);
        if (oldFileInfos != null && oldFileInfos.size() > 0){
            deleteList(oldFileInfos);
        }

        //保存新的
        FileInfo fileInfo = new FileInfo();
        fileInfo.setRelateId(relateId);
        fileInfo.setFileName(file.getName());
        fileInfo.setFileOriginName(file.getName());
        fileInfo.setSize(file.length());
        fileInfo.setFileType("image/*");
        fileInfo.setFilePath(path + "/" + file.getName());
        return fileInfoDao.save(fileInfo);
    }

    @Override
    public void deleteList(List<FileInfo> fileInfos) {
        //删除文件
        deleteFile(fileInfos);
        //删除记录
        deleteAll(fileInfos);
    }

    @Override
    public void preview(String id, HttpServletResponse response) {
        File file;
        FileInfo fileInfo = findById(id);

        if (fileInfo == null){
            throw new GlobalException("文件不存在!");
        }
        try {
            file = getFile(fileInfo);
            preview(file, fileInfo, response);
        } catch (IOException e) {
            throw new GlobalException("文件不存在!");
        }
    }

    @Override
    public void headPicture(String username, HttpServletResponse response) {
        File file;
        List<FileInfo> uploads = findByRelateId(username);
        if (uploads != null && uploads.size() > 0){
            FileInfo fileInfo = uploads.get(0);
            try {
                file = getFile(fileInfo);
                preview(file, fileInfo, response);
            } catch (IOException e) {
                throw new GlobalException("头像不存在!");
            }
        }
        else {
            String filaPath = fileManageProperties.getDefaultHeadPicture();
            Resource resource = new ClassPathResource("static" + filaPath);
            try {
                FileCopyUtils.copy(resource.getInputStream(), response.getOutputStream());
            } catch (IOException e) {
                throw new GlobalException("默认头像不存在!");
            }
        }
    }

    @Override
    public void download(String id, HttpServletResponse response) {
        File file;
        FileInfo fileInfo = findById(id);

        if (fileInfo == null){
            throw new GlobalException("文件不存在!");
        }
        try {
            file = getFile(fileInfo);
            response.setHeader("Content-Type", fileInfo.getFileType());
            response.setHeader("Content-Disposition", "attachment;filename=" + fileInfo.getFileName());
            response.setHeader("Content-Length", String.valueOf(fileInfo.getSize()));
            FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
        } catch (IOException e) {
            throw new GlobalException("请稍后重试!");
        }
    }


    /**
     * 创建一个Upload实体类
     *
     * @param multipartFile the multipart file
     * @param path          the path
     * @param relateId      the relate id
     * @return the upload
     * @author : yangjunqing / 2019-01-16
     */
    private FileInfo getFileInfo(MultipartFile multipartFile, String path, String relateId){
        if (multipartFile.getSize() == 0){
            throw new GlobalException("文件不能为空!");
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setRelateId(relateId);
        fileInfo.setFileName(genFileName(multipartFile));
        fileInfo.setFileOriginName(multipartFile.getOriginalFilename());
        fileInfo.setSize(multipartFile.getSize());
        fileInfo.setFileType(multipartFile.getContentType());

        if (StringUtils.isEmpty(path)){
            fileInfo.setFilePath(getUploadPath() + "/" + fileInfo.getFileName());
        }else {
            fileInfo.setFilePath(getUploadPath() + path + "/" + fileInfo.getFileName());
        }
        return fileInfo;
    }

    /**
     * 获取文件
     *
     * @param fileInfo the fileInfo
     * @return the file
     * @throws IOException the io exception
     * @author : yangjunqing / 2019-01-16
     */
    private File getFile(FileInfo fileInfo) throws IOException {
        String filePath = fileInfo.getFilePath();
        String absoluteFilePath = ToolUtils.getProjectPath() + filePath;
        File file = new File(absoluteFilePath);
        if (!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return file;
    }

    /**
     * 预览
     *
     * @param file     the file
     * @param fileInfo the fileInfo
     * @param response the response
     * @throws IOException the io exception
     * @author : yangjunqing / 2019-01-17
     */
    private void preview(File file, FileInfo fileInfo, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", fileInfo.getFileType());
        response.setHeader("Content-Disposition", "inline;filename=" + fileInfo.getFileName());
        response.setHeader("Content-Length", String.valueOf(fileInfo.getSize()));
        FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
    }

    /**
     * 保存file
     *
     * @param multipartFile the multipart file
     * @param file          the file
     * @throws IOException the io exception
     * @author : yangjunqing / 2019-01-16
     */
    private void transferTo(MultipartFile multipartFile, File file) throws IOException {
        FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
    }


    /**
     * 删除文件
     *
     * @param fileInfos the fileInfos
     * @author : yangjunqing / 2019-01-16
     */
    private void deleteFile(List<FileInfo> fileInfos){
        if (fileInfos != null && fileInfos.size() > 0){
            fileInfos.forEach(fileInfo -> {
                try {
                    File file = getFile(fileInfo);
                    if (file.exists()){
                        file.delete();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    /**
     * 创建一个唯一文件名
     *
     * @param multipartFile the multipart file
     * @return the string
     * @author : yangjunqing / 2019-01-16
     */
    private String genFileName(MultipartFile multipartFile){
        String suffix = ToolUtils.getFileSuffix(multipartFile.getOriginalFilename());
        String prefix = ToolUtils.getRandomName();
        return  prefix + suffix;
    }

    /**
     * 获取文件相对路径
     *
     * @return the string
     * @author : yangjunqing / 2019-01-16
     */
    private String getUploadPath(){
        return fileManageProperties.getFileUploadPath();
    }


    @Override
    public FileInfoDTO toDTO(FileInfo fileInfo) {
        if (fileInfo == null){
            return null;
        }

        FileInfoDTO dto = new FileInfoDTO();
        BeanUtils.copyProperties(fileInfo, dto);
        return dto;
    }

    @Override
    public FileInfo toEntity(FileInfoDTO dto) {
        FileInfo fileInfo = new FileInfo();
        BeanUtils.copyProperties(dto, fileInfo);
        return fileInfo;
    }

    @Override
    public List<FileInfoDTO> toDTOs(List<FileInfo> fileInfos) {
        List<FileInfoDTO> dtos = new ArrayList<>();
        if (fileInfos != null && fileInfos.size() > 0){
            fileInfos.forEach(fileInfo -> {
                FileInfoDTO dto = toDTO(fileInfo);
                dtos.add(dto);
            });
        }
        return dtos;
    }

    @Override
    public List<FileInfo> toEntitys(List<FileInfoDTO> fileInfoDTOs) {
        List<FileInfo> fileInfos = new ArrayList<>();
        if (fileInfoDTOs != null && fileInfoDTOs.size() > 0){
            fileInfoDTOs.forEach(fileInfoDTO -> {
                FileInfo fileInfo = toEntity(fileInfoDTO);
                fileInfos.add(fileInfo);
            });
        }
        return fileInfos;
    }
}
