package com.aijia.util.service.impl;


import com.aijia.config.properties.SeerJpaProperties;
import com.aijia.repository.JpaBaseRepository;
import com.aijia.service.impl.JpaBaseServiceImpl;
import com.aijia.util.dao.ColumnDao;
import com.aijia.util.dto.ColumnDTO;
import com.aijia.util.entity.Column;
import com.aijia.util.entity.Table;
import com.aijia.util.parm.ColumnPARM;
import com.aijia.util.parm.ColumnSequencePARM;
import com.aijia.util.service.ColumnService;
import com.aijia.util.service.TableService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * 列 service impl
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ColumnServiceImpl extends JpaBaseServiceImpl<Column, ColumnPARM, ColumnDTO> implements ColumnService {

    @Autowired
    private SeerJpaProperties seerJpaProperties;
    @Autowired
    private ColumnDao columnDao;
    @Autowired
    private TableService tableService;

    @Override
    protected JpaBaseRepository<Column> getJpaBaseRepository() {
        return columnDao;
    }

    @Override
    protected SeerJpaProperties getSeerJpaProperties() {
        return seerJpaProperties;
    }

    @Override
    public List<Column> findColumnsByTableName(String tableName) {
        return columnDao.findByTableName(tableName);
    }

    @Override
    public Column getColumnByTableNameAndColumnName(String tableName, String columnName) {
        return columnDao.getByTableNameAndName(tableName, columnName);
    }

    @Override
    public Boolean existsByName(String tableName, String columnName) {
        return columnDao.countByTableNameAndName(tableName, columnName) == 0 ? false : true;
    }

    @Override
    public Boolean updateRequired(String id, Boolean require) {
        return columnDao.updateRequired(id, require) == 0 ? false : true;
    }

    @Override
    public Boolean updateDisplayed(String id, Boolean display) {
        return columnDao.updateDisplayed(id, display) == 0 ? false : true;
    }

    @Override
    public Boolean updateEnabled(String id, Boolean enable) {
        return columnDao.updateEnabled(id, enable) == 0 ? false : true;
    }

    @Override
    public Boolean updateSequence(String id, Integer sequence) {
        return columnDao.updateSequence(id, sequence) == 0 ? false : true;
    }

    @Override
    public Boolean updateSequence(Set<ColumnSequencePARM> parms) {
        parms.forEach(columnSequence -> {
            updateSequence(columnSequence.getId(), columnSequence.getSequence());
        });
        return true;
    }


    @Override
    public ColumnDTO entityToDTO(Column column, Class<ColumnDTO> clazz) {
        if (null == column){
            return null;
        }

        ColumnDTO dto = new ColumnDTO();
        BeanUtils.copyProperties(column, dto);

        Table table = tableService.getTableByName(column.getTableName());
        if (null != table){
            dto.setTableDescription(table.getDescription());
        }

        return dto;
    }
}
