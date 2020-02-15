package com.aijia.util.service.impl;


import com.aijia.config.properties.SeerJpaProperties;
import com.aijia.repository.JpaBaseRepository;
import com.aijia.service.impl.JpaBaseServiceImpl;
import com.aijia.util.dao.TableDao;
import com.aijia.util.dto.TableDTO;
import com.aijia.util.entity.Table;
import com.aijia.util.parm.TablePARM;
import com.aijia.util.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 自定义表 service impl
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class TableServiceImpl extends JpaBaseServiceImpl<Table, TablePARM, TableDTO> implements TableService {

    /**
     * Seer jpa properties
     */
    @Autowired
    private SeerJpaProperties seerJpaProperties;
    /**
     * Custom table dao
     */
    @Autowired
    private TableDao tableDao;

    @Override
    protected JpaBaseRepository<Table> getJpaBaseRepository() {
        return tableDao;
    }

    @Override
    protected SeerJpaProperties getSeerJpaProperties() {
        return seerJpaProperties;
    }

    @Override
    public Boolean enableCustomization(String name) {
        return tableDao.updateCustomization(name, true) == 0 ? false : true;
    }

    @Override
    public Boolean disableCustomization(String name) {
        return tableDao.updateCustomization(name, false) == 0 ? false : true;
    }

    @Override
    public Table getTableByName(String name) {
        return tableDao.getByName(name);
    }

    @Override
    public Boolean existsByName(String name) {
        return tableDao.countByName(name) == 0 ? false : true;
    }
}
