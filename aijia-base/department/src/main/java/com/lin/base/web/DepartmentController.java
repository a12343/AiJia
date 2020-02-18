package com.lin.base.web;

//import com.seerbigdata.imms.base.dto.DepartmentDTO;
//import com.seerbigdata.imms.base.entity.Department;
//import com.seerbigdata.imms.base.service.DepartmentService;
//import com.seerbigdata.imms.base.validator.DepartmentDTOValidator;
//import com.seerbigdata.util.entity.ApiVersionHeader;
//import com.seerbigdata.util.entity.ListData;
//import com.seerbigdata.util.entity.SearchCriteria;
//import com.seerbigdata.util.entity.TableColumn;
//import com.seerbigdata.util.service.TableColumnService;
import com.aijia.util.entity.TableColumn;
import com.aijia.util.service.TableColumnService;
import com.lin.aijia.util.entity.ApiVersionHeader;
import com.lin.aijia.util.entity.ListData;
import com.lin.aijia.util.entity.SearchCriteria;
import com.lin.base.dto.DepartmentDTO;
import com.lin.base.entity.Department;
import com.lin.base.service.DepartmentService;
import com.lin.base.validator.DepartmentDTOValidator;
import com.yyfly.common.entity.ResponseData;
import com.yyfly.common.web.BaseController;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * 部门 controller
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@RestController
@RequestMapping(value = "department", headers = {ApiVersionHeader.V1})
@Api(value = "部门管理", tags = "部门管理API")
@ApiResponses({
    @ApiResponse(code = -1, message = "不符合条件"),
    @ApiResponse(code = 200, message = "成功"),
    @ApiResponse(code = 401, message = "未授权"),
    @ApiResponse(code = 403, message = "拒绝访问"),
    @ApiResponse(code = 404, message = "请求未找到"),
    @ApiResponse(code = 406, message = "token过期或者token无效"),
    @ApiResponse(code = 500, message = "服务器出错"),
})
public class DepartmentController extends BaseController {

    /**
     * Department dto validator
     */
    @Autowired
    private DepartmentDTOValidator departmentDTOValidator;
    /**
     * Department service
     */
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private TableColumnService tableColumnService;


    /**
     * Validator.
     *
     * @param binder the binder
     * @author : yangjunqing / 2019-02-15
     */
    @InitBinder
    public void validator(WebDataBinder binder){
        binder.addValidators(departmentDTOValidator);
    }


