package com.rbac.v2.service.impl;
import com.rbac.v2.converter.UserServiceConverter;
import com.rbac.v2.entity.Role;
import com.rbac.v2.entity.User;
import com.rbac.v2.entity.UserRole;
import com.rbac.v2.mapper.RoleMapper;
import com.rbac.v2.mapper.UserMapper;
import com.rbac.v2.mapper.UserRoleMapper;
import com.rbac.v2.pojo.dto.RoleDTO;
import com.rbac.v2.pojo.dto.UserDTO;
import com.rbac.v2.service.UserService;
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
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Override
    public UserDTO insertUser(UserDTO userDTO) {
        User user = UserServiceConverter.convertRequest(userDTO);
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
                            .setStatus(userDTO.getStatus())
                            .setCreateTime(userDTO.getCreateTime())
                            .setUpdateTime(userDTO.getUpdateTime());
                })
                .collect(Collectors.toList());
        userRoleMapper.updateUserRoleList(userRoleList);
        UserDTO data = selectUserById(user.getId());
        return data;
    }
    @Override
    public List<UserDTO> insertUserList(List<UserDTO> userDTOList) {
        List<User> userList = UserServiceConverter.convertRequest(userDTOList);
        int count = userMapper.insertUserList(userList);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            UserDTO userDTO = userDTOList.get(i);
            List<UserRole> userRoleList = userDTO
                    .getRoleDTOList()
                    .stream()
                    .map((RoleDTO rDTO)->{
                        return new UserRole()
                                .setUserId(user.getId())
                                .setRoleId(rDTO.getId())
                                .setStatus(userDTO.getStatus())
                                .setCreateTime(userDTO.getCreateTime())
                                .setUpdateTime(userDTO.getUpdateTime());
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
        User user = UserServiceConverter.convertRequest(userDTO);
        int count = userMapper.updateUserById(user);
        if (count == 0) {
            return null;
        }
        List<UserRole> oldUserRoleList = userRoleMapper.selectUserRoleByUserId(user.getId());
        List<UserRole> disableUserRoleList = accessDisableUserRoleList(oldUserRoleList, userDTO);
        List<UserRole> enableUserRoleList = accessEnableUserRoleList(userDTO);
        List<UserRole> userRoleList = Stream.concat(disableUserRoleList.stream(), enableUserRoleList.stream()).collect(Collectors.toList());
        if (userRoleList.isEmpty() == false) {
            userRoleMapper.updateUserRoleList(userRoleList);
        }
        UserDTO data = selectUserById(user.getId());
        return data;
    }
    @Override
    public List<UserDTO> updateUserByIdList(List<Integer> idList, UserDTO userDTO) {
        User user = UserServiceConverter.convertRequest(userDTO);
        int count = userMapper.updateUserByIdList(idList, user);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < idList.size(); i++) {
            Integer userId = idList.get(i);
            List<UserRole> oldUserRoleList = userRoleMapper.selectUserRoleByUserId(userId);
            List<UserRole> disableUserRoleList = accessDisableUserRoleList(oldUserRoleList, userDTO);
            List<UserRole> enableUserRoleList = accessEnableUserRoleList(userDTO.setId(userId));
            List<UserRole> userRoleList = Stream.concat(disableUserRoleList.stream(), enableUserRoleList.stream()).collect(Collectors.toList());
            if (userRoleList.isEmpty() == false) {
                userRoleMapper.updateUserRoleList(userRoleList);
            }
        }
        List<UserDTO> data = selectUserByIdList(idList);
        return data;
    }
    @Override
    public List<UserDTO> updateUserList(List<UserDTO> userDTOList) {
        List<User> userList = UserServiceConverter.convertRequest(userDTOList);
        int count = userMapper.updateUserList(userList);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            UserDTO userDTO = userDTOList.get(i);
            List<UserRole> oldUserRoleList = userRoleMapper.selectUserRoleByUserId(user.getId());
            List<UserRole> disableUserRoleList = accessDisableUserRoleList(oldUserRoleList, userDTO);
            List<UserRole> enableUserRoleList = accessEnableUserRoleList(userDTO);
            List<UserRole> userRoleList = Stream.concat(disableUserRoleList.stream(), enableUserRoleList.stream()).collect(Collectors.toList());
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
        List<Role> roleList = Optional
                .ofNullable(userRoleMapper.selectUserRoleByUserId(user.getId()))
                .orElse(new ArrayList<>())
                .stream()
                .map((UserRole ur)->{
                    return roleMapper.selectRoleById(ur.getRoleId());
                })
                .collect(Collectors.toList());
        UserDTO data = UserServiceConverter.convertResponse(user, roleList);
        return data;
    }
    @Override
    public List<UserDTO> selectUserByIdList(List<Integer> idList) {
        List<UserDTO> data = Optional
                .ofNullable(userMapper.selectUserByIdList(idList))
                .orElse(new ArrayList<>())
                .stream()
                .map((User u)->{
                    List<Role> rList = Optional
                            .ofNullable(userRoleMapper.selectUserRoleByUserId(u.getId()))
                            .orElse(new ArrayList<>())
                            .stream()
                            .map((UserRole ur) -> {
                                return roleMapper.selectRoleById(ur.getRoleId());
                            })
                            .collect(Collectors.toList());
                    return UserServiceConverter.convertResponse(u, rList);
                })
                .collect(Collectors.toList());
        return data;
    }
    @Override
    public List<UserDTO> selectUserByPagination(Pagination pagination) {
        List<UserDTO> data = Optional
                .ofNullable(userMapper.selectUserByPagination(pagination))
                .orElse(new ArrayList<>())
                .stream()
                .map((User u)->{
                    List<Role> rList = Optional
                            .ofNullable(userRoleMapper.selectUserRoleByUserId(u.getId()))
                            .orElse(new ArrayList<>())
                            .stream()
                            .map((UserRole ur)->{
                                return roleMapper.selectRoleById(ur.getRoleId());
                            })
                            .collect(Collectors.toList());
                    return UserServiceConverter.convertResponse(u, rList);
                })
                .collect(Collectors.toList());
        return data;
    }
    private List<UserRole> accessDisableUserRoleList(List<UserRole> oldUserRoleList, UserDTO userDTO) {
        return oldUserRoleList
                .stream()
                .map((UserRole ur)->{
                    return ur.getRoleId();
                })
                .filter((Integer rId)->{
                    return !userDTO
                            .getRoleDTOList()
                            .stream()
                            .map((RoleDTO rDTO)->{
                                return rDTO.getId();
                            })
                            .collect(Collectors.toSet())
                            .contains(rId);
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
                                        .setUpdateTime(userDTO.getUpdateTime());
                            })
                            .findFirst()
                            .get();
                })
                .collect(Collectors.toList());
    }
    private List<UserRole> accessEnableUserRoleList(UserDTO userDTO) {
        return userDTO
                .getRoleDTOList()
                .stream()
                .map((RoleDTO rDTO)->{
                    return new UserRole()
                            .setUserId(userDTO.getId())
                            .setRoleId(rDTO.getId())
                            .setStatus(0)
                            .setCreateTime(userDTO.getUpdateTime())
                            .setUpdateTime(userDTO.getUpdateTime());
                })
                .collect(Collectors.toList());
    }
}