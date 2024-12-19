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
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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
        Role role = Optional
                .of(roleDTO)
                .map((RoleDTO rDTO)->{
                    return new Role()
                            .setName(rDTO.getName())
                            .setStatus(rDTO.getStatus())
                            .setStatus(rDTO.getStatus())
                            .setCreateTime(rDTO.getCreateTime())
                            .setUpdateTime(rDTO.getUpdateTime());
                })
                .get();
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
                            .setStatus(0)
                            .setCreateTime(role.getCreateTime())
                            .setUpdateTime(role.getUpdateTime());
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
                            .setStatus(0)
                            .setCreateTime(role.getCreateTime())
                            .setUpdateTime(role.getUpdateTime());
                })
                .collect(Collectors.toList());
        userRoleMapper.updateUserRoleList(userRoleList);
        RoleDTO data = selectRoleById(role.getId());
        return data;
    }
    @Override
    public List<RoleDTO> insertRoleList(List<RoleDTO> roleDTOList) {
        List<Role> roleList = roleDTOList
                .stream()
                .map((RoleDTO rDTO)->{
                    return new Role()
                            .setName(rDTO.getName())
                            .setStatus(rDTO.getStatus())
                            .setStatus(rDTO.getStatus())
                            .setCreateTime(rDTO.getCreateTime())
                            .setUpdateTime(rDTO.getUpdateTime());
                })
                .collect(Collectors.toList());
        int count = roleMapper.insertRoleList(roleList);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < roleList.size(); i++) {
            Role role = roleList.get(i);
            List<RolePermission> rolePermissionList = roleDTOList
                    .get(i)
                    .getPermissionDTOList()
                    .stream()
                    .map((PermissionDTO pDTO)->{
                        return new RolePermission()
                                .setRoleId(role.getId())
                                .setPermissionId(pDTO.getId())
                                .setStatus(0)
                                .setCreateTime(role.getCreateTime())
                                .setUpdateTime(role.getUpdateTime());
                    })
                    .collect(Collectors.toList());
            rolePermissionMapper.updateRolePermissionList(rolePermissionList);
            List<UserRole> userRoleList = roleDTOList
                    .get(i)
                    .getUserDTOList()
                    .stream()
                    .map((UserDTO uDTO)->{
                        return new UserRole()
                                .setUserId(uDTO.getId())
                                .setRoleId(role.getId())
                                .setStatus(0)
                                .setCreateTime(role.getCreateTime())
                                .setUpdateTime(role.getUpdateTime());
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
        Role role = Optional
                .of(roleDTO)
                .map((RoleDTO rDTO)->{
                    return new Role()
                            .setId(rDTO.getId())
                            .setName(rDTO.getName())
                            .setStatus(rDTO.getStatus())
                            .setStatus(rDTO.getStatus())
                            .setCreateTime(rDTO.getCreateTime())
                            .setUpdateTime(rDTO.getUpdateTime());
                })
                .get();
        int count = roleMapper.updateRoleById(role);
        if (count == 0) {
            return null;
        }
        List<RolePermission> oldRolePermissionList = rolePermissionMapper.selectRolePermissionByRoleId(role.getId());
        Set<Integer> oldPermissionIdSet = oldRolePermissionList
                .stream()
                .map((RolePermission rp)->{
                    return rp.getPermissionId();
                })

                .collect(Collectors.toSet());
        Set<Integer> newPermissionIdSet = roleDTO
                .getPermissionDTOList()
                .stream()
                .map((PermissionDTO pDTO)->{
                    return pDTO.getId();
                })
                .collect(Collectors.toSet());
        List<RolePermission> updateRolePermissionList = oldPermissionIdSet
                .stream()
                .filter((Integer pId)->{
                    return !newPermissionIdSet.contains(pId);
                })
                .map((Integer pId)->{
                    return oldRolePermissionList
                            .stream()
                            .filter((RolePermission rp)->{
                                return Objects.equals(rp.getPermissionId(), pId);
                            })
                            .findFirst()
                            .map((RolePermission rp)->{
                                return rp
                                        .setStatus(Integer.MAX_VALUE)
                                        .setUpdateTime(role.getUpdateTime());
                            })
                            .get();
                })
                .collect(Collectors.toList());
        List<RolePermission> insertRolePermissionList = newPermissionIdSet
                .stream()
                .filter((Integer pId)->{
                    return !oldPermissionIdSet.contains(pId);
                })
                .map((Integer pId)->{
                    return new RolePermission()
                            .setRoleId(role.getId())
                            .setPermissionId(pId)
                            .setStatus(0)
                            .setCreateTime(role.getUpdateTime())
                            .setUpdateTime(role.getUpdateTime());
                })
                .collect(Collectors.toList());
        List<RolePermission> rolePermissionList = Stream.concat(updateRolePermissionList.stream(), insertRolePermissionList.stream()).collect(Collectors.toList());
        if (rolePermissionList.isEmpty() == false) {
            rolePermissionMapper.updateRolePermissionList(rolePermissionList);
        }
        List<UserRole> oldUserRoleList = userRoleMapper.selectUserRoleByRoleId(role.getId());
        Set<Integer> oldUserIdList = oldUserRoleList
                .stream()
                .map((UserRole ur)->{
                    return ur.getUserId();
                })
                .collect(Collectors.toSet());
        Set<Integer> newUserIdList = roleDTO
                .getUserDTOList()
                .stream()
                .map((UserDTO uDTO)->{
                    return uDTO.getId();
                })
                .collect(Collectors.toSet());
        List<UserRole> updateUserRoleList = oldUserIdList
                .stream()
                .filter((Integer uId)->{
                    return !newUserIdList.contains(uId);
                })
                .map((Integer uId)->{
                    return oldUserRoleList
                            .stream()
                            .filter((UserRole ur)->{
                                return Objects.equals(ur.getUserId(), uId);
                            })
                            .findFirst()
                            .map((UserRole ur)->{
                                return ur
                                        .setStatus(Integer.MAX_VALUE)
                                        .setUpdateTime(role.getUpdateTime());
                            })
                            .get();
                })
                .collect(Collectors.toList());
        List<UserRole> insertUserRoleList = newUserIdList
                .stream()
                .filter((Integer uId)->{
                    return !oldUserIdList.contains(uId);
                })
                .map((Integer uId)->{
                    return new UserRole()
                            .setUserId(uId)
                            .setRoleId(role.getId())
                            .setStatus(0)
                            .setCreateTime(role.getUpdateTime())
                            .setUpdateTime(role.getUpdateTime());
                })
                .collect(Collectors.toList());
        List<UserRole> userRoleList = Stream.concat(updateUserRoleList.stream(), insertUserRoleList.stream()).collect(Collectors.toList());
        if (userRoleList.isEmpty() == false) {
            userRoleMapper.updateUserRoleList(userRoleList);
        }
        RoleDTO data = selectRoleById(role.getId());
        return data;
    }
    @Override
    public List<RoleDTO> updateRoleByIdList(List<Integer> idList, RoleDTO roleDTO) {
        Role role = Optional
                .of(roleDTO)
                .map((RoleDTO rDTO)->{
                    return new Role()
                            .setId(rDTO.getId())
                            .setName(rDTO.getName())
                            .setStatus(rDTO.getStatus())
                            .setStatus(rDTO.getStatus())
                            .setCreateTime(rDTO.getCreateTime())
                            .setUpdateTime(rDTO.getUpdateTime());
                })
                .get();
        int count = roleMapper.updateRoleByIdList(idList, role);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < idList.size(); i++) {
            Integer roleId = idList.get(i);
            List<RolePermission> oldRolePermissionList = rolePermissionMapper.selectRolePermissionByRoleId(roleId);
            Set<Integer> oldPermissionIdSet = oldRolePermissionList
                    .stream()
                    .map((RolePermission rp)->{
                        return rp.getPermissionId();
                    })
                    .collect(Collectors.toSet());
            Set<Integer> newPermissionIdSet = roleDTO
                    .getPermissionDTOList()
                    .stream()
                    .map((PermissionDTO pDTO)->{
                        return pDTO.getId();
                    })
                    .collect(Collectors.toSet());
            List<RolePermission> updateRolePermissionList = oldPermissionIdSet
                    .stream()
                    .filter((Integer pId)->{
                        return !newPermissionIdSet.contains(pId);
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
                                            .setUpdateTime(role.getUpdateTime());
                                })
                                .findFirst()
                                .get();
                    })
                    .collect(Collectors.toList());
            List<RolePermission> insertRolePermissionList = newPermissionIdSet
                    .stream()
                    .filter((Integer pId)->{
                        return !oldPermissionIdSet.contains(pId);
                    })
                    .map((Integer pId)->{
                        return new RolePermission()
                                .setRoleId(roleId)
                                .setPermissionId(pId)
                                .setStatus(0)
                                .setCreateTime(role.getUpdateTime())
                                .setUpdateTime(role.getUpdateTime());
                    })
                    .collect(Collectors.toList());
            List<RolePermission> rolePermissionList = Stream.concat(updateRolePermissionList.stream(), insertRolePermissionList.stream()).collect(Collectors.toList());
            if (rolePermissionList.isEmpty() == false) {
                rolePermissionMapper.updateRolePermissionList(rolePermissionList);
            }
            List<UserRole> oldUserRoleList = userRoleMapper.selectUserRoleByRoleId(roleId);
            Set<Integer> oldUserIdList = oldUserRoleList
                    .stream()
                    .map((UserRole ur)->{
                        return ur.getUserId();
                    })
                    .collect(Collectors.toSet());
            Set<Integer> newUserIdList = roleDTO
                    .getUserDTOList()
                    .stream()
                    .map((UserDTO uDTO)->{
                        return uDTO.getId();
                    })
                    .collect(Collectors.toSet());
            List<UserRole> updateUserRoleList = oldUserIdList
                    .stream()
                    .filter((Integer uId)->{
                        return !newUserIdList.contains(uId);
                    })
                    .map((Integer uId)->{
                        return oldUserRoleList
                                .stream()
                                .filter((UserRole ur)->{
                                    return Objects.equals(ur.getUserId(), uId);
                                })
                                .findFirst()
                                .map((UserRole ur)->{
                                    return ur
                                            .setStatus(Integer.MAX_VALUE)
                                            .setUpdateTime(role.getUpdateTime());
                                })
                                .get();
                    })
                    .collect(Collectors.toList());
            List<UserRole> insertUserRoleList = newUserIdList
                    .stream()
                    .filter((Integer uId)->{
                        return !oldUserIdList.contains(uId);
                    })
                    .map((Integer uId)->{
                        return new UserRole()
                                .setUserId(uId)
                                .setRoleId(roleId)
                                .setStatus(0)
                                .setCreateTime(role.getUpdateTime())
                                .setUpdateTime(role.getUpdateTime());
                    })
                    .collect(Collectors.toList());
            List<UserRole> userRoleList = Stream.concat(updateUserRoleList.stream(), insertUserRoleList.stream()).collect(Collectors.toList());
            if (userRoleList.isEmpty() == false) {
                userRoleMapper.updateUserRoleList(userRoleList);
            }
        }
        List<RoleDTO> data = selectRoleByIdList(idList);
        return data;
    }
    @Override
    public List<RoleDTO> updateRoleList(List<RoleDTO> roleDTOList) {
        List<Role> roleList = roleDTOList
                .stream()
                .map((RoleDTO rDTO)->{
                    return new Role()
                            .setId(rDTO.getId())
                            .setName(rDTO.getName())
                            .setStatus(rDTO.getStatus())
                            .setStatus(rDTO.getStatus())
                            .setCreateTime(rDTO.getCreateTime())
                            .setUpdateTime(rDTO.getUpdateTime());
                })
                .collect(Collectors.toList());
        int count = roleMapper.updateRoleList(roleList);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < roleList.size(); i++) {
            Role role = roleList.get(i);
            List<RolePermission> oldRolePermissionList = rolePermissionMapper.selectRolePermissionByRoleId(role.getId());
            Set<Integer> oldPermissionIdSet = oldRolePermissionList
                    .stream()
                    .map((RolePermission rp)->{
                        return rp.getPermissionId();
                    })
                    .collect(Collectors.toSet());
            Set<Integer> newPermissionIdSet = roleDTOList
                    .get(i)
                    .getPermissionDTOList()
                    .stream()
                    .map((PermissionDTO pDTO)->{
                        return pDTO.getId();
                    })
                    .collect(Collectors.toSet());
            List<RolePermission> updateRolePermissionList = oldPermissionIdSet
                    .stream()
                    .filter((Integer pId)->{
                        return !newPermissionIdSet.contains(pId);
                    })
                    .map((Integer pId)->{
                        return oldRolePermissionList
                                .stream()
                                .filter((RolePermission rp)->{
                                    return Objects.equals(rp.getPermissionId(), pId);
                                })
                                .findFirst()
                                .map((RolePermission rp)->{
                                    return rp
                                            .setStatus(Integer.MAX_VALUE)
                                            .setUpdateTime(role.getUpdateTime());
                                })
                                .get();
                    })
                    .collect(Collectors.toList());
            List<RolePermission> insertRolePermissionList = newPermissionIdSet
                    .stream()
                    .filter((Integer pId)->{
                        return !oldPermissionIdSet.contains(pId);
                    })
                    .map((Integer pId)->{
                        return new RolePermission()
                                .setRoleId(role.getId())
                                .setPermissionId(pId)
                                .setStatus(0)
                                .setCreateTime(role.getUpdateTime())
                                .setUpdateTime(role.getUpdateTime());
                    })
                    .collect(Collectors.toList());
            List<RolePermission> rolePermissionList = Stream.concat(updateRolePermissionList.stream(), insertRolePermissionList.stream()).collect(Collectors.toList());
            if (rolePermissionList.isEmpty() == false) {
                rolePermissionMapper.updateRolePermissionList(rolePermissionList);
            }
            List<UserRole> oldUserRoleList = userRoleMapper.selectUserRoleByRoleId(role.getId());
            Set<Integer> oldUserIdList = oldUserRoleList
                    .stream()
                    .map((UserRole ur)->{
                        return ur.getUserId();
                    })
                    .collect(Collectors.toSet());
            Set<Integer> newUserIdList = roleDTOList
                    .get(i)
                    .getUserDTOList()
                    .stream()
                    .map((UserDTO uDTO)->{
                        return uDTO.getId();
                    })
                    .collect(Collectors.toSet());
            List<UserRole> updateUserRoleList = oldUserIdList
                    .stream()
                    .filter((Integer uId)->{
                        return !newUserIdList.contains(uId);
                    })
                    .map((Integer uId)->{
                        return oldUserRoleList
                                .stream()
                                .filter((UserRole ur)->{
                                    return Objects.equals(ur.getUserId(), uId);
                                })
                                .findFirst()
                                .map((UserRole ur)->{
                                    return ur
                                            .setStatus(Integer.MAX_VALUE)
                                            .setUpdateTime(role.getUpdateTime());
                                })
                                .get();
                    })
                    .collect(Collectors.toList());
            List<UserRole> insertUserRoleList = newUserIdList
                    .stream()
                    .filter((Integer uId)->{
                        return !oldUserIdList.contains(uId);
                    })
                    .map((Integer uId)->{
                        return new UserRole()
                                .setUserId(uId)
                                .setRoleId(role.getId())
                                .setStatus(0)
                                .setCreateTime(role.getUpdateTime())
                                .setUpdateTime(role.getUpdateTime());
                    })
                    .collect(Collectors.toList());
            List<UserRole> userRoleList = Stream.concat(updateUserRoleList.stream(), insertUserRoleList.stream()).collect(Collectors.toList());
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
        RoleDTO data = Optional
                .ofNullable(role)
                .map((Role r)->{
                    List<PermissionDTO> pDTOList = rolePermissionMapper
                            .selectRolePermissionByRoleId(r.getId())
                            .stream()
                            .map((RolePermission rp)->{
                                return Optional
                                        .ofNullable(permissionMapper.selectPermissionById(rp.getPermissionId()))
                                        .map((Permission p)->{
                                            return new PermissionDTO()
                                                    .setId(p.getId())
                                                    .setName(p.getName())
                                                    .setStatus(p.getStatus())
                                                    .setCreateTime(p.getCreateTime())
                                                    .setUpdateTime(p.getUpdateTime());
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
                                                    .setUsername(u.getUsername())
                                                    .setStatus(u.getStatus())
                                                    .setCreateTime(u.getCreateTime())
                                                    .setUpdateTime(u.getUpdateTime());
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
                            .setStatus(r.getStatus())
                            .setCreateTime(r.getCreateTime())
                            .setUpdateTime(r.getUpdateTime())
                            .setPermissionDTOList(pDTOList)
                            .setUserDTOList(uDTOList);
                })
                .orElse(null);
        return data;
    }
    @Override
    public List<RoleDTO> selectRoleByIdList(List<Integer> idList) {
        List<Role> roleList = roleMapper.selectRoleByIdList(idList);
        List<RoleDTO> data = roleList
                .stream()
                .map((Role r)->{
                    List<PermissionDTO> pDTOList = rolePermissionMapper
                            .selectRolePermissionByRoleId(r.getId())
                            .stream()
                            .map((RolePermission rp)->{
                                return Optional
                                        .ofNullable(permissionMapper.selectPermissionById(rp.getPermissionId()))
                                        .map((Permission p)->{
                                            return new PermissionDTO()
                                                    .setId(p.getId())
                                                    .setName(p.getName())
                                                    .setStatus(p.getStatus())
                                                    .setCreateTime(p.getCreateTime())
                                                    .setUpdateTime(p.getUpdateTime());
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
                                                    .setUsername(u.getUsername())
                                                    .setStatus(u.getStatus())
                                                    .setCreateTime(u.getCreateTime())
                                                    .setUpdateTime(u.getUpdateTime());
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
                            .setStatus(r.getStatus())
                            .setCreateTime(r.getCreateTime())
                            .setUpdateTime(r.getUpdateTime())
                            .setPermissionDTOList(pDTOList)
                            .setUserDTOList(uDTOList);
                })
                .collect(Collectors.toList());
        return data;
    }
    @Override
    public List<RoleDTO> selectRoleByPagination(Pagination pagination) {
        List<Role> roleList = roleMapper.selectRoleByPagination(pagination);
        List<RoleDTO> data = roleList
                .stream()
                .map((Role r)->{
                    List<PermissionDTO> pDTOList = rolePermissionMapper
                            .selectRolePermissionByRoleId(r.getId())
                            .stream()
                            .map((RolePermission rp)->{
                                return Optional
                                        .ofNullable(permissionMapper.selectPermissionById(rp.getPermissionId()))
                                        .map((Permission p)->{
                                            return new PermissionDTO()
                                                    .setId(p.getId())
                                                    .setName(p.getName())
                                                    .setStatus(p.getStatus())
                                                    .setCreateTime(p.getCreateTime())
                                                    .setUpdateTime(p.getUpdateTime());
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
                                                    .setUsername(u.getUsername())
                                                    .setStatus(u.getStatus())
                                                    .setCreateTime(u.getCreateTime())
                                                    .setUpdateTime(u.getUpdateTime());
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
                            .setStatus(r.getStatus())
                            .setCreateTime(r.getCreateTime())
                            .setUpdateTime(r.getUpdateTime())
                            .setPermissionDTOList(pDTOList)
                            .setUserDTOList(uDTOList);
                })
                .collect(Collectors.toList());
        return data;
    }
}