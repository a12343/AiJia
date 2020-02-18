package com.lin.base.service;

//import com.seerbigdata.imms.base.dto.DepartmentDTO;
//import com.seerbigdata.imms.base.entity.Department;
//import com.seerbigdata.util.entity.DTOConvert;
//import com.seerbigdata.util.entity.ListData;
//import com.seerbigdata.util.entity.SearchCriteria;
//import com.seerbigdata.util.support.BaseTableService;

import com.aijia.util.support.BaseTableService;
import com.lin.aijia.util.entity.DTOConvert;
import com.lin.aijia.util.entity.ListData;
import com.lin.aijia.util.entity.SearchCriteria;
import com.lin.base.dto.DepartmentDTO;
import com.lin.base.entity.Department;

/**
 * 部门 service
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public interface DepartmentService extends BaseTableService<Department>, DTOConvert<Department, DepartmentDTO> {

      /**
       * Find page list list data.
       *
       * @param searchCriteria the search criteria
       * @return the list data
       * @author : yangjunqing / 2019-02-15
       */
      ListData<Department> findPageList(SearchCriteria searchCriteria);
  }
