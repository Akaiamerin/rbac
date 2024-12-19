package com.rbac.service.impl;
import com.rbac.entity.Permission;
import com.rbac.entity.Role;
import com.rbac.entity.RolePermission;
import com.rbac.mapper.PermissionMapper;
import com.rbac.mapper.RoleMapper;
import com.rbac.mapper.RolePermissionMapper;
import com.rbac.pojo.dto.PermissionDTO;
import com.rbac.pojo.dto.RoleDTO;
import com.rbac.service.PermissionService;
import com.rbac.utils.Pagination;
import jakarta.annotation.Resource;
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
        Permission permission = Optional
                .of(permissionDTO)
                .map((PermissionDTO pDTO)->{
                    return new Permission()
                            .setName(permissionDTO.getName());
                })
                .get();
        int count = permissionMapper.insertPermission(permission);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < permissionDTO.getRoleDTOList().size(); i++) {
            Integer roleId = permissionDTO.getRoleDTOList().get(i).getId();
            RolePermission rolePermission = new RolePermission()
                    .setRoleId(roleId)
                    .setPermissionId(permission.getId());
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
        Permission permission = Optional
                .of(permissionDTO)
                .map((PermissionDTO pDTO)->{
                    return new Permission()
                            .setId(permissionDTO.getId())
                            .setName(permissionDTO.getName());
                })
                .get();
        int count = permissionMapper.updatePermissionById(permission);
        if (count == 0) {
            return null;
        }
        rolePermissionMapper.deleteRolePermissionByPermissionId(permission.getId());
        for (int i = 0; i < permissionDTO.getRoleDTOList().size(); i++) {
            Integer roleId = permissionDTO.getRoleDTOList().get(i).getId();
            RolePermission rolePermission = new RolePermission()
                    .setRoleId(roleId)
                    .setPermissionId(permission.getId());
            rolePermissionMapper.insertRolePermission(rolePermission);
        }
        PermissionDTO data = selectPermissionById(permission.getId());
        return data;
    }
    @Override
    public PermissionDTO selectPermissionById(Integer id) {
        Permission permission = permissionMapper.selectPermissionById(id);
        PermissionDTO data = Optional
                .ofNullable(permission)
                .map((Permission p)->{
                    List<RoleDTO> rDTOList = rolePermissionMapper
                            .selectRolePermissionByPermissionId(p.getId())
                            .stream()
                            .map((RolePermission rp)->{
                                return Optional
                                        .ofNullable(roleMapper.selectRoleById(rp.getRoleId()))
                                        .map((Role r)->{
                                            return new RoleDTO()
                                                    .setId(r.getId())
                                                    .setName(r.getName());
                                        })
                                        .orElseGet(()->{
                                            return new RoleDTO()
                                                    .setId(rp.getRoleId());
                                        });
                            })
                            .collect(Collectors.toList());
                    return new PermissionDTO()
                            .setId(p.getId())
                            .setName(p.getName())
                            .setRoleDTOList(rDTOList);
                })
                .orElse(null);
        return data;
    }
    @Override
    public List<PermissionDTO> selectPermissionByPagination(Pagination pagination) {
        List<Permission> permissionList = permissionMapper.selectPermissionByPagination(pagination);
        List<PermissionDTO> data = permissionList
                .stream()
                .map((Permission p)->{
                    List<RoleDTO> rDTOList = rolePermissionMapper
                            .selectRolePermissionByPermissionId(p.getId())
                            .stream()
                            .map((RolePermission rp)->{
                                return Optional
                                        .ofNullable(roleMapper.selectRoleById(rp.getRoleId()))
                                        .map((Role r)->{
                                            return new RoleDTO()
                                                    .setId(r.getId())
                                                    .setName(r.getName());
                                        })
                                        .orElseGet(()->{
                                            return new RoleDTO()
                                                    .setId(rp.getRoleId());
                                        });
                            })
                            .collect(Collectors.toList());
                    return new PermissionDTO()
                            .setId(p.getId())
                            .setName(p.getName())
                            .setRoleDTOList(rDTOList);
                })
                .collect(Collectors.toList());
        return data;
    }
}