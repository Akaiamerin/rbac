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
        User user = Optional
                .of(userDTO)
                .map((UserDTO uDTO)->{
                    return new User()
                            .setUsername(uDTO.getUsername())
                            .setPassword(uDTO.getPassword());
                })
                .get();
        int count = userMapper.insertUser(user);
        if (count == 0) {
            return null;
        }
        for (int i = 0; i < userDTO.getRoleDTOList().size(); i++) {
            Integer roleId = userDTO.getRoleDTOList().get(i).getId();
            UserRole userRole = new UserRole()
                    .setUserId(user.getId())
                    .setRoleId(roleId);
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
        User user = Optional
                .of(userDTO)
                .map((UserDTO uDTO)->{
                    return new User()
                            .setId(uDTO.getId())
                            .setUsername(uDTO.getUsername())
                            .setPassword(uDTO.getPassword());
                })
                .get();
        int count = userMapper.updateUserById(user);
        if (count == 0) {
            return null;
        }
        userRoleMapper.deleteUserRoleByUserId(user.getId());
        for (int i = 0; i < userDTO.getRoleDTOList().size(); i++) {
            Integer roleId = userDTO.getRoleDTOList().get(i).getId();
            UserRole userRole = new UserRole()
                    .setUserId(user.getId())
                    .setRoleId(roleId);
            userRoleMapper.insertUserRole(userRole);
        }
        UserDTO data = selectUserById(user.getId());
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
                                                    .setName(r.getName());
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
                            .setRoleDTOList(rDTOList);
                })
                .orElse(null);
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
                                                    .setName(r.getName());
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
                            .setRoleDTOList(rDTOList);
                })
                .collect(Collectors.toList());
        return data;
    }
}