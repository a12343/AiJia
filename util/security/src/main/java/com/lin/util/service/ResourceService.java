package com.lin.util.service;



import com.lin.util.dto.ResourceDTO;
import com.lin.util.entity.DTOConvert;
import com.lin.util.entity.ListData;
import com.lin.util.entity.Resource;
import com.lin.util.entity.SearchCriteria;
import com.yyfly.common.service.BaseService;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Set;

/**
 * 资源 service
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public interface ResourceService extends BaseService<Resource>, DTOConvert<Resource, ResourceDTO> {

      /**
       * Find page list list data.
       *
       * @param searchCriteria the search criteria
       * @return the list data
       * @author : yangjunqing / 2019-01-08
       */
      ListData<Resource> findPageList(SearchCriteria searchCriteria);

    /**
     * 通过用户名查询菜单权限.
     *
     * @param username the username
     * @return the list
     * @author : yangjunqing / 2019-03-18
     */
    Set<Resource> findMenuByUsername(@NonNull String username);

    /**
     * 根据用户和菜单编号查询功能资源.
     *
     * @param pid the pid
     * @return the list
     * @author : yangjunqing / 2019-03-18
     */
    List<Resource> findPermissionByPidAndUsername(String pid, String username);
  }
