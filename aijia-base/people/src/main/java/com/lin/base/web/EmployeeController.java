package com.lin.base.web;

//import com.seerbigdata.imms.base.dto.EmployeeDTO;
//import com.seerbigdata.imms.base.entity.Employee;
//import com.seerbigdata.imms.base.service.EmployeeService;
//import com.seerbigdata.imms.base.validator.EmployeeDTOValidator;
//import com.seerbigdata.util.entity.*;
//import com.seerbigdata.util.service.TableColumnService;
import com.aijia.util.entity.TableColumn;
import com.aijia.util.service.TableColumnService;

import com.lin.base.dto.EmployeeDTO;
import com.lin.base.entity.Employee;
import com.lin.base.service.EmployeeService;
import com.lin.base.validator.EmployeeDTOValidator;
import com.lin.util.entity.*;
import com.yyfly.common.entity.BaseEntity;
import com.yyfly.common.entity.ResponseData;
import com.yyfly.common.exception.GlobalException;
import com.yyfly.common.web.BaseController;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 员工 controller
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@RestController
@RequestMapping(value = "employee", headers = {ApiVersionHeader.V1})
@Api(value = "员工管理", tags = "员工管理API")
@ApiResponses({
    @ApiResponse(code = -1, message = "不符合条件"),
    @ApiResponse(code = 200, message = "成功"),
    @ApiResponse(code = 401, message = "未授权"),
    @ApiResponse(code = 403, message = "拒绝访问"),
    @ApiResponse(code = 404, message = "请求未找到"),
    @ApiResponse(code = 406, message = "token过期或者token无效"),
    @ApiResponse(code = 500, message = "服务器出错"),
})
public class EmployeeController extends BaseController {

