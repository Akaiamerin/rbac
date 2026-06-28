package com.rbac.v2.converter;
import com.rbac.v2.entity.Permission;
import com.rbac.v2.entity.Role;
import com.rbac.v2.entity.User;
import com.rbac.v2.exception.BusinessException;
import com.rbac.v2.pojo.dto.PermissionDTO;
import com.rbac.v2.pojo.dto.RoleDTO;
import com.rbac.v2.pojo.dto.UserDTO;
import com.rbac.v2.utils.StackMetadataWalkerUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
public class RoleServiceConverter {
    private RoleServiceConverter() {

    }
    public static Role convertRequest(RoleDTO roleDTO) {
        return Optional
                .ofNullable(roleDTO)
                .map((RoleDTO rDTO)->{
                    return new Role()
                            .setId(rDTO.getId())
                            .setName(rDTO.getName())
                            .setStatus(rDTO.getStatus())
                            .setCreateTime(rDTO.getCreateTime())
                            .setUpdateTime(rDTO.getUpdateTime());
                })
                .orElseThrow(()->{
                    return new BusinessException(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName());
                });
    }
    public static List<Role> convertRequest(List<RoleDTO> roleDTOList) {
        return roleDTOList
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
    }
    public static RoleDTO convertResponse(Role role, List<Permission> permissionList, List<User> userList) {
        List<PermissionDTO> permissionDTOList = Optional
                .ofNullable(permissionList)
                .orElse(new ArrayList<>())
                .stream()
                .map((Permission p)->{
                    return new PermissionDTO()
                            .setId(p.getId())
                            .setName(p.getName())
                            .setStatus(p.getStatus())
                            .setCreateTime(p.getCreateTime())
                            .setUpdateTime(p.getUpdateTime());
                })
                .collect(Collectors.toList());
        List<UserDTO> userDTOList = Optional
                .ofNullable(userList)
                .orElse(new ArrayList<>())
                .stream()
                .map((User u)->{
                    return new UserDTO()
                            .setId(u.getId())
                            .setUsername(u.getUsername())
                            .setStatus(u.getStatus())
                            .setCreateTime(u.getCreateTime())
                            .setUpdateTime(u.getUpdateTime());
                })
                .collect(Collectors.toList());
        return new RoleDTO()
                .setId(role.getId())
                .setName(role.getName())
                .setStatus(role.getStatus())
                .setCreateTime(role.getCreateTime())
                .setUpdateTime(role.getUpdateTime())
                .setPermissionDTOList(permissionDTOList)
                .setUserDTOList(userDTOList);
    }
}