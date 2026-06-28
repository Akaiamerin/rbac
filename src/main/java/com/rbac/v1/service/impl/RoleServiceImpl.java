package com.rbac.v1.service.impl;
import com.rbac.v1.converter.RoleServiceConverter;
import com.rbac.v1.entity.Permission;
import com.rbac.v1.entity.Role;
import com.rbac.v1.entity.RolePermission;
import com.rbac.v1.entity.User;
import com.rbac.v1.entity.UserRole;
import com.rbac.v1.mapper.PermissionMapper;
import com.rbac.v1.mapper.RoleMapper;
import com.rbac.v1.mapper.RolePermissionMapper;
import com.rbac.v1.mapper.UserMapper;
import com.rbac.v1.mapper.UserRoleMapper;
import com.rbac.v1.pojo.dto.PermissionDTO;
import com.rbac.v1.pojo.dto.RoleDTO;
import com.rbac.v1.pojo.dto.UserDTO;
import com.rbac.v1.service.RoleService;
import com.rbac.v1.utils.Pagination;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Override
    public RoleDTO insertRole(RoleDTO roleDTO) {
        Role role = RoleServiceConverter.convert(roleDTO);
        int count = roleMapper.insertRole(role);
        if (count == 0) {
            return null;
        }
        roleDTO.setId(role.getId());
        for (int i = 0; i < roleDTO.getPermissionDTOList().size(); i++) {
            PermissionDTO permissionDTO = roleDTO.getPermissionDTOList().get(i);
            RolePermission rolePermission = new RolePermission()
                    .setRoleId(roleDTO.getId())
                    .setPermissionId(permissionDTO.getId());
            rolePermissionMapper.insertRolePermission(rolePermission);
        }
        for (int i = 0; i < roleDTO.getUserDTOList().size(); i++) {
            UserDTO userDTO = roleDTO.getUserDTOList().get(i);
            UserRole userRole = new UserRole()
                    .setUserId(userDTO.getId())
                    .setRoleId(roleDTO.getId());
            userRoleMapper.insertUserRole(userRole);
        }
        RoleDTO data = selectRoleById(role.getId());
        return data;
    }
    @Override
    public RoleDTO deleteRoleById(Integer id) {
        RoleDTO data = selectRoleById(id);
        int count = roleMapper.deleteRoleById(id);
        if (count == 0) {
            return null;
        }
        rolePermissionMapper.deleteRolePermissionByRoleId(id);
        userRoleMapper.deleteUserRoleByRoleId(id);
        return data;
    }
    @Override
    public RoleDTO updateRoleById(RoleDTO roleDTO) {
        Role role = RoleServiceConverter.convert(roleDTO);
        int count = roleMapper.updateRoleById(role);
        if (count == 0) {
            return null;
        }
        rolePermissionMapper.deleteRolePermissionByRoleId(role.getId());
        for (int i = 0; i < roleDTO.getPermissionDTOList().size(); i++) {
            PermissionDTO permissionDTO = roleDTO.getPermissionDTOList().get(i);
            RolePermission rolePermission = new RolePermission()
                    .setRoleId(roleDTO.getId())
                    .setPermissionId(permissionDTO.getId());
            rolePermissionMapper.insertRolePermission(rolePermission);
        }
        userRoleMapper.deleteUserRoleByRoleId(role.getId());
        for (int i = 0; i < roleDTO.getUserDTOList().size(); i++) {
            UserDTO userDTO = roleDTO.getUserDTOList().get(i);
            UserRole userRole = new UserRole()
                    .setUserId(userDTO.getId())
                    .setRoleId(roleDTO.getId());
            userRoleMapper.insertUserRole(userRole);
        }
        RoleDTO data = selectRoleById(role.getId());
        return data;
    }
    @Override
    public RoleDTO selectRoleById(Integer id) {
        Role role = roleMapper.selectRoleById(id);
        List<Permission> permissionList = Optional
                .ofNullable(rolePermissionMapper.selectRolePermissionByRoleId(role.getId()))
                .orElse(new ArrayList<>())
                .stream()
                .map((RolePermission rp)->{
                    return permissionMapper.selectPermissionById(rp.getPermissionId());
                })
                .collect(Collectors.toList());
        List<User> userList = Optional
                .ofNullable(userRoleMapper.selectUserRoleByRoleId(role.getId()))
                .orElse(new ArrayList<>())
                .stream()
                .map((UserRole ur)->{
                    return userMapper.selectUserById(ur.getUserId());
                })
                .collect(Collectors.toList());
        RoleDTO data = RoleServiceConverter.convert(role, permissionList, userList);
        return data;
    }
    @Override
    public List<RoleDTO> selectRoleByPagination(Pagination pagination) {
        List<RoleDTO> data = Optional
                .ofNullable(roleMapper.selectRoleByPagination(pagination))
                .orElse(new ArrayList<>())
                .stream()
                .map((Role r)->{
                    List<Permission> pList = Optional
                            .ofNullable(rolePermissionMapper.selectRolePermissionByRoleId(r.getId()))
                            .orElse(new ArrayList<>())
                            .stream()
                            .map((RolePermission rp)->{
                                return permissionMapper.selectPermissionById(rp.getPermissionId());
                            })
                            .collect(Collectors.toList());
                    List<User> uList = Optional
                            .ofNullable(userRoleMapper.selectUserRoleByRoleId(r.getId()))
                            .orElse(new ArrayList<>())
                            .stream()
                            .map((UserRole ur)->{
                                return userMapper.selectUserById(ur.getUserId());
                            })
                            .collect(Collectors.toList());
                    return RoleServiceConverter.convert(r, pList, uList);
                })
                .collect(Collectors.toList());
        return data;
    }
}