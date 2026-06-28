package com.rbac.v1.converter;
import com.rbac.v1.exception.BusinessException;
import com.rbac.v1.pojo.dto.PermissionDTO;
import com.rbac.v1.pojo.dto.RoleDTO;
import com.rbac.v1.pojo.vo.PermissionVO;
import com.rbac.v1.pojo.vo.RoleVO;
import com.rbac.v1.utils.StackMetadataWalkerUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
public class PermissionControllerConverter {
    private PermissionControllerConverter() {

    }
    public static PermissionDTO convert(PermissionVO permissionVO) {
        return Optional
                .ofNullable(permissionVO)
                .map((PermissionVO pVO)->{
                    List<RoleDTO> rDTOList = pVO
                            .getRoleVOList()
                            .stream()
                            .map((RoleVO rVO)->{
                                return new RoleDTO()
                                        .setId(rVO.getId());
                            })
                            .collect(Collectors.toList());
                    return new PermissionDTO()
                            .setId(pVO.getId())
                            .setName(pVO.getName())
                            .setRoleDTOList(rDTOList);
                })
                .orElseThrow(()->{
                    return new BusinessException(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName());
                });
    }
    public static PermissionVO convert(PermissionDTO permissionDTO) {
        return Optional
                .ofNullable(permissionDTO)
                .map((PermissionDTO pDTO)->{
                    List<RoleVO> rVOList = pDTO
                            .getRoleDTOList()
                            .stream()
                            .map((RoleDTO rDTO)->{
                                return new RoleVO()
                                        .setId(rDTO.getId())
                                        .setName(rDTO.getName());
                            })
                            .collect(Collectors.toList());
                    return new PermissionVO()
                            .setId(pDTO.getId())
                            .setName(pDTO.getName())
                            .setRoleVOList(rVOList);
                })
                .orElse(null);
    }
    public static List<PermissionVO> convertBatch(List<PermissionDTO> permissionDTOList) {
        return Optional
                .ofNullable(permissionDTOList)
                .orElse(new ArrayList<>())
                .stream()
                .map((PermissionDTO pDTO)->{
                    return convert(pDTO);
                })
                .collect(Collectors.toList());
    }
}