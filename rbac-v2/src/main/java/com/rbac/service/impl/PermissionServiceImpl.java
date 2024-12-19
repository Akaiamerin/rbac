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
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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
        Permission permission = Optional
                .of(permissionDTO)
                .map((PermissionDTO pDTO)->{
                    return new Permission()
                            .setName(pDTO.getName())
                            .setStatus(pDTO.getStatus())
                            .setStatus(pDTO.getStatus())
                            .setCreateTime(pDTO.getCreateTime())
                            .setUpdateTime(pDTO.getUpdateTime());
                })
                .get();
        int count = permissionMapper.insertPermission(permission);
        if (count == 0) {
            return null;
        }
        List<RolePermission> rolePermissionList = permissionDTO
                .getRoleDTOList()
                .stream()
                .map((RoleDTO rVO)->{
                    return new RolePermission()
                            .setRoleId(rVO.getId())
                            .setPermissionId(permission.getId())
                            .setStatus(0)
                            .setCreateTime(permission.getCreateTime())
                            .setUpdateTime(permission.getUpdateTime());
                })
                .collect(Collectors.toList());
        rolePermissionMapper.updateRolePermissionList(rolePermissionList);
        PermissionDTO data = selectPermissionById(permission.getId());
        return data;
    }
    @Override
    public List<PermissionDTO> insertPermissionList(List<PermissionDTO> permissionDTOList) {
        List<Permission> permissionList = permissionDTOList
                .stream()
                .map((PermissionDTO pDTO)->{
                    return new Permission()
                            .setName(pDTO.getName())
                            .setStatus(pDTO.getStatus())
                            .setStatus(pDTO.getStatus())
                            .setCreateTime(pDTO.getCreateTime())
                            .setUpdateTime(pDTO.getUpdateTime());
                })
                .collect(Collectors.toList());
        int count = permissionMapper.insertPermissionList(permissionList);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < permissionList.size(); i++) {
            Permission permission = permissionList.get(i);
            List<RolePermission> rolePermissionList = permissionDTOList
                    .get(i)
                    .getRoleDTOList()
                    .stream()
                    .map((RoleDTO rVO)->{
                        return new RolePermission()
                                .setRoleId(rVO.getId())
                                .setPermissionId(permission.getId())
                                .setStatus(0)
                                .setCreateTime(permission.getCreateTime())
                                .setUpdateTime(permission.getUpdateTime());
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
        Permission permission = Optional
                .of(permissionDTO)
                .map((PermissionDTO pDTO)->{
                    return new Permission()
                            .setId(pDTO.getId())
                            .setName(pDTO.getName())
                            .setStatus(pDTO.getStatus())
                            .setStatus(pDTO.getStatus())
                            .setCreateTime(pDTO.getCreateTime())
                            .setUpdateTime(pDTO.getUpdateTime());
                })
                .get();
        int count = permissionMapper.updatePermissionById(permission);
        if (count == 0) {
            return null;
        }
        List<RolePermission> oldRolePermissionList = rolePermissionMapper.selectRolePermissionByPermissionId(permission.getId());
        Set<Integer> oldRoleIdSet = oldRolePermissionList
                .stream()
                .map((RolePermission rp)->{
                    return rp.getRoleId();
                })
                .collect(Collectors.toSet());
        Set<Integer> newRoleIdSet = permissionDTO
                .getRoleDTOList()
                .stream()
                .map((RoleDTO rDTO)->{
                    return rDTO.getId();
                })
                .collect(Collectors.toSet());
        List<RolePermission> updateRolePermissionList = oldRoleIdSet
                .stream()
                .filter((Integer rId)->{
                    return !newRoleIdSet.contains(rId);
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
                                        .setUpdateTime(permission.getUpdateTime());
                            })
                            .findFirst()
                            .get();
                })
                .collect(Collectors.toList());
        List<RolePermission> insertRolePermissionList = newRoleIdSet
                .stream()
                .filter((Integer rId)->{
                    return !oldRoleIdSet.contains(rId);
                })
                .map((Integer rId)->{
                    return new RolePermission()
                            .setRoleId(rId)
                            .setPermissionId(permission.getId())
                            .setStatus(0)
                            .setCreateTime(permission.getUpdateTime())
                            .setUpdateTime(permission.getUpdateTime());
                })
                .collect(Collectors.toList());
        List<RolePermission> rolePermissionList = Stream.concat(updateRolePermissionList.stream(), insertRolePermissionList.stream()).collect(Collectors.toList());
        if (rolePermissionList.isEmpty() == false) {
            rolePermissionMapper.updateRolePermissionList(rolePermissionList);
        }
        PermissionDTO data = selectPermissionById(permission.getId());
        return data;
    }
    @Override
    public List<PermissionDTO> updatePermissionByIdList(List<Integer> idList, PermissionDTO permissionDTO) {
        Permission permission = Optional
                .of(permissionDTO)
                .map((PermissionDTO pDTO)->{
                    return new Permission()
                            .setId(pDTO.getId())
                            .setName(pDTO.getName())
                            .setStatus(pDTO.getStatus())
                            .setStatus(pDTO.getStatus())
                            .setCreateTime(pDTO.getCreateTime())
                            .setUpdateTime(pDTO.getUpdateTime());
                })
                .get();
        int count = permissionMapper.updatePermissionByIdList(idList, permission);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < idList.size(); i++) {
            Integer permissionId = idList.get(i);
            List<RolePermission> oldRolePermissionList = rolePermissionMapper.selectRolePermissionByPermissionId(permissionId);
            Set<Integer> oldRoleIdSet = oldRolePermissionList
                    .stream()
                    .map((RolePermission rp)->{
                        return rp.getRoleId();
                    })
                    .collect(Collectors.toSet());
            Set<Integer> newRoleIdSet = permissionDTO
                    .getRoleDTOList()
                    .stream()
                    .map((RoleDTO rDTO)->{
                        return rDTO.getId();
                    })
                    .collect(Collectors.toSet());
            List<RolePermission> updateRolePermissionList = oldRoleIdSet
                    .stream()
                    .filter((Integer rId)->{
                        return !newRoleIdSet.contains(rId);
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
                                            .setUpdateTime(permission.getUpdateTime());
                                })
                                .findFirst()
                                .get();
                    })
                    .collect(Collectors.toList());
            List<RolePermission> insertRolePermissionList = newRoleIdSet
                    .stream()
                    .filter((Integer rId)->{
                        return !oldRoleIdSet.contains(rId);
                    })
                    .map((Integer rId)->{
                        return new RolePermission()
                                .setRoleId(rId)
                                .setPermissionId(permissionId)
                                .setStatus(0)
                                .setCreateTime(permission.getUpdateTime())
                                .setUpdateTime(permission.getUpdateTime());
                    })
                    .collect(Collectors.toList());
            List<RolePermission> rolePermissionList = Stream.concat(updateRolePermissionList.stream(), insertRolePermissionList.stream()).collect(Collectors.toList());
            if (rolePermissionList.isEmpty() == false) {
                rolePermissionMapper.updateRolePermissionList(rolePermissionList);
            }
        }
        List<PermissionDTO> data = selectPermissionByIdList(idList);
        return data;
    }
    @Override
    public List<PermissionDTO> updatePermissionList(List<PermissionDTO> permissionDTOList) {
        List<Permission> permissionList = permissionDTOList
                .stream()
                .map((PermissionDTO pDTO)->{
                    return new Permission()
                            .setId(pDTO.getId())
                            .setName(pDTO.getName())
                            .setStatus(pDTO.getStatus())
                            .setStatus(pDTO.getStatus())
                            .setCreateTime(pDTO.getCreateTime())
                            .setUpdateTime(pDTO.getUpdateTime());
                })
                .collect(Collectors.toList());
        int count = permissionMapper.updatePermissionList(permissionList);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < permissionList.size(); i++) {
            Permission permission = permissionList.get(i);
            List<RolePermission> oldRolePermissionList = rolePermissionMapper.selectRolePermissionByPermissionId(permission.getId());
            Set<Integer> oldRoleIdSet = oldRolePermissionList
                    .stream()
                    .map((RolePermission rp)->{
                        return rp.getRoleId();
                    })
                    .collect(Collectors.toSet());
            Set<Integer> newRoleIdSet = permissionDTOList
                    .get(i)
                    .getRoleDTOList()
                    .stream()
                    .map((RoleDTO rDTO)->{
                        return rDTO.getId();
                    })
                    .collect(Collectors.toSet());
            List<RolePermission> updateRolePermissionList = oldRoleIdSet
                    .stream()
                    .filter((Integer rId)->{
                        return !newRoleIdSet.contains(rId);
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
                                            .setUpdateTime(permission.getUpdateTime());
                                })
                                .findFirst()
                                .get();
                    })
                    .collect(Collectors.toList());
            List<RolePermission> insertRolePermissionList = newRoleIdSet
                    .stream()
                    .filter((Integer rId)->{
                        return !oldRoleIdSet.contains(rId);
                    })
                    .map((Integer rId)->{
                        return new RolePermission()
                                .setRoleId(rId)
                                .setPermissionId(permission.getId())
                                .setStatus(0)
                                .setCreateTime(permission.getUpdateTime())
                                .setUpdateTime(permission.getUpdateTime());
                    })
                    .collect(Collectors.toList());
            List<RolePermission> rolePermissionList = Stream.concat(updateRolePermissionList.stream(), insertRolePermissionList.stream()).collect(Collectors.toList());
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
                                                    .setName(r.getName())
                                                    .setStatus(r.getStatus())
                                                    .setCreateTime(r.getCreateTime())
                                                    .setUpdateTime(r.getUpdateTime());
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
                            .setStatus(p.getStatus())
                            .setCreateTime(p.getCreateTime())
                            .setUpdateTime(p.getUpdateTime())
                            .setRoleDTOList(rDTOList);
                })
                .orElse(null);
        return data;
    }
    @Override
    public List<PermissionDTO> selectPermissionByIdList(List<Integer> idList) {
        List<Permission> permissionList = permissionMapper.selectPermissionByIdList(idList);
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
                                                    .setName(r.getName())
                                                    .setStatus(r.getStatus())
                                                    .setCreateTime(r.getCreateTime())
                                                    .setUpdateTime(r.getUpdateTime());
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
                            .setStatus(p.getStatus())
                            .setCreateTime(p.getCreateTime())
                            .setUpdateTime(p.getUpdateTime())
                            .setRoleDTOList(rDTOList);
                })
                .collect(Collectors.toList());
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
                                                    .setName(r.getName())
                                                    .setStatus(r.getStatus())
                                                    .setCreateTime(r.getCreateTime())
                                                    .setUpdateTime(r.getUpdateTime());
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
                            .setStatus(p.getStatus())
                            .setCreateTime(p.getCreateTime())
                            .setUpdateTime(p.getUpdateTime())
                            .setRoleDTOList(rDTOList);
                })
                .collect(Collectors.toList());
        return data;
    }
}