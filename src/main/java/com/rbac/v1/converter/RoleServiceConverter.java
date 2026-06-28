package com.rbac.v1.converter;
import com.rbac.v1.entity.Permission;
import com.rbac.v1.entity.Role;
import com.rbac.v1.entity.User;
import com.rbac.v1.exception.BusinessException;
import com.rbac.v1.pojo.dto.PermissionDTO;
import com.rbac.v1.pojo.dto.RoleDTO;
import com.rbac.v1.pojo.dto.UserDTO;
import com.rbac.v1.utils.StackMetadataWalkerUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
public class RoleServiceConverter {
    private RoleServiceConverter() {

    }
    public static Role convert(RoleDTO roleDTO) {
        return Optional
                .ofNullable(roleDTO)
                .map((RoleDTO rDTO)->{
                    return new Role()
                            .setId(rDTO.getId())
                            .setName(roleDTO.getName());
                })
                .orElseThrow(()->{
                    return new BusinessException(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName());
                });
    }
    public static RoleDTO convert(Role role, List<Permission> permissionList, List<User> userList) {
        List<PermissionDTO> PermissionDTOList = Optional
                .ofNullable(permissionList)
                .orElse(new ArrayList<>())
                .stream()
                .map((Permission p)->{
                    return new PermissionDTO()
                            .setId(p.getId())
                            .setName(p.getName());
                })
                .collect(Collectors.toList());
        List<UserDTO> UserDTOList = Optional
                .ofNullable(userList)
                .orElse(new ArrayList<>())
                .stream()
                .map((User u)->{
                    return new UserDTO()
                            .setId(u.getId())
                            .setUsername(u.getUsername());
                })
                .collect(Collectors.toList());
        return new RoleDTO()
                .setId(role.getId())
                .setName(role.getName())
                .setPermissionDTOList(PermissionDTOList)
                .setUserDTOList(UserDTOList);
    }
}