    /**
     * 新增
     *
     * @param dto the dto
     * @return the response data
     * @author : yangjunqing / 2019-02-15
     */
    @PostMapping
    @ApiOperation(value = "新增部门", response = DepartmentDTO.class,
            notes = "新增部门API \n" +
                    "请求示例： \n" +
                    "{ \n" +
                    "   \"name\": \"\" //名称 \n" +
                    "   \"pid\": \"\" //上一级部门 \n" +
                    "   \"note\": \"\" //备注 \n" +
                    "   \"enabled\": boolean //是否启用 \n" +
                    "   \"tableColumns\": [ //自定义列 \n" +
                    "       { \n" +
                    "               \"columnName\": \"\" //自定义列名 \n" +
                    "               \"columnValue\": \"\" //值 \n" +
                    "       } \n" +
                    "   ] \n" +
                    "} \n"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "enabled", value = "是否启用", required = true, dataType = "boolean"),
        @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "string"),
        @ApiImplicitParam(name = "pid", value = "上一级部门", required = false, dataType = "string"),
        @ApiImplicitParam(name = "note", value = "备注", required = false, dataType = "string"),
        @ApiImplicitParam(name = "tableColumns", value = "自定义列对象数组", required = false, dataType = "string")
    })
    public ResponseData add(@RequestBody @Valid @ApiIgnore DepartmentDTO dto){
        Department department = departmentService.toEntity(dto);
        List<TableColumn> tableColumns = tableColumnService.parmToEntity(dto.getTableColumns(), TableColumn.class);
        department = departmentService.save(department, tableColumns);
        return ResponseData.success(departmentService.toDTO(department));

    }

    /**
     * 更新
     *
     * @param dto the dto
     * @return the response data
     * @author : yangjunqing / 2019-02-15
     */
    @PutMapping
    @ApiOperation(value = "更新部门", response = DepartmentDTO.class,
            notes = "更新部门API \n" +
                    "请求示例： \n" +
                    "{ \n" +
                    "   \"id\": \"\" //部门编号 \n" +
                    "   \"version\": 0 //版本号 \n" +
                    "   \"name\": \"\" //名称 \n" +
                    "   \"pid\": \"\" //上一级部门 \n" +
                    "   \"note\": \"\" //备注 \n" +
                    "   \"enabled\": boolean //是否启用 \n" +
                    "   \"tableColumns\": [ //自定义列 \n" +
                    "       { \n" +
                    "               \"columnName\": \"\" //自定义列名 \n" +
                    "               \"columnValue\": \"\" //值 \n" +
                    "       } \n" +
                    "   ] \n" +
                    "} \n"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "id", value = "部门编号", required = true, dataType = "string"),
        @ApiImplicitParam(name = "version", value = "版本号", required = true, dataType = "integer"),
        @ApiImplicitParam(name = "enabled", value = "是否启用", required = true, dataType = "boolean"),
        @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "string"),
        @ApiImplicitParam(name = "pid", value = "上一级部门", required = false, dataType = "string"),
        @ApiImplicitParam(name = "note", value = "备注", required = false, dataType = "string")
    })
    public ResponseData update(@RequestBody @Valid @ApiIgnore DepartmentDTO dto){
        Department department = departmentService.toEntity(dto);
        List<TableColumn> tableColumns = tableColumnService.parmToEntity(dto.getTableColumns(), TableColumn.class);
        department = departmentService.save(department, tableColumns);
        return ResponseData.success(departmentService.toDTO(department));
    }


    /**
     * 删除
     *
     * @param ids the ids
     * @return the response data
     * @author : yangjunqing / 2019-02-15
     */
    @ApiOperation(value = "删除部门", notes = "删除部门API")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "ids", value = "部门编号数组", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public ResponseData deleteByIds(@RequestBody Set<String> ids){
        departmentService.deleteByIds(ids);
        return ResponseData.success();
    }

    /**
     * 通过编号查询部门信息
     *
     * @param id the id
     * @return the response data
     * @author : yangjunqing / 2019-02-15
     */
    @GetMapping("{id}")
    @ApiOperation(value = "通过编号查询部门信息", notes = "通过编号查询部门信息API", response = DepartmentDTO.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "id", value = "部门编号", required = true, dataType = "string")
    })
    public ResponseData findById(@PathVariable("id") String id){
        Department department = departmentService.findById(id);
        return ResponseData.success(departmentService.toDTO(department));
    }

    /**
    * 查询所有.
    *
    * @param searchCriteria the search criteria
    * @return the response data
    * @author : yangjunqing / 2019-02-15
    */
    @PostMapping("list/all")
    @ApiOperation(value = "查询所有", response = DepartmentDTO.class,
            notes = "查询所有API(请忽略分页参数，时间参数格式 yyyy-MM-dd|yyyy-MM-dd HH:mm:ss):  \n" +
                    "  1.条件查询  \n" +
                    "  1.条件查询  \n" +
                    "    createBy -> 创建人账号  \n" +
                    "    createDate -> 创建时间  \n"
    )
    @ApiImplicitParams({
    @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findAll(@RequestBody @Nullable SearchCriteria searchCriteria){
        List<Department> departments = departmentService.findAll(searchCriteria.buildSearchParams(), searchCriteria.buildSort());
        return ResponseData.success(departmentService.toDTOs(departments));
    }

    /**
     * 分页查询
     *
     * @param searchCriteria the search criteria
     * @return the response data
     * @author : yangjunqing / 2019-02-15
     */
    @PostMapping("list")
    @ApiOperation(value = "分页查询", response = ListData.class,
            notes = "分页查询支持说明(时间参数格式 yyyy-MM-dd|yyyy-MM-dd HH:mm:ss):  \n" +
                    "  1.快速查询:  \n" +
                    "    暂无  \n" +
                    "  2.条件查询  \n" +
                    "    createBy -> 创建人账号  \n" +
                    "    createDate -> 创建时间  \n"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findList(@RequestBody @Nullable SearchCriteria searchCriteria){
        ListData<Department> departments = departmentService.findPageList(searchCriteria);
        departments.setData(departmentService.toDTOs(departments.getOriginData()));
        return ResponseData.success(departments);
    }
}
