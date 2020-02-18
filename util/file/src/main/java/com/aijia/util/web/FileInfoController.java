package com.aijia.util.web;


import com.aijia.util.dto.FileInfoDTO;
import com.aijia.util.entity.FileInfo;
import com.aijia.util.service.FileInfoService;

import com.lin.util.entity.ApiVersionHeader;
import com.lin.util.entity.ListData;
import com.lin.util.entity.SearchCriteria;
import com.yyfly.common.entity.ResponseData;
import com.yyfly.common.exception.GlobalException;
import com.yyfly.common.web.BaseController;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 文件 controller
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@RestController
@RequestMapping(value = "fileInfo")
@Api(value = "文件管理", tags = "文件管理API")
@ApiResponses({
    @ApiResponse(code = -1, message = "不符合条件"),
    @ApiResponse(code = 200, message = "成功"),
    @ApiResponse(code = 401, message = "未授权"),
    @ApiResponse(code = 403, message = "拒绝访问"),
    @ApiResponse(code = 404, message = "请求未找到"),
    @ApiResponse(code = 406, message = "token过期或者token无效"),
    @ApiResponse(code = 500, message = "服务器出错"),
})
public class FileInfoController extends BaseController {

    /**
     * FileInfo service
     */
    @Autowired
    private FileInfoService fileInfoService;


