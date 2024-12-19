package com.rbac.service.impl;
import com.rbac.entity.Permission;
import com.rbac.entity.Role;
import com.rbac.entity.RolePermission;
import com.rbac.entity.User;
import com.rbac.entity.UserRole;
import com.rbac.mapper.PermissionMapper;
import com.rbac.mapper.RoleMapper;
import com.rbac.mapper.RolePermissionMapper;
import com.rbac.mapper.UserMapper;
import com.rbac.mapper.UserRoleMapper;
import com.rbac.pojo.dto.PermissionDTO;
import com.rbac.pojo.dto.RoleDTO;
import com.rbac.pojo.dto.UserDTO;
import com.rbac.service.RoleService;
import com.rbac.utils.Pagination;
import jakarta.annotation.Resource;
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
        Role role = Optional
                .of(roleDTO)
                .map((RoleDTO rDTO)->{
                    return new Role()
                            .setName(roleDTO.getName());
                })
                .get();
        int count = roleMapper.insertRole(role);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < roleDTO.getPermissionDTOList().size(); i++) {
            Integer permissionId = roleDTO.getPermissionDTOList().get(i).getId();
            RolePermission rolePermission = new RolePermission()
                    .setRoleId(role.getId())
                    .setPermissionId(permissionId);
            rolePermissionMapper.insertRolePermission(rolePermission);
        }
        for (int i = 0; i < roleDTO.getUserDTOList().size(); i++) {
            Integer userId = roleDTO.getUserDTOList().get(i).getId();
            UserRole userRole = new UserRole()
                    .setUserId(userId)
                    .setRoleId(role.getId());
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
        Role role = Optional
                .of(roleDTO)
                .map((RoleDTO rDTO)->{
                    return new Role()
                            .setId(roleDTO.getId())
                            .setName(roleDTO.getName());
                })
                .get();
        int count = roleMapper.updateRoleById(role);
        if (count == 0) {
            return null;
        }
        rolePermissionMapper.deleteRolePermissionByRoleId(role.getId());
        for (int i = 0; i < roleDTO.getPermissionDTOList().size(); i++) {
            Integer permissionId = roleDTO.getPermissionDTOList().get(i).getId();
            RolePermission rolePermission = new RolePermission()
                    .setRoleId(role.getId())
                    .setPermissionId(permissionId);
            rolePermissionMapper.insertRolePermission(rolePermission);
        }
        userRoleMapper.deleteUserRoleByRoleId(role.getId());
        for (int i = 0; i < roleDTO.getUserDTOList().size(); i++) {
            Integer userId = roleDTO.getUserDTOList().get(i).getId();
            UserRole userRole = new UserRole()
                    .setUserId(userId)
                    .setRoleId(role.getId());
            userRoleMapper.insertUserRole(userRole);
        }
        RoleDTO data = selectRoleById(role.getId());
        return data;
    }
    @Override
    public RoleDTO selectRoleById(Integer id) {
        Role role = roleMapper.selectRoleById(id);
        RoleDTO data = Optional
                .ofNullable(role)
                .map((Role r)->{
                    List<PermissionDTO> pDTOList = rolePermissionMapper
                            .selectRolePermissionByURoleId(r.getId())
                            .stream()
                            .map((RolePermission rp)->{
                                return Optional
                                        .ofNullable(permissionMapper.selectPermissionById(rp.getPermissionId()))
                                        .map((Permission p)->{
                                            return new PermissionDTO()
                                                    .setId(p.getId())
                                                    .setName(p.getName());
                                        })
                                        .orElseGet(()->{
                                            return new PermissionDTO()
                                                    .setId(rp.getPermissionId());
                                        });
                            })
                            .collect(Collectors.toList());
                    List<UserDTO> uDTOList = userRoleMapper
                            .selectUserRoleByRoleId(r.getId())
                            .stream()
                            .map((UserRole ur)->{
                                return Optional
                                        .ofNullable(userMapper.selectUserById(ur.getUserId()))
                                        .map((User u)->{
                                            return new UserDTO()
                                                    .setId(u.getId())
                                                    .setUsername(u.getUsername());
                                        })
                                        .orElseGet(()->{
                                            return new UserDTO()
                                                    .setId(ur.getId());
                                        });
                            })
                            .collect(Collectors.toList());
                    return new RoleDTO()
                            .setId(r.getId())
                            .setName(r.getName())
                            .setPermissionDTOList(pDTOList)
                            .setUserDTOList(uDTOList);
                })
                .orElse(null);
        return data;
    }
    @Override
    public List<RoleDTO> selectRoleByPagination(Pagination pagination) {
        List<Role> roleList = roleMapper.selectRoleByPagination(pagination);
        List<RoleDTO> data = roleList
                .stream()
                .map((Role r)->{
                    List<PermissionDTO> pDTOList = rolePermissionMapper
                            .selectRolePermissionByURoleId(r.getId())
                            .stream()
                            .map((RolePermission rp)->{
                                return Optional
                                        .ofNullable(permissionMapper.selectPermissionById(rp.getPermissionId()))
                                        .map((Permission p)->{
                                            return new PermissionDTO()
                                                    .setId(p.getId())
                                                    .setName(p.getName());
                                        })
                                        .orElseGet(()->{
                                            return new PermissionDTO()
                                                    .setId(rp.getPermissionId());
                                        });
                            })
                            .collect(Collectors.toList());
                    List<UserDTO> uDTOList = userRoleMapper
                            .selectUserRoleByRoleId(r.getId())
                            .stream()
                            .map((UserRole ur)->{
                                return Optional
                                        .ofNullable(userMapper.selectUserById(ur.getUserId()))
                                        .map((User u)->{
                                            return new UserDTO()
                                                    .setId(u.getId())
                                                    .setUsername(u.getUsername());
                                        })
                                        .orElseGet(()->{
                                            return new UserDTO()
                                                    .setId(ur.getId());
                                        });
                            })
                            .collect(Collectors.toList());
                    return new RoleDTO()
                            .setId(r.getId())
                            .setName(r.getName())
                            .setPermissionDTOList(pDTOList)
                            .setUserDTOList(uDTOList);
                })
                .collect(Collectors.toList());
        return data;
    }
}