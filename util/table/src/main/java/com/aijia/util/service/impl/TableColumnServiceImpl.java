package com.aijia.util.service.impl;

import com.aijia.config.properties.SeerJpaProperties;
import com.aijia.repository.JpaBaseRepository;
import com.aijia.service.impl.JpaBaseServiceImpl;
import com.aijia.util.dao.TableColumnDao;
import com.aijia.util.dto.ColumnDTO;
import com.aijia.util.dto.TableColumnDTO;
import com.aijia.util.entity.Column;
import com.aijia.util.entity.TableColumn;
import com.aijia.util.parm.TableColumnPARM;
import com.aijia.util.service.ColumnService;
import com.aijia.util.service.TableColumnService;
import com.google.common.collect.Lists;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 表关联的列 service impl
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class TableColumnServiceImpl extends JpaBaseServiceImpl<TableColumn, TableColumnPARM, TableColumnDTO> implements TableColumnService {

    /**
     * Seer jpa properties
     */
    @Autowired
    private SeerJpaProperties seerJpaProperties;
    /**
     * Table column dao
     */
    @Autowired
    private TableColumnDao tableColumnDao;
    @Autowired
    private ColumnService columnService;


    @Override
    protected JpaBaseRepository<TableColumn> getJpaBaseRepository() {
        return tableColumnDao;
    }

    @Override
    protected SeerJpaProperties getSeerJpaProperties() {
        return seerJpaProperties;
    }

    @Override
    public Boolean save(String tableName, String tableId, TableColumn tableColumn) {
        TableColumn old = getTableColumn(tableName, tableId, tableColumn.getColumnName());

        if (old != null){
            old.setColumnValue(tableColumn.getColumnValue());
            tableColumnDao.save(old);
        }else {
            tableColumn.setTableName(tableName);
            tableColumn.setTableId(tableId);
            tableColumnDao.save(tableColumn);
        }
        return true;
    }

    @Override
    public Boolean save(String tableName, String tableId, Collection<TableColumn> tableColumns) {
        if (null != tableColumns && tableColumns.size() > 0){
            tableColumns.forEach(tableColumn -> {
                save(tableName, tableId, tableColumn);
            });
        }
        return true;
    }

    @Override
    public Boolean deleteByTableNameAndTableId(String tableName, String id) {
        return tableColumnDao.deleteByTableNameAndDataId(tableName, id) == 0 ? false : true;
    }

    @Override
    public Boolean deleteByTableNameAndTableId(String tableName, Set<String> ids) {
        return tableColumnDao.deleteByTableNameAndDataIds(tableName, ids) == 0 ? false : true;
    }

    @Override
    public TableColumn getTableColumn(String tableName, String tableId, String columnName) {
        return tableColumnDao.getTableColumn(tableName, tableId, columnName);
    }

    @Override
    public List<TableColumn> findTableColumn(String tableName, String tableId) {
        List<TableColumn> tableColumns = Lists.newArrayList();

        List<Map<String, Object>> mapList = tableColumnDao.findTableColumn(tableName, tableId);
        if (null != mapList && mapList.size() > 0){
            mapList.forEach(map -> {
                TableColumn tableColumn = new TableColumn();
                tableColumn.setTableName((String) map.get("tableName"));
                tableColumn.setColumnName((String) map.get("columnName"));
                tableColumn.setColumnValue((String) map.get("columnValue"));
                tableColumn.setDisplayed((Boolean) map.get("displayed"));
                tableColumns.add(tableColumn);
            });
        }

        return tableColumns;
    }


    @Override
    public TableColumnPARM entityToPARM(TableColumn tableColumn, Class<TableColumnPARM> clazz) {
        if (null == tableColumn){
            return null;
        }

        TableColumnPARM parm = new TableColumnPARM();
        BeanUtils.copyProperties(tableColumn, parm);

        Column column = columnService.getColumnByTableNameAndColumnName(tableColumn.getTableName(), tableColumn.getColumnName());
        if (null != column){
            parm.setColumn(columnService.entityToDTO(column, ColumnDTO.class));
        }

        return parm;
    }
}