    /**
     * 上传文件
     *
     * @param relateId the relate id
     * @param path     the path
     * @param file     the file
     * @return the response data
     * @author : yangjunqing / 2019-01-17
     */
    @ApiOperation(value = "上传文件(单个)", notes = "上传文件(单个)API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "api-version", value = "api-version", paramType = "header", required = true, dataType = "string", defaultValue = "V1"),
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
            @ApiImplicitParam(name = "relateId", value = "关联编号", required = false, dataType = "string"),
            @ApiImplicitParam(name = "path", value = "路径(建议传此参数(参考路径: 设备->/equipment, 工单->/workOrder)", required = false, dataType = "string"),
            @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "MultipartFile")
    })
    @PostMapping(value = "file", headers = {ApiVersionHeader.V1}, consumes = "multipart/form-data")
    public ResponseData upload(@RequestParam(value = "relateId", required = false) @ApiIgnore String relateId,
                               @RequestParam(value = "path", defaultValue = "/file", required = false) @ApiIgnore String path,
                               MultipartFile file){
        FileInfo fileInfo = fileInfoService.uploadFile(file, path, relateId);
        return ResponseData.success(fileInfoService.toDTO(fileInfo));
    }


    /**
     * 上传文件(多个).
     *
     * @param relateId the relate id
     * @param path     the path
     * @param files    the files
     * @return the response data
     * @author : yangjunqing / 2019-01-18
     */
    @ApiOperation(value = "上传文件(多个)", notes = "上传文件(多个)API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "api-version", value = "api-version", paramType = "header", required = true, dataType = "string", defaultValue = "V1"),
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
            @ApiImplicitParam(name = "relateId", value = "关联编号", required = true, dataType = "string"),
            @ApiImplicitParam(name = "path", value = "路径(建议传此参数(参考路径: 设备->/equipment, 工单->/workOrder)", required = false, dataType = "string"),
            @ApiImplicitParam(name = "files", value = "文件数组", required = true, dataType = "MultipartFile", allowMultiple = true)
    })
    @PostMapping(value = "files", headers = {ApiVersionHeader.V1}, consumes = "multipart/form-data")
    public ResponseData upload(@RequestParam(value = "relateId", required = false) @ApiIgnore String relateId,
                               @RequestParam(value = "path", defaultValue = "/file", required = false) @ApiIgnore String path,
                               MultipartFile[] files){
        if (files == null || files.length == 0){
            throw new GlobalException("文件不能为空!");
        }
        List<FileInfo> fileInfos = fileInfoService.uploadFile(Arrays.asList(files), path, relateId);
        return ResponseData.success(fileInfoService.toDTOs(fileInfos));
    }


    /**
     * 上传头像.
     *
     * @param headPicture the head picture
     * @return the response data
     * @author : yangjunqing / 2019-01-17
     */
    @ApiOperation(value = "上传头像", notes = "上传头像API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "api-version", value = "api-version", paramType = "header", required = true, dataType = "string", defaultValue = "V1"),
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
            @ApiImplicitParam(name = "headPicture", value = "头像", required = true, dataType = "MultipartFile")
    })
    @PostMapping(value = "headPicture", headers = {ApiVersionHeader.V1}, consumes = "multipart/form-data")
    public ResponseData uploadHeadPicture(@ApiIgnore MultipartFile headPicture){
        FileInfo fileInfo = fileInfoService.uploadHeadPicture(headPicture);
        return ResponseData.success(fileInfoService.toDTO(fileInfo));
    }


    /**
     * 获取头像.
     *
     * @param username the username
     * @param response the response
     * @author : yangjunqing / 2019-01-17
     */
    @ApiOperation(value = "获取头像", notes = "获取头像API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户账号", required = true, dataType = "string")
    })
    @GetMapping("headPicture/{username}")
    public void headPicture(@PathVariable("username") String username, HttpServletResponse response){
        fileInfoService.headPicture(username, response);
    }


    /**
     * 删除
     *
     * @param ids the ids
     * @return the response data
     * @author : yangjunqing / 2019-01-16
     */
    @ApiOperation(value = "删除上传文件", notes = "删除上传文件API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "api-version", value = "api-version", paramType = "header", required = true, dataType = "string", defaultValue = "V1"),
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
            @ApiImplicitParam(name = "ids", value = "上传文件编号数组", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping(headers = {ApiVersionHeader.V1})
    public ResponseData deleteByIds(@RequestBody List<String> ids){
        List<FileInfo> fileInfos = fileInfoService.findAll(ids);
        fileInfoService.deleteList(fileInfos);
        return ResponseData.success();
    }

    /**
     * 预览文件.
     *
     * @param id       the id
     * @param response the response
     * @author : yangjunqing / 2019-01-17
     */
    @ApiOperation(value = "预览文件", notes = "预览文件API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文件编号", required = true, dataType = "string")
    })
    @GetMapping("preview/{id}")
    public void preview(@PathVariable("id") String id, HttpServletResponse response){
        fileInfoService.preview(id, response);
    }

    /**
     * 下载文件.
     *
     * @param id       the id
     * @param response the response
     * @author : yangjunqing / 2019-01-17
     */
    @ApiOperation(value = "下载文件", notes = "下载文件API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文件编号", required = true, dataType = "string")
    })
    @GetMapping("download/{id}")
    public void download(@PathVariable("id") String id, HttpServletResponse response) {
        fileInfoService.download(id, response);
    }


    /**
     * 查询所有.
     *
     * @param searchCriteria the search criteria
     * @return the response data
     * @author : yangjunqing / 2019-02-15
     */
    @PostMapping("list/all")
    @ApiOperation(value = "查询所有", notes = "查询所有API(请忽略分页参数)", response = FileInfoDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findAll(@RequestBody @Nullable SearchCriteria searchCriteria){
        List<FileInfo> fileInfos = fileInfoService.findAll(searchCriteria.buildSearchParams());
        return ResponseData.success(fileInfoService.toDTOs(fileInfos));
    }


    /**
     * 分页查询
     *
     * @param searchCriteria the search criteria
     * @return the response data
     * @author : yangjunqing / 2019-02-14
     */
    @PostMapping("list")
    @ApiOperation(value = "分页查询", notes = "分页查询API", response = ListData.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findList(@RequestBody @Nullable SearchCriteria searchCriteria){
        ListData<FileInfo> fileInfos = fileInfoService.findPageList(searchCriteria);
        fileInfos.setData(fileInfoService.toDTOs(fileInfos.getOriginData()));
        return ResponseData.success(fileInfos);
    }
}
