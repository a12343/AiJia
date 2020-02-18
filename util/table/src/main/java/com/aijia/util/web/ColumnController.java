package com.aijia.util.web;

import com.aijia.search.JpaListData;
import com.aijia.search.JpaSearchCriteria;
import com.aijia.util.dto.ColumnDTO;
import com.aijia.util.entity.Column;
import com.aijia.util.parm.ColumnPARM;
import com.aijia.util.parm.ColumnSequencePARM;
import com.aijia.util.service.ColumnService;
import com.google.common.base.Preconditions;


import com.lin.util.entity.ApiVersionHeader;
import com.lin.util.entity.ListData;
import com.yyfly.common.entity.ResponseData;
import com.yyfly.common.web.BaseController;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 列 controller
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@RestController
@RequestMapping(value = "column", headers = {ApiVersionHeader.V1})
@Api(value = "自定义列管理", tags = "自定义列管理API")
@ApiResponses({
    @ApiResponse(code = -1, message = "不符合条件"),
    @ApiResponse(code = 200, message = "成功"),
    @ApiResponse(code = 401, message = "未授权"),
    @ApiResponse(code = 403, message = "拒绝访问"),
    @ApiResponse(code = 404, message = "请求未找到"),
    @ApiResponse(code = 406, message = "token过期或者token无效"),
    @ApiResponse(code = 500, message = "服务器出错"),
})
public class ColumnController extends BaseController {

    /**
     * Column service
     */
    @Autowired
    private ColumnService columnService;


