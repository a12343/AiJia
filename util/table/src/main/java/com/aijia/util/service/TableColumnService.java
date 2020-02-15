package com.aijia.util.service;


import com.aijia.service.JpaBaseService;
import com.aijia.table.service.BaseTableColumnService;
import com.aijia.util.dto.TableColumnDTO;
import com.aijia.util.entity.TableColumn;
import com.aijia.util.parm.TableColumnPARM;

/**
 * 表关联的列 service
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public interface TableColumnService extends JpaBaseService<TableColumn, TableColumnPARM, TableColumnDTO>, BaseTableColumnService<TableColumn> {
}
