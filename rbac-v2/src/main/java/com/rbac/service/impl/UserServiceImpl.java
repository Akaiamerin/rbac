package com.rbac.service.impl;
import com.rbac.entity.Role;
import com.rbac.entity.User;
import com.rbac.entity.UserRole;
import com.rbac.mapper.RoleMapper;
import com.rbac.mapper.UserMapper;
import com.rbac.mapper.UserRoleMapper;
import com.rbac.pojo.dto.RoleDTO;
import com.rbac.pojo.dto.UserDTO;
import com.rbac.service.UserService;
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
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Override
    public UserDTO insertUser(UserDTO userDTO) {
        User user = Optional
                .of(userDTO)
                .map((UserDTO uDTO)->{
                    return new User()
                            .setUsername(uDTO.getUsername())
                            .setPassword(uDTO.getPassword())
                            .setStatus(uDTO.getStatus())
                            .setCreateTime(uDTO.getCreateTime())
                            .setUpdateTime(uDTO.getUpdateTime());
                })
                .get();
        int count = userMapper.insertUser(user);
        if (count == 0) {
            return null;
        }
        List<UserRole> userRoleList = userDTO
                .getRoleDTOList()
                .stream()
                .map((RoleDTO rDTO)->{
                    return new UserRole()
                            .setUserId(user.getId())
                            .setRoleId(rDTO.getId())
                            .setStatus(0)
                            .setCreateTime(user.getCreateTime())
                            .setUpdateTime(user.getUpdateTime());
                })
                .collect(Collectors.toList());
        userRoleMapper.updateUserRoleList(userRoleList);
        UserDTO data = selectUserById(user.getId());
        return data;
    }
    @Override
    public List<UserDTO> insertUserList(List<UserDTO> userDTOList) {
        List<User> userList = userDTOList
                .stream()
                .map((UserDTO uDTO)->{
                    return new User()
                            .setUsername(uDTO.getUsername())
                            .setPassword(uDTO.getPassword())
                            .setStatus(uDTO.getStatus())
                            .setCreateTime(uDTO.getCreateTime())
                            .setUpdateTime(uDTO.getUpdateTime());
                })
                .collect(Collectors.toList());
        int count = userMapper.insertUserList(userList);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            List<UserRole> userRoleList = userDTOList
                    .get(i)
                    .getRoleDTOList()
                    .stream()
                    .map((RoleDTO rDTO)->{
                        return new UserRole()
                                .setUserId(user.getId())
                                .setRoleId(rDTO.getId())
                                .setStatus(0)
                                .setCreateTime(user.getCreateTime())
                                .setUpdateTime(user.getUpdateTime());
                    })
                    .collect(Collectors.toList());
            userRoleMapper.updateUserRoleList(userRoleList);
        }
        List<Integer> idList = userList
                .stream()
                .map((User u)->{
                    return u.getId();
                })
                .collect(Collectors.toList());
        List<UserDTO> data = selectUserByIdList(idList);
        return data;
    }
    @Override
    public UserDTO updateUserById(UserDTO userDTO) {
        User user = Optional
                .of(userDTO)
                .map((UserDTO uDTO)->{
                    return new User()
                            .setId(uDTO.getId())
                            .setUsername(uDTO.getUsername())
                            .setPassword(uDTO.getPassword())
                            .setStatus(uDTO.getStatus())
                            .setCreateTime(uDTO.getCreateTime())
                            .setUpdateTime(uDTO.getUpdateTime());
                })
                .get();
        int count = userMapper.updateUserById(user);
        if (count == 0) {
            return null;
        }
        List<UserRole> oldUserRoleList = userRoleMapper.selectUserRoleByUserId(user.getId());
        Set<Integer> oldRoleIdSet = oldUserRoleList
                .stream()
                .map((UserRole ur)->{
                    return ur.getRoleId();
                })
                .collect(Collectors.toSet());
        Set<Integer> newRoleIdSet = userDTO
                .getRoleDTOList()
                .stream()
                .map((RoleDTO rDTO)->{
                    return rDTO.getId();
                })
                .collect(Collectors.toSet());
        List<UserRole> updateUserRoleList = oldRoleIdSet
                .stream()
                .filter((Integer rId)->{
                    return !newRoleIdSet.contains(rId);
                })
                .map((Integer rId)->{
                    return oldUserRoleList
                            .stream()
                            .filter((UserRole ur)->{
                                return Objects.equals(ur.getRoleId(), rId);
                            })
                            .map((UserRole ur)->{
                                return ur
                                        .setStatus(Integer.MAX_VALUE)
                                        .setUpdateTime(user.getUpdateTime());
                            })
                            .findFirst()
                            .get();
                })
                .collect(Collectors.toList());
        List<UserRole> insertUserRoleList = newRoleIdSet
                .stream()
                .filter((Integer rId)->{
                    return !oldRoleIdSet.contains(rId);
                })
                .map((Integer rId)->{
                    return new UserRole()
                            .setUserId(user.getId())
                            .setRoleId(rId)
                            .setStatus(0)
                            .setCreateTime(user.getUpdateTime())
                            .setUpdateTime(user.getUpdateTime());
                })
                .collect(Collectors.toList());
        List<UserRole> userRoleList = Stream.concat(updateUserRoleList.stream(), insertUserRoleList.stream()).collect(Collectors.toList());
        if (userRoleList.isEmpty() == false) {
            userRoleMapper.updateUserRoleList(userRoleList);
        }
        UserDTO data = selectUserById(user.getId());
        return data;
    }
    @Override
    public List<UserDTO> updateUserByIdList(List<Integer> idList, UserDTO userDTO) {
        User user = Optional
                .of(userDTO)
                .map((UserDTO uDTO)->{
                    return new User()
                            .setId(uDTO.getId())
                            .setUsername(uDTO.getUsername())
                            .setPassword(uDTO.getPassword())
                            .setStatus(uDTO.getStatus())
                            .setCreateTime(uDTO.getCreateTime())
                            .setUpdateTime(uDTO.getUpdateTime());
                })
                .get();
        int count = userMapper.updateUserByIdList(idList, user);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < idList.size(); i++) {
            Integer userId = idList.get(i);
            List<UserRole> oldUserRoleList = userRoleMapper.selectUserRoleByUserId(userId);
            Set<Integer> oldRoleIdSet = oldUserRoleList
                    .stream()
                    .map((UserRole ur)->{
                        return ur.getRoleId();
                    })
                    .collect(Collectors.toSet());
            Set<Integer> newRoleIdSet = userDTO
                    .getRoleDTOList()
                    .stream()
                    .map((RoleDTO rDTO)->{
                        return rDTO.getId();
                    })
                    .collect(Collectors.toSet());
            List<UserRole> updateUserRoleList = oldRoleIdSet
                    .stream()
                    .filter((Integer rId)->{
                        return !newRoleIdSet.contains(rId);
                    })
                    .map((Integer rId)->{
                        return oldUserRoleList
                                .stream()
                                .filter((UserRole ur)->{
                                    return Objects.equals(ur.getRoleId(), rId);
                                })
                                .findFirst()
                                .map((UserRole ur)->{
                                    return ur
                                            .setStatus(Integer.MAX_VALUE)
                                            .setUpdateTime(user.getUpdateTime());
                                })
                                .get();
                    })
                    .collect(Collectors.toList());
            List<UserRole> insertUserRoleList = newRoleIdSet
                    .stream()
                    .filter((Integer rId)->{
                        return !oldRoleIdSet.contains(rId);
                    })
                    .map((Integer rId)->{
                        return new UserRole()
                                .setUserId(userId)
                                .setRoleId(rId)
                                .setStatus(0)
                                .setCreateTime(user.getUpdateTime())
                                .setUpdateTime(user.getUpdateTime());
                    })
                    .collect(Collectors.toList());
            List<UserRole> userRoleList = Stream.concat(updateUserRoleList.stream(), insertUserRoleList.stream()).collect(Collectors.toList());
            if (userRoleList.isEmpty() == false) {
                userRoleMapper.updateUserRoleList(userRoleList);
            }
        }
        List<UserDTO> data = selectUserByIdList(idList);
        return data;
    }
    @Override
    public List<UserDTO> updateUserList(List<UserDTO> userDTOList) {
        List<User> userList = userDTOList
                .stream()
                .map((UserDTO uDTO)->{
                    return new User()
                            .setId(uDTO.getId())
                            .setUsername(uDTO.getUsername())
                            .setPassword(uDTO.getPassword())
                            .setStatus(uDTO.getStatus())
                            .setCreateTime(uDTO.getCreateTime())
                            .setUpdateTime(uDTO.getUpdateTime());
                })
                .collect(Collectors.toList());
        int count = userMapper.updateUserList(userList);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            List<UserRole> oldUserRoleList = userRoleMapper.selectUserRoleByUserId(user.getId());
            Set<Integer> oldRoleIdSet = oldUserRoleList
                    .stream()
                    .map((UserRole ur)->{
                        return ur.getRoleId();
                    })
                    .collect(Collectors.toSet());
            Set<Integer> newRoleIdSet = userDTOList
                    .get(i)
                    .getRoleDTOList()
                    .stream()
                    .map((RoleDTO rDTO)->{
                        return rDTO.getId();
                    })
                    .collect(Collectors.toSet());
            List<UserRole> updateUserRoleList = oldRoleIdSet
                    .stream()
                    .filter((Integer rId)->{
                        return !newRoleIdSet.contains(rId);
                    })
                    .map((Integer rId)->{
                        return oldUserRoleList
                                .stream()
                                .filter((UserRole ur)->{
                                    return Objects.equals(ur.getRoleId(), rId);
                                })
                                .map((UserRole ur)->{
                                    return ur
                                            .setStatus(Integer.MAX_VALUE)
                                            .setUpdateTime(user.getUpdateTime());
                                })
                                .findFirst()
                                .get();
                    })
                    .collect(Collectors.toList());
            List<UserRole> insertUserRoleList = newRoleIdSet
                    .stream()
                    .filter((Integer rId)->{
                        return !oldRoleIdSet.contains(rId);
                    })
                    .map((Integer rId)->{
                        return new UserRole()
                                .setUserId(user.getId())
                                .setRoleId(rId)
                                .setStatus(0)
                                .setCreateTime(user.getUpdateTime())
                                .setUpdateTime(user.getUpdateTime());
                    })
                    .collect(Collectors.toList());
            List<UserRole> userRoleList = Stream.concat(updateUserRoleList.stream(), insertUserRoleList.stream()).collect(Collectors.toList());
            if (userRoleList.isEmpty() == false) {
                userRoleMapper.updateUserRoleList(userRoleList);
            }
        }
        List<Integer> idList = userList
                .stream()
                .map((User u)->{
                    return u.getId();
                })
                .collect(Collectors.toList());
        List<UserDTO> data = selectUserByIdList(idList);
        return data;
    }
    @Override
    public UserDTO selectUserById(Integer id) {
        User user = userMapper.selectUserById(id);
        UserDTO data = Optional
                .ofNullable(user)
                .map((User u)->{
                    List<RoleDTO> rDTOList = userRoleMapper
                            .selectUserRoleByUserId(u.getId())
                            .stream()
                            .map((UserRole ur)->{
                                return Optional
                                        .ofNullable(roleMapper.selectRoleById(ur.getRoleId()))
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
                                                    .setId(ur.getRoleId());
                                        });
                            })
                            .collect(Collectors.toList());
                    return new UserDTO()
                            .setId(u.getId())
                            .setUsername(u.getUsername())
                            .setPassword(u.getPassword())
                            .setStatus(u.getStatus())
                            .setCreateTime(u.getCreateTime())
                            .setUpdateTime(u.getUpdateTime())
                            .setRoleDTOList(rDTOList);
                })
                .orElse(null);
        return data;
    }
    @Override
    public List<UserDTO> selectUserByIdList(List<Integer> idList) {
        List<User> userList = userMapper.selectUserByIdList(idList);
        List<UserDTO> data = userList
                .stream()
                .map((User u)->{
                    List<RoleDTO> rDTOList = userRoleMapper
                            .selectUserRoleByUserId(u.getId())
                            .stream()
                            .map((UserRole ur)->{
                                return Optional
                                        .ofNullable(roleMapper.selectRoleById(ur.getRoleId()))
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
                                                    .setId(ur.getRoleId());
                                        });
                            })
                            .collect(Collectors.toList());
                    return new UserDTO()
                            .setId(u.getId())
                            .setUsername(u.getUsername())
                            .setPassword(u.getPassword())
                            .setStatus(u.getStatus())
                            .setCreateTime(u.getCreateTime())
                            .setUpdateTime(u.getUpdateTime())
                            .setRoleDTOList(rDTOList);
                })
                .collect(Collectors.toList());
        return data;
    }
    @Override
    public List<UserDTO> selectUserByPagination(Pagination pagination) {
        List<User> userList = userMapper.selectUserByPagination(pagination);
        List<UserDTO> data = userList
                .stream()
                .map((User u)->{
                    List<RoleDTO> rDTOList = userRoleMapper
                            .selectUserRoleByUserId(u.getId())
                            .stream()
                            .map((UserRole ur)->{
                                return Optional
                                        .ofNullable(roleMapper.selectRoleById(ur.getRoleId()))
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
                                                    .setId(ur.getRoleId());
                                        });
                            })
                            .collect(Collectors.toList());
                    return new UserDTO()
                            .setId(u.getId())
                            .setUsername(u.getUsername())
                            .setPassword(u.getPassword())
                            .setStatus(u.getStatus())
                            .setCreateTime(u.getCreateTime())
                            .setUpdateTime(u.getUpdateTime())
                            .setRoleDTOList(rDTOList);
                })
                .collect(Collectors.toList());
        return data;
    }
}