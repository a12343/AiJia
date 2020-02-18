package com.lin.aijia.web;


import com.lin.aijia.dto.DictDTO;
import com.lin.aijia.entity.Dict;
import com.lin.aijia.service.DictService;

import com.lin.aijia.validator.DictDTOValidator;
import com.lin.util.entity.ApiVersionHeader;
import com.lin.util.entity.ListData;
import com.lin.util.entity.SearchCriteria;
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

/**
 * 数据字典 controller
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@RestController
@RequestMapping(value = "dict", headers = {ApiVersionHeader.V1})
@Api(value = "数据字典管理", tags = "数据字典管理API")
@ApiResponses({
    @ApiResponse(code = -1, message = "不符合条件"),
    @ApiResponse(code = 200, message = "成功"),
    @ApiResponse(code = 401, message = "未授权"),
    @ApiResponse(code = 403, message = "拒绝访问"),
    @ApiResponse(code = 404, message = "请求未找到"),
    @ApiResponse(code = 406, message = "token过期或者token无效"),
    @ApiResponse(code = 500, message = "服务器出错"),
})
public class DictController extends BaseController {

    /**
     * Dict dto validator
     */
    @Autowired
    private DictDTOValidator dictDTOValidator;
    /**
     * Dict service
     */
    @Autowired
    private DictService dictService;


    /**
     * Validator.
     *
     * @param binder the binder
     * @author : yangjunqing / 2019-02-15
     */
    @InitBinder
    public void validator(WebDataBinder binder){
        binder.addValidators(dictDTOValidator);
    }

    /**
     * 新增
     *
     * @param dto the dto
     * @return the response data
     * @author : yangjunqing / 2019-02-15
     */
    @PostMapping
    @ApiOperation(value = "新增数据字典", notes = "新增数据字典API", response = DictDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
            @ApiImplicitParam(name = "name", value = "名称", required = false, dataType = "string"),
            @ApiImplicitParam(name = "pid", value = "上一级", required = false, dataType = "string"),
            @ApiImplicitParam(name = "entityName", value = "类名", required = false, dataType = "string"),
            @ApiImplicitParam(name = "filed", value = "字段名", required = false, dataType = "string"),
            @ApiImplicitParam(name = "code", value = "编码", required = false, dataType = "string"),
            @ApiImplicitParam(name = "value", value = "值", required = false, dataType = "string")
    })
    public ResponseData add(@RequestBody @Valid @ApiIgnore DictDTO dto){
        Dict dict = dictService.toEntity(dto);
        dict = dictService.add(dict);
        return ResponseData.success(dictService.toDTO(dict));

    }


    /**
     * 更新
     *
     * @param dto the dto
     * @return the response data
     * @author : yangjunqing / 2019-02-15
     */
    @PutMapping
    @ApiOperation(value = "更新数据字典", notes = "更新数据字典API", response = DictDTO.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "id", value = "数据字典编号", required = true, dataType = "string"),
        @ApiImplicitParam(name = "version", value = "版本号", required = true, dataType = "integer"),
        @ApiImplicitParam(name = "name", value = "名称", required = false, dataType = "string"),
        @ApiImplicitParam(name = "pid", value = "上一级", required = false, dataType = "string"),
        @ApiImplicitParam(name = "entityName", value = "类名", required = false, dataType = "string"),
        @ApiImplicitParam(name = "filed", value = "字段名", required = false, dataType = "string"),
        @ApiImplicitParam(name = "code", value = "编码", required = false, dataType = "string"),
        @ApiImplicitParam(name = "value", value = "值", required = false, dataType = "string"),
        @ApiImplicitParam(name = "hierarchy", value = "层级", required = true, dataType = "integer")
    })
    public ResponseData update(@RequestBody @Valid @ApiIgnore DictDTO dto){
        Dict dict = dictService.toEntity(dto);
        dict = dictService.update(dict);
        return ResponseData.success(dictService.toDTO(dict));
    }


    /**
     * 删除
     *
     * @param ids the ids
     * @return the response data
     * @author : yangjunqing / 2019-02-15
     */
    @ApiOperation(value = "删除数据字典", notes = "删除数据字典API")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "ids", value = "数据字典编号数组", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public ResponseData deleteByIds(@RequestBody List<String> ids){
        List<Dict> dicts = dictService.findAll(ids);
        dictService.deleteAll(dicts);
        return ResponseData.success();
    }

    /**
     * 通过编号查询数据字典信息
     *
     * @param id the id
     * @return the response data
     * @author : yangjunqing / 2019-02-15
     */
    @GetMapping("{id}")
    @ApiOperation(value = "通过编号查询数据字典信息", notes = "通过编号查询数据字典信息API", response = DictDTO.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "id", value = "数据字典编号", required = true, dataType = "string")
    })
    public ResponseData findById(@PathVariable("id") String id){
        Dict dict = dictService.findById(id);
        return ResponseData.success(dictService.toDTO(dict));
    }

    /**
    * 查询所有.
    *
    * @param searchCriteria the search criteria
    * @return the response data
    * @author : yangjunqing / 2019-02-15
    */
    @PostMapping("list/all")
    @ApiOperation(value = "查询所有", response = DictDTO.class,
            notes = "查询所有API(请忽略分页参数，时间参数格式 yyyy-MM-dd|yyyy-MM-dd HH:mm:ss):  \n" +
                    "  1.条件查询  \n" +
                    "    createBy -> 创建人账号  \n" +
                    "    createDate -> 创建时间  \n"
    )
    @ApiImplicitParams({
    @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findAll(@RequestBody @Nullable SearchCriteria searchCriteria){
    List<Dict> dicts = dictService.findAll(searchCriteria.buildSearchParams());
        return ResponseData.success(dictService.toDTOs(dicts));
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
        ListData<Dict> dicts = dictService.findPageList(searchCriteria);
        dicts.setData(dictService.toDTOs(dicts.getOriginData()));
        return ResponseData.success(dicts);
    }
}
