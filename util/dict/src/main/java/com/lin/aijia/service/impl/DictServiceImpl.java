package com.lin.aijia.service.impl;


import com.lin.aijia.dao.DictDao;
import com.lin.aijia.dto.DictDTO;
import com.lin.aijia.entity.Dict;
import com.lin.aijia.service.DictService;
import com.lin.aijia.util.entity.ListData;
import com.lin.aijia.util.entity.SearchCriteria;
import com.yyfly.common.repository.BaseRepository;
import com.yyfly.common.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典 service impl
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {

    @Autowired
    private DictDao dictDao;


    @Override
    protected BaseRepository<Dict> getDao() {
        return dictDao;
    }

    @Override
    public Dict add(Dict dict) {
        if (StringUtils.isEmpty(dict.getPid())){
            dict.setHierarchy(0);
        }
        else {
            dict.setHierarchy(dictDao.getHierarchyById(dict.getPid()) + 1);
        }
        return save(dict);
    }

    @Override
    public Dict update(Dict dict) {
        return save(dict);
    }

    @Override
    public ListData<Dict> findPageList(SearchCriteria searchCriteria) {
        return ListData.of(findAll(searchCriteria.buildSearchParams(), searchCriteria.buildPageRequest()));
    }

    @Override
    public List<Dict> findDict(String entityName, String filed) {
        return dictDao.findByEntityNameAndFiled(entityName, filed);
    }

    @Override
    public Dict getDict(String entityName, String filed, String code) {
        return dictDao.findByEntityNameAndFiledAndCode(entityName, filed, code);
    }

    @Override
    public String getValue(String entityName, String filed, String code) {
        return dictDao.getValueByEntityNameAndFiledAndCode(entityName, filed, code);
    }

    @Override
    public DictDTO toDTO(Dict dict) {
        if (dict == null){
            return null;
        }

        DictDTO dto = new DictDTO();
        BeanUtils.copyProperties(dict, dto);
        return dto;
    }

    @Override
    public Dict toEntity(DictDTO dto) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(dto, dict);
        return dict;
    }

    @Override
    public List<DictDTO> toDTOs(List<Dict> dicts) {
        List<DictDTO> dtos = new ArrayList<>();
        if (dicts != null && dicts.size() > 0){
            dicts.forEach(dict -> {
                DictDTO dto = toDTO(dict);
                dtos.add(dto);
            });
        }
        return dtos;
    }

    @Override
    public List<Dict> toEntitys(List<DictDTO> dictDTOs) {
        List<Dict> dicts = new ArrayList<>();
        if (dictDTOs != null && dictDTOs.size() > 0){
            dictDTOs.forEach(dictDTO -> {
                Dict dict = toEntity(dictDTO);
                dicts.add(dict);
            });
        }
        return dicts;
    }
}