    /**
     * 新增
     *
     * @param parm the parm
     * @return the response data
     * @author : yangjunqing / 2019-04-17
     */
    @PostMapping
    @ApiOperation(value = "新增列", response = ColumnDTO.class,
            notes = "新增列API \n" +
                    "请求示例： \n" +
                    "{ \n" +
                    "   \"tableName\": \"\" //表名 \n" +
                    "   \"name\": \"\" //列名 \n" +
                    "   \"label\": \"\" //列别名 \n" +
                    "   \"type\": \"\" //类型(可用值:text,number,radio,checkbox,date,datetime,select) \n" +
                    "   \"required\": boolean //是否必填 \n" +
                    "   \"displayed\": boolean //是否列表显示 \n" +
                    "   \"enabled\": boolean //是否启用 \n" +
                    "   \"sequence\": 0 //序列值 \n" +
                    "   \"length\": \"\" //长度 \n" +
                    "   \"defaultValue\": \"\" //默认值 \n" +
                    "} \n"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData add(@RequestBody @Validated ColumnPARM parm){
        Preconditions.checkArgument(!columnService.existsByName(parm.getTableName(), parm.getName()), "该列名已存在!");

        Column column = columnService.parmToEntity(parm, Column.class);
        column = columnService.save(column);
        return ResponseData.success(columnService.entityToDTO(column, ColumnDTO.class));
    }


    /**
     * 更新是否必填属性
     *
     * @param id      the id
     * @param require the require
     * @return the response data
     * @author : yangjunqing / 2019-04-17
     */
    @PutMapping("required/{id}/{require}")
    @ApiOperation(value = "更新是否必填属性", notes = "更新是否必填属性API", response = Boolean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData updateRequired(@PathVariable("id") String id, @PathVariable("require") Boolean require){
        return ResponseData.success(columnService.updateRequired(id, require));
    }


    /**
     * 更新是否列表显示属性
     *
     * @param id      the id
     * @param display the display
     * @return the response data
     * @author : yangjunqing / 2019-04-17
     */
    @PutMapping("displayed/{id}/{display}")
    @ApiOperation(value = "更新是否列表显示属性", notes = "更新是否列表显示属性API", response = Boolean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData updateDisplayed(@PathVariable("id") String id, @PathVariable("display") Boolean display){
        return ResponseData.success(columnService.updateDisplayed(id, display));
    }


    /**
     * 更新是否启用属性
     *
     * @param id     the id
     * @param enable the enable
     * @return the response data
     * @author : yangjunqing / 2019-04-17
     */
    @PutMapping("enabled/{id}/{enable}")
    @ApiOperation(value = "更新是否启用属性", notes = "更新是否启用属性API", response = Boolean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData updateEnabled(@PathVariable("id") String id, @PathVariable("enable") Boolean enable){
        return ResponseData.success(columnService.updateEnabled(id, enable));
    }


    /**
     * 更新排序号.
     *
     * @param parms the parms
     * @return the response data
     * @author : yangjunqing / 2019-04-17
     */
    @PutMapping("sequence")
    @ApiOperation(value = "更新排序号", response = Boolean.class,
            notes = "更新排序号API \n" +
                    "请求示例： \n" +
                    "[ \n" +
                    "   { \n" +
                    "       \"id\": \"\" //列编号 \n" +
                    "       \"sequence\": \"\" //序列值 \n" +
                    "   } \n" +
                    "] \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData updateSequence(@RequestBody Set<ColumnSequencePARM> parms){
        Preconditions.checkArgument(null != parms && parms.size() > 0, "参数不能为空!");
        return ResponseData.success(columnService.updateSequence(parms));
    }


    /**
     * 通过编号查询列信息
     *
     * @param id the id
     * @return the response data
     * @author : yangjunqing / 2019-04-17
     */
    @GetMapping("{id}")
    @ApiOperation(value = "通过编号查询列信息", notes = "通过编号查询列信息API", response = ColumnDTO.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findById(@PathVariable("id") String id){
        Column column = columnService.findById(id);
        return ResponseData.success(columnService.entityToDTO(column, ColumnDTO.class));
    }


    /**
     * 通过表名查询有效的自定义列
     *
     * @param tableName the table name
     * @return the response data
     * @author : yangjunqing / 2019-04-17
     */
    @GetMapping("tableName/{tableName}")
    @ApiOperation(value = "通过表名查询有效的自定义列", notes = "通过表名查询有效的自定义列API", response = ColumnDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findByTableName(@PathVariable("tableName") String tableName){
        List<Column> columns = columnService.findColumnsByTableName(tableName);
        return ResponseData.success(columnService.entityToDTO(columns, ColumnDTO.class));
    }

    /**
     * 通过表名和列名获取列信息
     *
     * @param tableName the table name
     * @return the response data
     * @author : yangjunqing / 2019-04-17
     */
    @GetMapping("tableName/{tableName}/columnName/{columnName}")
    @ApiOperation(value = "通过表名和列名获取列信息", notes = "通过表名和列名获取列信息API", response = ColumnDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findByTableNameAndColumnName(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName){
        Column column = columnService.getColumnByTableNameAndColumnName(tableName, columnName);
        return ResponseData.success(columnService.entityToDTO(column, ColumnDTO.class));
    }


    /**
     * 分页查询
     *
     * @param searchCriteria the search criteria
     * @return the response data
     * @author : yangjunqing / 2019-04-17
     */
    @PostMapping("list")
    @ApiOperation(value = "分页查询", response = ListData.class,
            notes = "分页查询支持说明(时间参数格式 yyyy-MM-dd|yyyy-MM-dd HH:mm:ss):  \n" +
                    "  1.建议排序列:  \n" +
                    "    tableName -> 表名  \n" +
                    "    name -> 列名  \n" +
                    "    sequence -> 序列  \n" +
                    "    createDate -> 创建时间  \n" +
                    "    updateDate -> 更新时间  \n" +
                    "  2.条件查询  \n" +
                    "    tableName -> 表名  \n" +
                    "    name -> 列名  \n" +
                    "    createBy -> 创建人账号  \n" +
                    "    createDate -> 创建时间  \n"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findList(@RequestBody @Nullable JpaSearchCriteria searchCriteria){
        JpaListData listData = columnService.findPageList(searchCriteria);
        List<Column> columns = ((List<Column>) listData.getData())
                                    .stream()
                                    .filter(column -> column.getEnabled())
                                    .collect(Collectors.toList());
        listData.setData(columnService.entityToDTO(columns, ColumnDTO.class));
        return ResponseData.success(listData);
    }
}
