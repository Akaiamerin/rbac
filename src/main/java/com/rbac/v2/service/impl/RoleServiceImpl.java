package com.rbac.v2.service.impl;
import com.rbac.v2.converter.RoleServiceConverter;
import com.rbac.v2.entity.Permission;
import com.rbac.v2.entity.Role;
import com.rbac.v2.entity.RolePermission;
import com.rbac.v2.entity.User;
import com.rbac.v2.entity.UserRole;
import com.rbac.v2.mapper.PermissionMapper;
import com.rbac.v2.mapper.RoleMapper;
import com.rbac.v2.mapper.RolePermissionMapper;
import com.rbac.v2.mapper.UserMapper;
import com.rbac.v2.mapper.UserRoleMapper;
import com.rbac.v2.pojo.dto.PermissionDTO;
import com.rbac.v2.pojo.dto.RoleDTO;
import com.rbac.v2.pojo.dto.UserDTO;
import com.rbac.v2.service.RoleService;
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
        Role role = RoleServiceConverter.convertRequest(roleDTO);
        int count = roleMapper.insertRole(role);
        if (count == 0) {
            return null;
        }
        List<RolePermission> rolePermissionList = roleDTO
                .getPermissionDTOList()
                .stream()
                .map((PermissionDTO pDTO)->{
                    return new RolePermission()
                            .setRoleId(role.getId())
                            .setPermissionId(pDTO.getId())
                            .setStatus(roleDTO.getStatus())
                            .setCreateTime(roleDTO.getCreateTime())
                            .setUpdateTime(roleDTO.getUpdateTime());
                })
                .collect(Collectors.toList());
        rolePermissionMapper.updateRolePermissionList(rolePermissionList);
        List<UserRole> userRoleList = roleDTO
                .getUserDTOList()
                .stream()
                .map((UserDTO uDTO)->{
                    return new UserRole()
                            .setUserId(uDTO.getId())
                            .setRoleId(role.getId())
                            .setStatus(roleDTO.getStatus())
                            .setCreateTime(roleDTO.getCreateTime())
                            .setUpdateTime(roleDTO.getUpdateTime());
                })
                .collect(Collectors.toList());
        userRoleMapper.updateUserRoleList(userRoleList);
        RoleDTO data = selectRoleById(role.getId());
        return data;
    }
    @Override
    public List<RoleDTO> insertRoleList(List<RoleDTO> roleDTOList) {
        List<Role> roleList = RoleServiceConverter.convertRequest(roleDTOList);
        int count = roleMapper.insertRoleList(roleList);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < roleList.size(); i++) {
            Role role = roleList.get(i);
            RoleDTO roleDTO = roleDTOList.get(i);
            List<RolePermission> rolePermissionList = roleDTO
                    .getPermissionDTOList()
                    .stream()
                    .map((PermissionDTO pDTO)->{
                        return new RolePermission()
                                .setRoleId(role.getId())
                                .setPermissionId(pDTO.getId())
                                .setStatus(roleDTO.getStatus())
                                .setCreateTime(roleDTO.getCreateTime())
                                .setUpdateTime(roleDTO.getUpdateTime());
                    })
                    .collect(Collectors.toList());
            rolePermissionMapper.updateRolePermissionList(rolePermissionList);
            List<UserRole> userRoleList = roleDTO
                    .getUserDTOList()
                    .stream()
                    .map((UserDTO uDTO)->{
                        return new UserRole()
                                .setUserId(uDTO.getId())
                                .setRoleId(role.getId())
                                .setStatus(roleDTO.getStatus())
                                .setCreateTime(roleDTO.getCreateTime())
                                .setUpdateTime(roleDTO.getUpdateTime());
                    })
                    .collect(Collectors.toList());
            userRoleMapper.updateUserRoleList(userRoleList);
        }
        List<Integer> idList = roleList
                .stream()
                .map((Role r)->{
                    return r.getId();
                })
                .collect(Collectors.toList());
        List<RoleDTO> data = selectRoleByIdList(idList);
        return data;
    }
    @Override
    public RoleDTO updateRoleById(RoleDTO roleDTO) {
        Role role = RoleServiceConverter.convertRequest(roleDTO);
        int count = roleMapper.updateRoleById(role);
        if (count == 0) {
            return null;
        }
        List<RolePermission> oldRolePermissionList = rolePermissionMapper.selectRolePermissionByRoleId(role.getId());
        List<RolePermission> disableRolePermissionList = accessDisableRolePermissionList(oldRolePermissionList, roleDTO);
        List<RolePermission> enableRolePermissionList = accessEnableRolePermissionList(roleDTO);
        List<RolePermission> rolePermissionList = Stream.concat(disableRolePermissionList.stream(), enableRolePermissionList.stream()).collect(Collectors.toList());
        if (rolePermissionList.isEmpty() == false) {
            rolePermissionMapper.updateRolePermissionList(rolePermissionList);
        }
        List<UserRole> oldUserRoleList = userRoleMapper.selectUserRoleByRoleId(role.getId());
        List<UserRole> disableUserRoleList = accessDisableUserRoleList(oldUserRoleList, roleDTO);
        List<UserRole> enableUserRoleList = accessEnableUserRoleList(roleDTO);
        List<UserRole> userRoleList = Stream.concat(disableUserRoleList.stream(), enableUserRoleList.stream()).collect(Collectors.toList());
        if (userRoleList.isEmpty() == false) {
            userRoleMapper.updateUserRoleList(userRoleList);
        }
        RoleDTO data = selectRoleById(role.getId());
        return data;
    }
    @Override
    public List<RoleDTO> updateRoleByIdList(List<Integer> idList, RoleDTO roleDTO) {
        Role role = RoleServiceConverter.convertRequest(roleDTO);
        int count = roleMapper.updateRoleByIdList(idList, role);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < idList.size(); i++) {
            Integer roleId = idList.get(i);
            List<RolePermission> oldRolePermissionList = rolePermissionMapper.selectRolePermissionByRoleId(roleId);
            List<RolePermission> disableRolePermissionList = accessDisableRolePermissionList(oldRolePermissionList, roleDTO);
            List<RolePermission> enableRolePermissionList = accessEnableRolePermissionList(roleDTO.setId(roleId));
            List<RolePermission> rolePermissionList = Stream.concat(disableRolePermissionList.stream(), enableRolePermissionList.stream()).collect(Collectors.toList());
            if (rolePermissionList.isEmpty() == false) {
                rolePermissionMapper.updateRolePermissionList(rolePermissionList);
            }
            List<UserRole> oldUserRoleList = userRoleMapper.selectUserRoleByRoleId(roleId);
            List<UserRole> disableUserRoleList = accessDisableUserRoleList(oldUserRoleList, roleDTO);
            List<UserRole> enableUserRoleList = accessEnableUserRoleList(roleDTO.setId(roleId));
            List<UserRole> userRoleList = Stream.concat(disableUserRoleList.stream(), enableUserRoleList.stream()).collect(Collectors.toList());
            if (userRoleList.isEmpty() == false) {
                userRoleMapper.updateUserRoleList(userRoleList);
            }
        }
        List<RoleDTO> data = selectRoleByIdList(idList);
        return data;
    }
    @Override
    public List<RoleDTO> updateRoleList(List<RoleDTO> roleDTOList) {
        List<Role> roleList = RoleServiceConverter.convertRequest(roleDTOList);
        int count = roleMapper.updateRoleList(roleList);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < roleList.size(); i++) {
            Role role = roleList.get(i);
            RoleDTO roleDTO = roleDTOList.get(i);
            List<RolePermission> oldRolePermissionList = rolePermissionMapper.selectRolePermissionByRoleId(role.getId());
            List<RolePermission> disableRolePermissionList = accessDisableRolePermissionList(oldRolePermissionList, roleDTO);
            List<RolePermission> enableRolePermissionList = accessEnableRolePermissionList(roleDTO);
            List<RolePermission> rolePermissionList = Stream.concat(disableRolePermissionList.stream(), enableRolePermissionList.stream()).collect(Collectors.toList());
            if (rolePermissionList.isEmpty() == false) {
                rolePermissionMapper.updateRolePermissionList(rolePermissionList);
            }
            List<UserRole> oldUserRoleList = userRoleMapper.selectUserRoleByRoleId(role.getId());
            List<UserRole> disableUserRoleList = accessDisableUserRoleList(oldUserRoleList, roleDTO);
            List<UserRole> enableUserRoleList = accessEnableUserRoleList(roleDTO);
            List<UserRole> userRoleList = Stream.concat(disableUserRoleList.stream(), enableUserRoleList.stream()).collect(Collectors.toList());
            if (userRoleList.isEmpty() == false) {
                userRoleMapper.updateUserRoleList(userRoleList);
            }
        }
        List<Integer> idList = roleList
                .stream()
                .map((Role r)->{
                    return r.getId();
                })
                .collect(Collectors.toList());
        List<RoleDTO> data = selectRoleByIdList(idList);
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
        RoleDTO data = RoleServiceConverter.convertResponse(role, permissionList, userList);
        return data;
    }
    @Override
    public List<RoleDTO> selectRoleByIdList(List<Integer> idList) {
        List<RoleDTO> data = Optional
                .ofNullable(roleMapper.selectRoleByIdList(idList))
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
                    return RoleServiceConverter.convertResponse(r, pList, uList);
                })
                .collect(Collectors.toList());
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
                    return RoleServiceConverter.convertResponse(r, pList, uList);
                })
                .collect(Collectors.toList());
        return data;
    }
    private List<RolePermission> accessDisableRolePermissionList(List<RolePermission> oldRolePermissionList, RoleDTO roleDTO) {
        return oldRolePermissionList
                .stream()
                .map((RolePermission rp)->{
                    return rp.getPermissionId();
                })
                .filter((Integer pId)->{
                    return !roleDTO
                            .getPermissionDTOList()
                            .stream()
                            .map((PermissionDTO pDTO)->{
                                return pDTO.getId();
                            })
                            .collect(Collectors.toSet())
                            .contains(pId);
                })
                .map((Integer pId)->{
                    return oldRolePermissionList
                            .stream()
                            .filter((RolePermission rp)->{
                                return Objects.equals(rp.getPermissionId(), pId);
                            })
                            .map((RolePermission rp)->{
                                return rp
                                        .setStatus(Integer.MAX_VALUE)
                                        .setUpdateTime(roleDTO.getUpdateTime());
                            })
                            .findFirst()
                            .get();
                })
                .collect(Collectors.toList());
    }
    private List<RolePermission> accessEnableRolePermissionList(RoleDTO roleDTO) {
        return roleDTO
                .getPermissionDTOList()
                .stream()
                .map((PermissionDTO pDTO)->{
                    return new RolePermission()
                            .setRoleId(roleDTO.getId())
                            .setPermissionId(pDTO.getId())
                            .setStatus(0)
                            .setCreateTime(roleDTO.getUpdateTime())
                            .setUpdateTime(roleDTO.getUpdateTime());
                })
                .collect(Collectors.toList());
    }
    private List<UserRole> accessDisableUserRoleList(List<UserRole> oldUserRoleList, RoleDTO roleDTO) {
        return oldUserRoleList
                .stream()
                .map((UserRole ur)->{
                    return ur.getUserId();
                })
                .filter((Integer uId)->{
                    return !roleDTO
                            .getUserDTOList()
                            .stream()
                            .map((UserDTO uDTO)->{
                                return uDTO.getId();
                            })
                            .collect(Collectors.toSet())
                            .contains(uId);
                })
                .map((Integer uId)->{
                    return oldUserRoleList
                            .stream()
                            .filter((UserRole ur)->{
                                return Objects.equals(ur.getUserId(), uId);
                            })
                            .map((UserRole ur)->{
                                return ur
                                        .setStatus(Integer.MAX_VALUE)
                                        .setUpdateTime(roleDTO.getUpdateTime());
                            })
                            .findFirst()
                            .get();
                })
                .collect(Collectors.toList());
    }
    private List<UserRole> accessEnableUserRoleList(RoleDTO roleDTO) {
        return roleDTO
                .getUserDTOList()
                .stream()
                .map((UserDTO uDTO)->{
                    return new UserRole()
                            .setUserId(uDTO.getId())
                            .setRoleId(roleDTO.getId())
                            .setStatus(0)
                            .setCreateTime(roleDTO.getUpdateTime())
                            .setUpdateTime(roleDTO.getUpdateTime());
                })
                .collect(Collectors.toList());
    }
}