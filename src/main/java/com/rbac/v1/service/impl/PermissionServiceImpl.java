package com.rbac.v1.service.impl;
import com.rbac.v1.converter.PermissionServiceConverter;
import com.rbac.v1.entity.Permission;
import com.rbac.v1.entity.Role;
import com.rbac.v1.entity.RolePermission;
import com.rbac.v1.mapper.PermissionMapper;
import com.rbac.v1.mapper.RoleMapper;
import com.rbac.v1.mapper.RolePermissionMapper;
import com.rbac.v1.pojo.dto.PermissionDTO;
import com.rbac.v1.pojo.dto.RoleDTO;
import com.rbac.v1.service.PermissionService;
import com.rbac.v1.utils.Pagination;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Override
    public PermissionDTO insertPermission(PermissionDTO permissionDTO) {
        Permission permission = PermissionServiceConverter.convert(permissionDTO);
        int count = permissionMapper.insertPermission(permission);
        if (count == 0) {
            return null;
        }
        permissionDTO.setId(permission.getId());
        for (int i = 0; i < permissionDTO.getRoleDTOList().size(); i++) {
            RoleDTO roleDTO = permissionDTO.getRoleDTOList().get(i);
            RolePermission rolePermission = new RolePermission()
                    .setRoleId(roleDTO.getId())
                    .setPermissionId(permissionDTO.getId());
            rolePermissionMapper.insertRolePermission(rolePermission);
        }
        PermissionDTO data = selectPermissionById(permission.getId());
        return data;
    }
    @Override
    public PermissionDTO deletePermissionById(Integer id) {
        PermissionDTO data = selectPermissionById(id);
        int count = permissionMapper.deletePermissionById(id);
        if (count == 0) {
            return null;
        }
        rolePermissionMapper.deleteRolePermissionByPermissionId(id);
        return data;
    }
    @Override
    public PermissionDTO updatePermissionById(PermissionDTO permissionDTO) {
        Permission permission = PermissionServiceConverter.convert(permissionDTO);
        int count = permissionMapper.updatePermissionById(permission);
        if (count == 0) {
            return null;
        }
        rolePermissionMapper.deleteRolePermissionByPermissionId(permission.getId());
        for (int i = 0; i < permissionDTO.getRoleDTOList().size(); i++) {
            RoleDTO roleDTO = permissionDTO.getRoleDTOList().get(i);
            RolePermission rolePermission = new RolePermission()
                    .setRoleId(roleDTO.getId())
                    .setPermissionId(permissionDTO.getId());
            rolePermissionMapper.insertRolePermission(rolePermission);
        }
        PermissionDTO data = selectPermissionById(permission.getId());
        return data;
    }
    @Override
    public PermissionDTO selectPermissionById(Integer id) {
        Permission permission = permissionMapper.selectPermissionById(id);
        List<Role> roleList = Optional
                .ofNullable(rolePermissionMapper.selectRolePermissionByPermissionId(permission.getId()))
                .orElse(new ArrayList<>())
                .stream()
                .map((RolePermission rp)->{
                    return roleMapper.selectRoleById(rp.getRoleId());
                })
                .collect(Collectors.toList());
        PermissionDTO data = PermissionServiceConverter.convert(permission, roleList);
        return data;
    }
    @Override
    public List<PermissionDTO> selectPermissionByPagination(Pagination pagination) {
        List<PermissionDTO> data = Optional
                .ofNullable(permissionMapper.selectPermissionByPagination(pagination))
                .orElse(new ArrayList<>())
                .stream()
                .map((Permission p)->{
                    List<Role> rList = Optional
                            .ofNullable(rolePermissionMapper.selectRolePermissionByPermissionId(p.getId()))
                            .orElse(new ArrayList<>())
                            .stream()
                            .map((RolePermission rp)->{
                                return roleMapper.selectRoleById(rp.getRoleId());
                            })
                            .collect(Collectors.toList());
                    return PermissionServiceConverter.convert(p, rList);
                })
                .collect(Collectors.toList());
        return data;
    }
}