package com.lin.util.service.impl;


import com.lin.aijia.util.entity.ListData;
import com.lin.aijia.util.entity.SearchCriteria;
import com.lin.aijia.util.utils.ToolUtils;
import com.lin.util.dao.ResourceDao;
import com.lin.util.dto.ResourceDTO;
import com.lin.util.entity.Resource;
import com.lin.util.service.ResourceService;
import com.yyfly.common.entity.BaseEntity;
import com.yyfly.common.exception.GlobalException;
import com.yyfly.common.repository.BaseRepository;
import com.yyfly.common.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 资源 service impl
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;


    @Override
    protected BaseRepository<Resource> getDao() {
        return resourceDao;
    }


    @Override
    public ListData<Resource> findPageList(SearchCriteria searchCriteria) {
        return ListData.of(findAll(searchCriteria.buildSearchParams(), searchCriteria.buildPageRequest()));
    }

    @Override
    public Set<Resource> findMenuByUsername(String username) {
        if (StringUtils.isEmpty(username)){
            throw new GlobalException("用户名不能为空!");
        }
        return resourceDao.findMenuByUsername(username);
    }

    @Override
    public List<Resource> findPermissionByPidAndUsername(String pid, String username) {
        return resourceDao.findPermissionByPidAndUsername(pid, username);
    }

    @Override
    public ResourceDTO toDTO(Resource resource) {
        if (resource == null){
            return null;
        }

        ResourceDTO dto = new ResourceDTO();
        BeanUtils.copyProperties(resource, dto);

        if (BaseEntity.NORMAL == dto.getStatus()){
            dto.setEnabled(true);
        }
        if (BaseEntity.DISABLE == dto.getStatus()){
            dto.setEnabled(false);
        }

        return dto;
    }

    @Override
    public Resource toEntity(ResourceDTO dto) {
        Resource resource = new Resource();
        BeanUtils.copyProperties(dto, resource);
        resource = ToolUtils.enabledToEntity(resource, dto.isEnabled());
        return resource;
    }

    @Override
    public List<ResourceDTO> toDTOs(List<Resource> resources) {
        List<ResourceDTO> dtos = new ArrayList<>();
        if (resources != null && resources.size() > 0){
            resources.forEach(resource -> {
                ResourceDTO dto = toDTO(resource);
                dtos.add(dto);
            });
        }
        return dtos;
    }

    @Override
    public List<Resource> toEntitys(List<ResourceDTO> resourceDtos) {
        List<Resource> resources = new ArrayList<>();
        if (resourceDtos != null && resourceDtos.size() > 0){
            resourceDtos.forEach(resourceDto -> {
                Resource resource = toEntity(resourceDto);
                resources.add(resource);
            });
        }
        return resources;
    }
}
