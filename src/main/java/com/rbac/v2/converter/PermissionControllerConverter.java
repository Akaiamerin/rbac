package com.rbac.v2.converter;
import com.rbac.v2.exception.BusinessException;
import com.rbac.v2.pojo.dto.PermissionDTO;
import com.rbac.v2.pojo.dto.RoleDTO;
import com.rbac.v2.pojo.vo.PermissionVO;
import com.rbac.v2.pojo.vo.RoleVO;
import com.rbac.v2.utils.StackMetadataWalkerUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
public class PermissionControllerConverter {
    private PermissionControllerConverter() {

    }
    public static PermissionDTO convertRequest(PermissionVO permissionVO) {
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
                            .setStatus(pVO.getStatus())
                            .setCreateTime(pVO.getCreateTime())
                            .setUpdateTime(pVO.getUpdateTime())
                            .setRoleDTOList(rDTOList);
                })
                .orElseThrow(()->{
                    return new BusinessException(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName());
                });
    }
    public static PermissionVO convertResponse(PermissionDTO permissionDTO) {
        return Optional
                .ofNullable(permissionDTO)
                .map((PermissionDTO pDTO)->{
                    List<RoleVO> rVOList = pDTO
                            .getRoleDTOList()
                            .stream()
                            .map((RoleDTO rDTO)->{
                                return new RoleVO()
                                        .setId(rDTO.getId())
                                        .setName(rDTO.getName())
                                        .setStatus(rDTO.getStatus())
                                        .setCreateTime(rDTO.getCreateTime())
                                        .setUpdateTime(rDTO.getUpdateTime());
                            })
                            .collect(Collectors.toList());
                    return new PermissionVO()
                            .setId(pDTO.getId())
                            .setName(pDTO.getName())
                            .setStatus(pDTO.getStatus())
                            .setCreateTime(pDTO.getCreateTime())
                            .setUpdateTime(pDTO.getUpdateTime())
                            .setRoleVOList(rVOList);
                })
                .orElse(null);
    }
    public static PermissionDTO convertRequest(Integer id, PermissionVO permissionVO) {
        permissionVO.setId(id);
        return convertRequest(permissionVO);
    }
    public static List<PermissionDTO> convertRequest(List<PermissionVO> permissionVOList) {
        return permissionVOList
                .stream()
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
                            .setStatus(pVO.getStatus())
                            .setCreateTime(pVO.getCreateTime())
                            .setUpdateTime(pVO.getUpdateTime())
                            .setRoleDTOList(rDTOList);
                })
                .collect(Collectors.toList());
    }
    public static List<PermissionVO> convertResponse(List<PermissionDTO> permissionDTOList) {
        return Optional
                .ofNullable(permissionDTOList)
                .orElse(new ArrayList<>())
                .stream()
                .map((PermissionDTO pDTO)->{
                    List<RoleVO> rVOList = pDTO
                            .getRoleDTOList()
                            .stream()
                            .map((RoleDTO rDTO)->{
                                return new RoleVO()
                                        .setId(rDTO.getId())
                                        .setName(rDTO.getName())
                                        .setStatus(rDTO.getStatus())
                                        .setCreateTime(rDTO.getCreateTime())
                                        .setUpdateTime(rDTO.getUpdateTime());
                            })
                            .collect(Collectors.toList());
                    return new PermissionVO()
                            .setId(pDTO.getId())
                            .setName(pDTO.getName())
                            .setStatus(pDTO.getStatus())
                            .setCreateTime(pDTO.getCreateTime())
                            .setUpdateTime(pDTO.getUpdateTime())
                            .setRoleVOList(rVOList);
                })
                .collect(Collectors.toList());
    }
}