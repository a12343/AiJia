package com.lin.aijia.service;


import com.lin.aijia.dto.DictDTO;
import com.lin.aijia.entity.Dict;
import com.lin.aijia.util.entity.DTOConvert;
import com.lin.aijia.util.entity.ListData;
import com.lin.aijia.util.entity.SearchCriteria;
import com.yyfly.common.service.BaseService;

import java.util.List;

/**
 * 数据字典 service
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public interface DictService extends BaseService<Dict>, DTOConvert<Dict, DictDTO> {

    /**
     * 新增.
     *
     * @param dict the dict
     * @return the dict
     * @author : yangjunqing / 2019-02-19
     */
    Dict add(Dict dict);

    /**
     * 更新.
     *
     * @param dict the dict
     * @return the dict
     * @author : yangjunqing / 2019-02-19
     */
    Dict update(Dict dict);

    /**
     * Find page list list data.
     *
     * @param searchCriteria the search criteria
     * @return the list data
     * @author : yangjunqing / 2019-02-15
     */
    ListData<Dict> findPageList(SearchCriteria searchCriteria);

    /**
     * 通过类名,字段名查询字典信息.
     *
     * @param entityName the entity name
     * @param filed      the filed
     * @return the list
     * @author : yangjunqing / 2019-02-15
     */
    List<Dict> findDict(String entityName, String filed);

    /**
     * 通过类名,字段名,code查询字典信息.
     *
     * @param entityName the entity name
     * @param filed      the filed
     * @param code       the code
     * @return the dict
     * @author : yangjunqing / 2019-02-15
     */
    Dict getDict(String entityName, String filed, String code);

    /**
     * 通过类名,字段名,code查询value.
     *
     * @param entityName the entity name
     * @param filed      the filed
     * @param code       the code
     * @return the string
     * @author : yangjunqing / 2019-02-16
     */
    String getValue(String entityName, String filed, String code);
  }
