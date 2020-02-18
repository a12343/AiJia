package com.lin.base.dao;


import com.lin.base.entity.Department;
import com.yyfly.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 部门 repository
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Repository
public interface DepartmentDao extends BaseRepository<Department> {
}
