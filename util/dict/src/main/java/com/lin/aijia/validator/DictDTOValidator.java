package com.lin.aijia.validator;

import com.lin.aijia.dto.DictDTO;
import com.lin.aijia.entity.Dict;
import com.lin.aijia.service.DictService;
import com.yyfly.common.exception.GlobalException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * DictDTO 验证器
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Component
public class DictDTOValidator implements Validator {

    @Autowired
    private DictService dictService;


    /**
     * 在WebDataBinder添加验证器后，所有的对象参数都会执行这个supports方法, 通过后spring才会检测
     * controller层的方法参数是否使用@Validated/Valid，然后执行validate方法
     * 所以为了防止错误，这里支持任何类型参数
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object object, Errors errors) {
        if (object == null){
            throw new GlobalException("数据字典信息不能为空!");
        }
        DictDTO dto = (DictDTO) object;

        //新增
        if (StringUtils.isEmpty(dto.getId())){
            if (StringUtils.isNotEmpty(dto.getEntityName()) && StringUtils.isNotEmpty(dto.getFiled()) && StringUtils.isNotEmpty(dto.getCode())){
                Dict origin = dictService.getDict(dto.getEntityName(), dto.getFiled(), dto.getCode());
                if (origin != null){
                    throw new GlobalException("节点编码已存在!");
                }
            }
        }
        //更新
        else {
            if (StringUtils.isNotEmpty(dto.getEntityName()) && StringUtils.isNotEmpty(dto.getFiled()) && StringUtils.isNotEmpty(dto.getCode())){
                Dict origin = dictService.getDict(dto.getEntityName(), dto.getFiled(), dto.getCode());
                if (origin != null && !origin.getPid().equals(dto.getId())){
                    throw new GlobalException("节点编码已存在!");
                }
            }
        }
    }
}
