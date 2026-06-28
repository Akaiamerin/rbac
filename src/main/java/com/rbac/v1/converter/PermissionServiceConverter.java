package com.rbac.v1.converter;
import com.rbac.v1.entity.Permission;
import com.rbac.v1.entity.Role;
import com.rbac.v1.exception.BusinessException;
import com.rbac.v1.pojo.dto.PermissionDTO;
import com.rbac.v1.pojo.dto.RoleDTO;
import com.rbac.v1.utils.StackMetadataWalkerUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
public class PermissionServiceConverter {
    private PermissionServiceConverter() {

    }
    public static Permission convert(PermissionDTO permissionDTO) {
        return Optional
                .ofNullable(permissionDTO)
                .map((PermissionDTO pDTO)->{
                    return new Permission()
                            .setId(pDTO.getId())
                            .setName(pDTO.getName());
                })
                .orElseThrow(()->{
                    return new BusinessException(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName());
                });
    }
    public static PermissionDTO convert(Permission permission, List<Role> roleList) {
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
        return new PermissionDTO()
                .setId(permission.getId())
                .setName(permission.getName())
                .setRoleDTOList(roleDTOList);
    }
}