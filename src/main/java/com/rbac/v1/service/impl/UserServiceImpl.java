package com.rbac.v1.service.impl;
import com.rbac.v1.converter.UserServiceConverter;
import com.rbac.v1.entity.Role;
import com.rbac.v1.entity.User;
import com.rbac.v1.entity.UserRole;
import com.rbac.v1.mapper.RoleMapper;
import com.rbac.v1.mapper.UserMapper;
import com.rbac.v1.mapper.UserRoleMapper;
import com.rbac.v1.pojo.dto.RoleDTO;
import com.rbac.v1.pojo.dto.UserDTO;
import com.rbac.v1.service.UserService;
import com.rbac.v1.utils.Pagination;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
        User user = UserServiceConverter.convert(userDTO);
        int count = userMapper.insertUser(user);
        if (count == 0) {
            return null;
        }
        userDTO.setId(user.getId());
        for (int i = 0; i < userDTO.getRoleDTOList().size(); i++) {
            RoleDTO roleDTO = userDTO.getRoleDTOList().get(i);
            UserRole userRole = new UserRole()
                    .setUserId(userDTO.getId())
                    .setRoleId(roleDTO.getId());
            userRoleMapper.insertUserRole(userRole);
        }
        UserDTO data = selectUserById(user.getId());
        return data;
    }
    @Override
    public UserDTO deleteUserById(Integer id) {
        UserDTO data = selectUserById(id);
        int count = userMapper.deleteUserById(id);
        if (count == 0) {
            return null;
        }
        userRoleMapper.deleteUserRoleByUserId(id);
        return data;
    }
    @Override
    public UserDTO updateUserById(UserDTO userDTO) {
        User user = UserServiceConverter.convert(userDTO);
        int count = userMapper.updateUserById(user);
        if (count == 0) {
            return null;
        }
        userRoleMapper.deleteUserRoleByUserId(user.getId());
        for (int i = 0; i < userDTO.getRoleDTOList().size(); i++) {
            RoleDTO roleDTO = userDTO.getRoleDTOList().get(i);
            UserRole userRole = new UserRole()
                    .setUserId(userDTO.getId())
                    .setRoleId(roleDTO.getId());
            userRoleMapper.insertUserRole(userRole);
        }
        UserDTO data = selectUserById(user.getId());
        return data;
    }
    @Override
    public UserDTO selectUserById(Integer id) {
        User user = userMapper.selectUserById(id);
        List<Role> roleList= Optional
                .ofNullable(userRoleMapper.selectUserRoleByUserId(user.getId()))
                .orElse(new ArrayList<>())
                .stream()
                .map((UserRole ur)->{
                    return roleMapper.selectRoleById(ur.getRoleId());
                })
                .collect(Collectors.toList());
        UserDTO data = UserServiceConverter.convert(user, roleList);
        return data;
    }
    @Override
    public List<UserDTO> selectUserByPagination(Pagination pagination) {
        List<UserDTO> data = Optional
                .ofNullable(userMapper.selectUserByPagination(pagination))
                .orElse(new ArrayList<>())
                .stream()
                .map((User u)->{
                    List<Role> rList= Optional
                            .ofNullable(userRoleMapper.selectUserRoleByUserId(u.getId()))
                            .orElse(new ArrayList<>())
                            .stream()
                            .map((UserRole ur)->{
                                return roleMapper.selectRoleById(ur.getRoleId());
                            })
                            .collect(Collectors.toList());
                    return UserServiceConverter.convert(u, rList);
                })
                .collect(Collectors.toList());
        return data;
    }
}