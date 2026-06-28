package com.rbac.v2.service.impl;
import com.rbac.v2.converter.PermissionServiceConverter;
import com.rbac.v2.entity.Permission;
import com.rbac.v2.entity.Role;
import com.rbac.v2.entity.RolePermission;
import com.rbac.v2.mapper.PermissionMapper;
import com.rbac.v2.mapper.RoleMapper;
import com.rbac.v2.mapper.RolePermissionMapper;
import com.rbac.v2.pojo.dto.PermissionDTO;
import com.rbac.v2.pojo.dto.RoleDTO;
import com.rbac.v2.service.PermissionService;
import com.rbac.v2.utils.Pagination;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
        Permission permission = PermissionServiceConverter.convertRequest(permissionDTO);
        int count = permissionMapper.insertPermission(permission);
        if (count == 0) {
            return null;
        }
        List<RolePermission> rolePermissionList = permissionDTO
                .getRoleDTOList()
                .stream()
                .map((RoleDTO rDTO)->{
                    return new RolePermission()
                            .setRoleId(rDTO.getId())
                            .setPermissionId(permission.getId())
                            .setStatus(permissionDTO.getStatus())
                            .setCreateTime(permissionDTO.getCreateTime())
                            .setUpdateTime(permissionDTO.getUpdateTime());
                })
                .collect(Collectors.toList());
        rolePermissionMapper.updateRolePermissionList(rolePermissionList);
        PermissionDTO data = selectPermissionById(permission.getId());
        return data;
    }
    @Override
    public List<PermissionDTO> insertPermissionList(List<PermissionDTO> permissionDTOList) {
        List<Permission> permissionList = PermissionServiceConverter.convertRequest(permissionDTOList);
        int count = permissionMapper.insertPermissionList(permissionList);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < permissionList.size(); i++) {
            Permission permission = permissionList.get(i);
            PermissionDTO permissionDTO = permissionDTOList.get(i);
            List<RolePermission> rolePermissionList = permissionDTO
                    .getRoleDTOList()
                    .stream()
                    .map((RoleDTO rDTO)->{
                        return new RolePermission()
                                .setRoleId(rDTO.getId())
                                .setPermissionId(permission.getId())
                                .setStatus(permissionDTO.getStatus())
                                .setCreateTime(permissionDTO.getCreateTime())
                                .setUpdateTime(permissionDTO.getUpdateTime());
                    })
                    .collect(Collectors.toList());
            rolePermissionMapper.updateRolePermissionList(rolePermissionList);
        }
        List<Integer> idList = permissionList
                .stream()
                .map((Permission p)->{
                    return p.getId();
                })
                .collect(Collectors.toList());
        List<PermissionDTO> data = selectPermissionByIdList(idList);
        return data;
    }
    @Override
    public PermissionDTO updatePermissionById(PermissionDTO permissionDTO) {
        Permission permission = PermissionServiceConverter.convertRequest(permissionDTO);
        int count = permissionMapper.updatePermissionById(permission);
        if (count == 0) {
            return null;
        }
        List<RolePermission> oldRolePermissionList = rolePermissionMapper.selectRolePermissionByPermissionId(permission.getId());
        List<RolePermission> disableRolePermissionList = accessDisableRolePermissionList(oldRolePermissionList, permissionDTO);
        List<RolePermission> enableRolePermissionList = accessEnableRolePermissionList(permissionDTO);
        List<RolePermission> rolePermissionList = Stream.concat(disableRolePermissionList.stream(), enableRolePermissionList.stream()).collect(Collectors.toList());
        if (rolePermissionList.isEmpty() == false) {
            rolePermissionMapper.updateRolePermissionList(rolePermissionList);
        }
        PermissionDTO data = selectPermissionById(permission.getId());
        return data;
    }
    @Override
    public List<PermissionDTO> updatePermissionByIdList(List<Integer> idList, PermissionDTO permissionDTO) {
        Permission permission = PermissionServiceConverter.convertRequest(permissionDTO);
        int count = permissionMapper.updatePermissionByIdList(idList, permission);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < idList.size(); i++) {
            Integer permissionId = idList.get(i);
            List<RolePermission> oldRolePermissionList = rolePermissionMapper.selectRolePermissionByPermissionId(permissionId);
            List<RolePermission> disableRolePermissionList = accessDisableRolePermissionList(oldRolePermissionList, permissionDTO);
            List<RolePermission> enableRolePermissionList = accessEnableRolePermissionList(permissionDTO.setId(permissionId));
            List<RolePermission> rolePermissionList = Stream.concat(disableRolePermissionList.stream(), enableRolePermissionList.stream()).collect(Collectors.toList());
            if (rolePermissionList.isEmpty() == false) {
                rolePermissionMapper.updateRolePermissionList(rolePermissionList);
            }
        }
        List<PermissionDTO> data = selectPermissionByIdList(idList);
        return data;
    }
    @Override
    public List<PermissionDTO> updatePermissionList(List<PermissionDTO> permissionDTOList) {
        List<Permission> permissionList = PermissionServiceConverter.convertRequest(permissionDTOList);
        int count = permissionMapper.updatePermissionList(permissionList);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < permissionList.size(); i++) {
            Permission permission = permissionList.get(i);
            PermissionDTO permissionDTO = permissionDTOList.get(i);
            List<RolePermission> oldRolePermissionList = rolePermissionMapper.selectRolePermissionByPermissionId(permission.getId());
            List<RolePermission> disableRolePermissionList = accessDisableRolePermissionList(oldRolePermissionList, permissionDTO);
            List<RolePermission> enableRolePermissionList = accessEnableRolePermissionList(permissionDTO);
            List<RolePermission> rolePermissionList = Stream.concat(disableRolePermissionList.stream(), enableRolePermissionList.stream()).collect(Collectors.toList());
            if (rolePermissionList.isEmpty() == false) {
                rolePermissionMapper.updateRolePermissionList(rolePermissionList);
            }
        }
        List<Integer> idList = permissionList
                .stream()
                .map((Permission p)->{
                    return p.getId();
                })
                .collect(Collectors.toList());
        List<PermissionDTO> data = selectPermissionByIdList(idList);
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
        PermissionDTO data = PermissionServiceConverter.convertResponse(permission, roleList);
        return data;
    }
    @Override
    public List<PermissionDTO> selectPermissionByIdList(List<Integer> idList) {
        List<PermissionDTO> data = Optional
                .ofNullable(permissionMapper.selectPermissionByIdList(idList))
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
                    return PermissionServiceConverter.convertResponse(p, rList);
                })
                .collect(Collectors.toList());
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
                    return PermissionServiceConverter.convertResponse(p, rList);
                })
                .collect(Collectors.toList());
        return data;
    }
    private List<RolePermission> accessDisableRolePermissionList(List<RolePermission> oldRolePermissionList, PermissionDTO permissionDTO) {
        return oldRolePermissionList
                .stream()
                .map((RolePermission rp)->{
                    return rp.getRoleId();
                })
                .filter((Integer rId)->{
                    return !permissionDTO
                            .getRoleDTOList()
                            .stream()
                            .map((RoleDTO rDTO)->{
                                return rDTO.getId();
                            })
                            .collect(Collectors.toSet())
                            .contains(rId);
                })
                .map((Integer rId)->{
                    return oldRolePermissionList
                            .stream()
                            .filter((RolePermission rp)->{
                                return Objects.equals(rp.getRoleId(), rId);
                            })
                            .map((RolePermission rp)->{
                                return rp
                                        .setStatus(Integer.MAX_VALUE)
                                        .setUpdateTime(permissionDTO.getUpdateTime());
                            })
                            .findFirst()
                            .get();
                })
                .collect(Collectors.toList());
    }
    private List<RolePermission> accessEnableRolePermissionList(PermissionDTO permissionDTO) {
        return permissionDTO
                .getRoleDTOList()
                .stream()
                .map((RoleDTO rDTO)->{
                    return new RolePermission()
                            .setRoleId(rDTO.getId())
                            .setPermissionId(permissionDTO.getId())
                            .setStatus(0)
                            .setCreateTime(permissionDTO.getUpdateTime())
                            .setUpdateTime(permissionDTO.getUpdateTime());
                })
                .collect(Collectors.toList());
    }
}