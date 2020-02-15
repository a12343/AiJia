package com.aijia.util.service;


import com.aijia.service.JpaBaseService;
import com.aijia.table.service.BaseTableService;
import com.aijia.util.dto.TableDTO;
import com.aijia.util.entity.Table;
import com.aijia.util.parm.TablePARM;
import org.springframework.lang.NonNull;

/**
 * 自定义表 service
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public interface TableService extends JpaBaseService<Table, TablePARM, TableDTO>, BaseTableService<Table> {

    /**
     * 根据表名查询是否已存在.
     *
     * @param name the name
     * @return the boolean
     * @author : yangjunqing / 2019-04-17
     */
    Boolean existsByName(@NonNull String name);
}
