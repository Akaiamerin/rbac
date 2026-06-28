package com.rbac.v2.converter;
import com.rbac.v2.entity.Permission;
import com.rbac.v2.entity.Role;
import com.rbac.v2.exception.BusinessException;
import com.rbac.v2.pojo.dto.PermissionDTO;
import com.rbac.v2.pojo.dto.RoleDTO;
import com.rbac.v2.utils.StackMetadataWalkerUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
public class PermissionServiceConverter {
    private PermissionServiceConverter() {

    }
    public static Permission convertRequest(PermissionDTO permissionDTO) {
        return Optional
                .ofNullable(permissionDTO)
                .map((PermissionDTO pDTO)->{
                    return new Permission()
                            .setId(pDTO.getId())
                            .setName(pDTO.getName())
                            .setStatus(pDTO.getStatus())
                            .setCreateTime(pDTO.getCreateTime())
                            .setUpdateTime(pDTO.getUpdateTime());
                })
                .orElseThrow(()->{
                    return new BusinessException(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName());
                });
    }
    public static List<Permission> convertRequest(List<PermissionDTO> permissionDTOList) {
        return permissionDTOList
                .stream()
                .map((PermissionDTO pDTO)->{
                    return new Permission()
                            .setId(pDTO.getId())
                            .setName(pDTO.getName())
                            .setStatus(pDTO.getStatus())
                            .setCreateTime(pDTO.getCreateTime())
                            .setUpdateTime(pDTO.getUpdateTime());
                })
                .collect(Collectors.toList());
    }
    public static PermissionDTO convertResponse(Permission permission, List<Role> roleList) {
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
        return new PermissionDTO()
                .setId(permission.getId())
                .setName(permission.getName())
                .setStatus(permission.getStatus())
                .setCreateTime(permission.getCreateTime())
                .setUpdateTime(permission.getUpdateTime())
                .setRoleDTOList(roleDTOList);
    }
}