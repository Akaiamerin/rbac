package com.rbac.v1.converter;
import com.rbac.v1.entity.Role;
import com.rbac.v1.entity.User;
import com.rbac.v1.exception.BusinessException;
import com.rbac.v1.pojo.dto.RoleDTO;
import com.rbac.v1.pojo.dto.UserDTO;
import com.rbac.v1.utils.StackMetadataWalkerUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
public class UserServiceConverter {
    private UserServiceConverter() {

    }
    public static User convert(UserDTO userDTO) {
        return Optional
                .ofNullable(userDTO)
                .map((UserDTO uDTO)->{
                    return new User()
                            .setId(uDTO.getId())
                            .setUsername(uDTO.getUsername())
                            .setPassword(uDTO.getPassword());
                })
                .orElseThrow(()->{
                    return new BusinessException(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName());
                });
    }
    public static UserDTO convert(User user, List<Role> roleList) {
        List<RoleDTO> roleDTOList = Optional
                .ofNullable(roleList)
                .orElse(new ArrayList<>())
                .stream()
                .map((Role r)->{
                    return new RoleDTO()
                            .setId(r.getId())
                            .setName(r.getName());
                })
                .collect(Collectors.toList());
        return new UserDTO()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setRoleDTOList(roleDTOList);
    }
}