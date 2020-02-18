package com.lin.util.service.impl;


import com.lin.aijia.service.DictService;

import com.lin.util.dao.RoleDao;
import com.lin.util.dto.RoleDTO;
import com.lin.util.entity.ListData;
import com.lin.util.entity.Resource;
import com.lin.util.entity.Role;
import com.lin.util.entity.SearchCriteria;
import com.lin.util.service.ResourceService;
import com.lin.util.service.RoleService;
import com.lin.util.utils.ToolUtils;
import com.yyfly.common.entity.BaseEntity;
import com.yyfly.common.repository.BaseRepository;
import com.yyfly.common.service.impl.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色 service impl
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private DictService dictService;


    @Override
    protected BaseRepository<Role> getDao() {
        return roleDao;
    }


    @Override
    public ListData<Role> findPageList(SearchCriteria searchCriteria) {
        return ListData.of(findAll(searchCriteria.buildSearchParams(), searchCriteria.buildPageRequest()));
    }

    @Override
    public boolean existsByRoleName(String roleName) {
        return roleDao.countByRoleName(roleName) == 0 ? false : true;
    }

    @Override
    public boolean existsByRoleNameWithOutId(String roleName, String id) {
        return roleDao.countByRoleNameWithOutId(roleName, id) == 0 ? false : true;
    }

    @Override
    public boolean updateStatusByIds(Set<String> ids, int status) {
        return roleDao.updateStatusByIds(ids, status) == 0 ? false : true;
    }

    @Override
    public List<String> findRoleIdsByUsername(String username) {
        return roleDao.findRoleIdsByUsername(username);
    }

    @Override
    public RoleDTO toDTO(Role role) {
        if (role == null){
            return null;
        }

        RoleDTO dto = new RoleDTO();
        BeanUtils.copyProperties(role, dto);

        dto.setResourceIds(role.getResourceIds());
        dto.setResourceNames(role.getResourceNames());

        if (BaseEntity.NORMAL == dto.getStatus()){
            dto.setEnabled(true);
        }
        if (BaseEntity.DISABLE == dto.getStatus()){
            dto.setEnabled(false);
        }

        //角色组
        dto.setGroupValue(dictService.getValue(Role.class.getSimpleName(), Role.DICT_GROUP, dto.getGroupCode()));

        return dto;
    }

    @Override
    public Role toEntity(RoleDTO dto) {
        Role role = new Role();
        BeanUtils.copyProperties(dto, role);
        role = ToolUtils.enabledToEntity(role, dto.isEnabled());

        Set<String> resourceIds = dto.getResourceIds();
        if (resourceIds != null && resourceIds.size() > 0){
            Set<Resource> resources = new HashSet<>(resourceIds.size());
            for (String resourceId : resourceIds){
                Resource resource = resourceService.findById(resourceId);
                resources.add(resource);
            }
            role.setResources(resources);
        }

        return role;
    }

    @Override
    public List<RoleDTO> toDTOs(List<Role> roles) {
        List<RoleDTO> dtos = new ArrayList<>();
        if (roles != null && roles.size() > 0){
            roles.forEach(role -> {
                RoleDTO dto = toDTO(role);
                dtos.add(dto);
            });
        }
        return dtos;
    }

    @Override
    public List<Role> toEntitys(List<RoleDTO> roleDtos) {
        List<Role> roles = new ArrayList<>();
        if (roleDtos != null && roleDtos.size() > 0){
            roleDtos.forEach(roleDto -> {
                Role role = toEntity(roleDto);
                roles.add(role);
            });
        }
        return roles;
    }
}
