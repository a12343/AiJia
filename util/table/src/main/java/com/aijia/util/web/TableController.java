package com.aijia.util.web;


import com.aijia.search.JpaListData;
import com.aijia.search.JpaSearchCriteria;
import com.aijia.util.dto.TableDTO;
import com.aijia.util.entity.Table;
import com.aijia.util.parm.TablePARM;
import com.aijia.util.service.TableService;
import com.lin.aijia.util.entity.ApiVersionHeader;
import com.lin.aijia.util.entity.ListData;
import com.yyfly.common.entity.ResponseData;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 自定义表 controller
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@RestController
@RequestMapping(value = "Table", headers = {ApiVersionHeader.V1})
@Api(value = "自定义表管理", tags = "自定义表管理API")
@ApiResponses({
    @ApiResponse(code = -1, message = "不符合条件"),
    @ApiResponse(code = 200, message = "成功"),
    @ApiResponse(code = 401, message = "未授权"),
    @ApiResponse(code = 403, message = "拒绝访问"),
    @ApiResponse(code = 404, message = "请求未找到"),
    @ApiResponse(code = 406, message = "token过期或者token无效"),
    @ApiResponse(code = 500, message = "服务器出错"),
})
public class TableController {

    /**
     * CustomTable service
     */
    @Autowired
    private TableService tableService;


    /**
     * 新增
     *
     * @param parm the parm
     * @return the response data
     * @author : yangjunqing / 2019-04-17
     */
    @PostMapping
    @ApiOperation(value = "新增自定义表", response = TableDTO.class,
            notes = "新增自定义表API \n" +
                    "请求示例： \n" +
                    "{ \n" +
                    "   \"name\": \"\" //表名 \n" +
                    "   \"description\": \"\" //表描述 \n" +
                    "   \"moduleName\": \"\" //模块名称(非必传) \n" +
                    "   \"custom\": \"\" //是否开启自定义列功能 \n" +
                    "} \n"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData add(@RequestBody @Validated TablePARM parm){
        Table table = tableService.parmToEntity(parm, Table.class);
        table = tableService.save(table);
        return ResponseData.success(tableService.entityToDTO(table, TableDTO.class));

    }


    /**
     * 开启自定义列功能
     *
     * @param name the name
     * @return the response data
     * @author : yangjunqing / 2019-04-17
     */
    @PutMapping("enabled/{name}")
    @ApiOperation(value = "开启自定义列功能", notes = "开启自定义列功能API", response = Boolean.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData enableCustomization(@PathVariable("name") String name){
        return ResponseData.success(tableService.enableCustomization(name));
    }


    /**
     * 关闭自定义列功能
     *
     * @param name the name
     * @return the response data
     * @author : yangjunqing / 2019-04-17
     */
    @PutMapping("disabled/{name}")
    @ApiOperation(value = "关闭自定义列功能", notes = "关闭自定义列功能API", response = Boolean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData disableCustomization(@PathVariable("name") String name){
        return ResponseData.success(tableService.disableCustomization(name));
    }


    /**
     * 通过表名查询自定义表信息
     *
     * @param name the name
     * @return the response data
     * @author : yangjunqing / 2019-04-17
     */
    @GetMapping("name/{name}")
    @ApiOperation(value = "通过表名查询自定义表信息", notes = "通过表名查询自定义表信息API", response = TableDTO.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findByName(@PathVariable("name") String name){
        Table table = tableService.getTableByName(name);
        return ResponseData.success(tableService.entityToDTO(table, TableDTO.class));
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
                    "  1.条件查询  \n" +
                    "    name -> 表名  \n" +
                    "    description -> 表描述  \n" +
                    "    createBy -> 创建人账号  \n" +
                    "    createDate -> 创建时间  \n"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findList(@RequestBody @Nullable JpaSearchCriteria searchCriteria){
        JpaListData listData = tableService.findPageList(searchCriteria);
        listData.setData(tableService.entityToDTO((List<Table>) listData.getData(), TableDTO.class));
        return ResponseData.success(listData);
    }
}
