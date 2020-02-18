package com.lin.base.service.impl;

//import com.seerbigdata.imms.base.dao.DepartmentDao;
//import com.seerbigdata.imms.base.dto.DepartmentDTO;
//import com.seerbigdata.imms.base.entity.Department;
//import com.seerbigdata.imms.base.service.DepartmentService;
//import com.seerbigdata.util.entity.ListData;
//import com.seerbigdata.util.entity.SearchCriteria;
//import com.seerbigdata.util.entity.TableColumn;
//import com.seerbigdata.util.parm.TableColumnPARM;
//import com.seerbigdata.util.service.TableColumnService;
//import com.seerbigdata.util.support.BaseTableServiceImpl;
//import com.seerbigdata.util.utils.TableUtils;
//import com.seerbigdata.util.utils.ToolUtils;
import com.aijia.util.entity.TableColumn;
import com.aijia.util.parm.TableColumnPARM;
import com.aijia.util.service.TableColumnService;
import com.aijia.util.support.BaseTableServiceImpl;
import com.lin.aijia.util.entity.ListData;
import com.lin.aijia.util.entity.SearchCriteria;
import com.lin.aijia.util.utils.TableUtils;
import com.lin.aijia.util.utils.ToolUtils;
import com.lin.base.dao.DepartmentDao;
import com.lin.base.dto.DepartmentDTO;
import com.lin.base.entity.Department;
import com.lin.base.service.DepartmentService;
import com.yyfly.common.repository.BaseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门 service impl
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Service
public class DepartmentServiceImpl extends BaseTableServiceImpl<Department> implements DepartmentService {

    /**
     * Department dao
     */
    @Autowired
    private DepartmentDao departmentDao;
    /**
     * Table column service
     */
    @Autowired
    private TableColumnService tableColumnService;

    /**
     * 表名
     */
    private final String tableName = TableUtils.getTableName(Department.class);


    @Override
    protected BaseRepository<Department> getDao() {
        return departmentDao;
    }

    @Override
    public TableColumnService getTableColumnService() {
        return tableColumnService;
    }

    @Override
    public String getEntityTableName() {
        return this.tableName;
    }

    @Override
    public ListData<Department> findPageList(SearchCriteria searchCriteria) {
        return ListData.of(findAll(searchCriteria.buildSearchParams(), searchCriteria.buildPageRequest()));
    }

    @Override
    public DepartmentDTO toDTO(Department department) {
        if (department == null){
            return null;
        }

        DepartmentDTO dto = new DepartmentDTO();
        BeanUtils.copyProperties(department, dto);
        dto.setEnabled(ToolUtils.statusToBoolean(dto.getStatus()));

        List<TableColumn> tableColumns = tableColumnService.findTableColumn(getEntityTableName(), department.getId());
        dto.setTableColumns(tableColumnService.entityToPARM(tableColumns, TableColumnPARM.class));

        return dto;
    }

    @Override
    public Department toEntity(DepartmentDTO dto) {
        Department department = new Department();
        BeanUtils.copyProperties(dto, department);
        department.setStatus(ToolUtils.booleanToStatus(dto.isEnabled()));
        return department;
    }

    @Override
    public List<DepartmentDTO> toDTOs(List<Department> departments) {
        List<DepartmentDTO> dtos = new ArrayList<>();
        if (departments != null && departments.size() > 0){
            departments.forEach(department -> {
                DepartmentDTO dto = toDTO(department);
                dtos.add(dto);
            });
        }
        return dtos;
    }

    @Override
    public List<Department> toEntitys(List<DepartmentDTO> departmentDTOs) {
        List<Department> departments = new ArrayList<>();
        if (departmentDTOs != null && departmentDTOs.size() > 0){
            departmentDTOs.forEach(departmentDTO -> {
                Department department = toEntity(departmentDTO);
                departments.add(department);
            });
        }
        return departments;
    }
}
