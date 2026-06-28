package com.rbac.v2.converter;
import com.rbac.v2.entity.Role;
import com.rbac.v2.entity.User;
import com.rbac.v2.exception.BusinessException;
import com.rbac.v2.pojo.dto.RoleDTO;
import com.rbac.v2.pojo.dto.UserDTO;
import com.rbac.v2.utils.StackMetadataWalkerUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
public class UserServiceConverter {
    private UserServiceConverter() {

    }
    public static User convertRequest(UserDTO userDTO) {
        return Optional
                .ofNullable(userDTO)
                .map((UserDTO uDTO)->{
                    return new User()
                            .setId(uDTO.getId())
                            .setUsername(uDTO.getUsername())
                            .setPassword(uDTO.getPassword())
                            .setStatus(uDTO.getStatus())
                            .setCreateTime(uDTO.getCreateTime())
                            .setUpdateTime(uDTO.getUpdateTime());
                })
                .orElseThrow(()->{
                    return new BusinessException(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName());
                });
    }
    public static List<User> convertRequest(List<UserDTO> userDTOList) {
        return userDTOList
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
    }
    public static UserDTO convertResponse(User user, List<Role> roleList) {
        List<RoleDTO> roleDTOList = Optional
                .ofNullable(roleList)
                .orElse(new ArrayList<>())
                .stream()
                .map((Role r)->{
                    return new RoleDTO()
                            .setId(r.getId())
                            .setName(r.getName())
                            .setStatus(r.getStatus())
                            .setCreateTime(r.getCreateTime())
                            .setUpdateTime(r.getUpdateTime());
                })
                .collect(Collectors.toList());
        return new UserDTO()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setStatus(user.getStatus())
                .setCreateTime(user.getCreateTime())
                .setUpdateTime(user.getUpdateTime())
                .setRoleDTOList(roleDTOList);
    }
}