    /**
     * Employee dto validator
     */
    @Autowired
    private EmployeeDTOValidator employeeDTOValidator;
    /**
     * Employee service
     */
    @Autowired
    private EmployeeService employeeService;
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
        binder.addValidators(employeeDTOValidator);
    }


    /**
     * 新增
     *
     * @param dto the dto
     * @return the response data
     * @author : yangjunqing / 2019-02-15
     */
    @PostMapping
    @ApiOperation(value = "新增员工", response = EmployeeDTO.class,
            notes = "新增员工API \n" +
                    "请求示例： \n" +
                    "{ \n" +
                    "   \"name\": \"\" //名称 \n" +
                    "   \"departmentId\": \"\" //部门编号 \n" +
                    "   \"email\": \"\" //邮箱 \n" +
                    "   \"phone\": \"\" //电话 \n" +
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
        @ApiImplicitParam(name = "departmentId", value = "部门编号", required = true, dataType = "string"),
        @ApiImplicitParam(name = "email", value = "邮箱", required = false, dataType = "string"),
        @ApiImplicitParam(name = "phone", value = "电话", required = false, dataType = "string"),
        @ApiImplicitParam(name = "note", value = "备注", required = false, dataType = "string")
    })
    public ResponseData add(@RequestBody @Valid @ApiIgnore EmployeeDTO dto){
        Employee employee = employeeService.toEntity(dto);
        System.out.println(dto.getTableColumns());
        List<TableColumn> tableColumns = tableColumnService.parmToEntity(dto.getTableColumns(), TableColumn.class);
        employee = employeeService.save(employee, tableColumns);
        return ResponseData.success(employeeService.toDTO(employee));
    }

    /**
     * 更新
     *
     * @param dto the dto
     * @return the response data
     * @author : yangjunqing / 2019-02-15
     */
    @PutMapping
    @ApiOperation(value = "更新员工", response = EmployeeDTO.class,
            notes = "更新员工API \n" +
                    "请求示例： \n" +
                    "{ \n" +
                    "   \"id\": \"\" //员工编号 \n" +
                    "   \"version\": 0 //版本号 \n" +
                    "   \"name\": \"\" //名称 \n" +
                    "   \"departmentId\": \"\" //部门编号 \n" +
                    "   \"email\": \"\" //邮箱 \n" +
                    "   \"phone\": \"\" //电话 \n" +
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
        @ApiImplicitParam(name = "id", value = "员工编号", required = true, dataType = "string"),
        @ApiImplicitParam(name = "version", value = "版本号", required = true, dataType = "integer"),
        @ApiImplicitParam(name = "enabled", value = "是否启用", required = true, dataType = "boolean"),
        @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "string"),
        @ApiImplicitParam(name = "departmentId", value = "部门编号", required = true, dataType = "string"),
        @ApiImplicitParam(name = "email", value = "邮箱", required = false, dataType = "string"),
        @ApiImplicitParam(name = "phone", value = "电话", required = false, dataType = "string"),
        @ApiImplicitParam(name = "note", value = "备注", required = false, dataType = "string")
    })
    public ResponseData update(@RequestBody @Valid @ApiIgnore EmployeeDTO dto){
        Employee employee = employeeService.toEntity(dto);
        List<TableColumn> tableColumns = tableColumnService.parmToEntity(dto.getTableColumns(), TableColumn.class);
        employee = employeeService.save(employee, tableColumns);
        return ResponseData.success(employeeService.toDTO(employee));
    }


    /**
     * 删除
     *
     * @param ids the ids
     * @return the response data
     * @author : yangjunqing / 2019-02-15
     */
    @ApiOperation(value = "删除员工", notes = "删除员工API")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "ids", value = "员工编号数组", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public ResponseData deleteByIds(@RequestBody Set<String> ids){
        employeeService.deleteByIds(ids);
        return ResponseData.success();
    }


    /**
     * 批量更新状态.
     *
     * @param dto the dto
     * @return the response data
     * @author : yangjunqing / 2019-03-15
     */
    @ApiOperation(value = "批量更新状态", notes = "批量更新状态API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    @PutMapping("status")
    public ResponseData updateStatusByIds(@RequestBody BaseInDTO dto){
        Set<String> ids = dto.getIds();
        if (null == ids || ids.size() == 0){
            throw new GlobalException("至少选择一个id!");
        }
        if (null == dto.getEnabled()){
            throw new  GlobalException("请选择是否启用!");
        }

        return ResponseData.success(employeeService.updateStatusByIds(ids, dto.getEnabled() == true ? BaseEntity.NORMAL : BaseEntity.DISABLE));
    }


    /**
     * 通过编号查询员工信息
     *
     * @param id the id
     * @return the response data
     * @author : yangjunqing / 2019-02-15
     */
    @GetMapping("{id}")
    @ApiOperation(value = "通过编号查询员工信息", notes = "通过编号查询员工信息API", response = EmployeeDTO.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "id", value = "员工编号", required = true, dataType = "string")
    })
    public ResponseData findById(@PathVariable("id") String id){
        Employee employee = employeeService.findById(id);
        return ResponseData.success(employeeService.toDTO(employee));
    }

    /**
    * 查询所有.
    *
    * @param searchCriteria the search criteria
    * @return the response data
    * @author : yangjunqing / 2019-02-15
    */
    @PostMapping("list/all")
    @ApiOperation(value = "查询所有", response = EmployeeDTO.class,
            notes = "查询所有API(请忽略分页参数，时间参数格式 yyyy-MM-dd|yyyy-MM-dd HH:mm:ss):  \n" +
                    "  1.快速查询:  \n" +
                    "    员工名称 部门名称  \n" +
                    "  2.条件查询  \n" +
                    "    name -> 员工名称  \n" +
                    "    departmentId -> 部门编号  \n"
    )
    @ApiImplicitParams({
    @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findAll(@RequestBody @Nullable SearchCriteria searchCriteria){
        if (StringUtils.isNotEmpty(searchCriteria.getFastSearch())){
            searchCriteria.setFastSearchParams(initFastSearchParams(searchCriteria.getFastSearch()));
        }

        List<Employee> employees = employeeService.findAll(searchCriteria.buildSearchParams(), searchCriteria.buildSort());
        return ResponseData.success(employeeService.toDTOs(employees));
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
                    "    员工名称 部门名称  \n" +
                    "  2.条件查询  \n" +
                    "    name -> 员工名称  \n" +
                    "    departmentId -> 部门编号  \n"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findList(@RequestBody @Nullable SearchCriteria searchCriteria){
        if (StringUtils.isNotEmpty(searchCriteria.getFastSearch())){
            searchCriteria.setFastSearchParams(initFastSearchParams(searchCriteria.getFastSearch()));
        }

        ListData<Employee> employees = employeeService.findPageList(searchCriteria);
        employees.setData(employeeService.toDTOs(employees.getOriginData()));
        return ResponseData.success(employees);
    }

    /**
     * 初始化快速查询条件.
     *
     * @param fastSearch the fast search
     * @return the list
     * @author : yangjunqing / 2019-02-16
     */
    private List<FastSearchParam> initFastSearchParams(String fastSearch){
        List<FastSearchParam> fastSearchParams = new ArrayList<>(1);
        fastSearchParams.add(new FastSearchParam("name"));
        fastSearchParams.add(new FastSearchParam("id", employeeService.findIdsByFastSearch(fastSearch)));
        return fastSearchParams;
    }
}
