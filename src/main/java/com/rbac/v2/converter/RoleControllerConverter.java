package com.rbac.v2.converter;
import com.rbac.v2.exception.BusinessException;
import com.rbac.v2.pojo.dto.PermissionDTO;
import com.rbac.v2.pojo.dto.RoleDTO;
import com.rbac.v2.pojo.dto.UserDTO;
import com.rbac.v2.pojo.vo.PermissionVO;
import com.rbac.v2.pojo.vo.RoleVO;
import com.rbac.v2.pojo.vo.UserVO;
import com.rbac.v2.utils.StackMetadataWalkerUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
public class RoleControllerConverter {
    private RoleControllerConverter() {

    }
    public static RoleDTO convertRequest(RoleVO roleVO) {
        return Optional
                .ofNullable(roleVO)
                .map((RoleVO rVO)->{
                    List<PermissionDTO> pDTOList = rVO
                            .getPermissionVOList()
                            .stream()
                            .map((PermissionVO pVO)->{
                                return new PermissionDTO()
                                        .setId(pVO.getId());
                            })
                            .collect(Collectors.toList());
                    List<UserDTO> uDTOList = rVO.getUserVOList()
                            .stream()
                            .map((UserVO uVO)->{
                                return new UserDTO()
                                        .setId(uVO.getId());
                            })
                            .collect(Collectors.toList());
                    return new RoleDTO()
                            .setId(rVO.getId())
                            .setName(rVO.getName())
                            .setStatus(rVO.getStatus())
                            .setCreateTime(rVO.getCreateTime())
                            .setUpdateTime(rVO.getUpdateTime())
                            .setPermissionDTOList(pDTOList)
                            .setUserDTOList(uDTOList);
                })
                .orElseThrow(()->{
                    return new BusinessException(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName());
                });
    }
    public static RoleVO convertResponse(RoleDTO roleDTO) {
        return Optional
                .ofNullable(roleDTO)
                .map((RoleDTO rDTO)->{
                    List<PermissionVO> pVOList = rDTO
                            .getPermissionDTOList()
                            .stream()
                            .map((PermissionDTO pDTO)->{
                                return new PermissionVO()
                                        .setId(pDTO.getId())
                                        .setName(pDTO.getName())
                                        .setStatus(pDTO.getStatus())
                                        .setCreateTime(pDTO.getCreateTime())
                                        .setUpdateTime(pDTO.getUpdateTime());
                            })
                            .collect(Collectors.toList());
                    List<UserVO> uVOList = rDTO
                            .getUserDTOList()
                            .stream()
                            .map((UserDTO uDTO)->{
                                return new UserVO()
                                        .setId(uDTO.getId())
                                        .setUsername(uDTO.getUsername())
                                        .setStatus(uDTO.getStatus())
                                        .setCreateTime(uDTO.getCreateTime())
                                        .setUpdateTime(uDTO.getUpdateTime());
                            })
                            .collect(Collectors.toList());
                    return new RoleVO()
                            .setId(rDTO.getId())
                            .setName(rDTO.getName())
                            .setStatus(rDTO.getStatus())
                            .setCreateTime(rDTO.getCreateTime())
                            .setUpdateTime(rDTO.getUpdateTime())
                            .setPermissionVOList(pVOList)
                            .setUserVOList(uVOList);
                })
                .orElse(null);
    }
    public static RoleDTO convertRequest(Integer id, RoleVO roleVO) {
        roleVO.setId(id);
        return convertRequest(roleVO);
    }
    public static List<RoleDTO> convertRequest(List<RoleVO> roleVOList) {
        return roleVOList
                .stream()
                .map((RoleVO rVO)->{
                    List<PermissionDTO> pDTOList = rVO
                            .getPermissionVOList()
                            .stream()
                            .map((PermissionVO pVO)->{
                                return new PermissionDTO()
                                        .setId(pVO.getId());
                            })
                            .collect(Collectors.toList());
                    List<UserDTO> uDTOList = rVO
                            .getUserVOList()
                            .stream()
                            .map((UserVO uVO)->{
                                return new UserDTO()
                                        .setId(uVO.getId());
                            })
                            .collect(Collectors.toList());
                    return new RoleDTO()
                            .setId(rVO.getId())
                            .setName(rVO.getName())
                            .setStatus(rVO.getStatus())
                            .setCreateTime(rVO.getCreateTime())
                            .setUpdateTime(rVO.getUpdateTime())
                            .setPermissionDTOList(pDTOList)
                            .setUserDTOList(uDTOList);
                })
                .collect(Collectors.toList());
    }
    public static List<RoleVO> convertResponse(List<RoleDTO> roleDTOList) {
        return Optional
                .ofNullable(roleDTOList)
                .orElse(new ArrayList<>())
                .stream()
                .map((RoleDTO rDTO)->{
                    List<PermissionVO> pVOList = rDTO
                            .getPermissionDTOList()
                            .stream()
                            .map((PermissionDTO pDTO)->{
                                return new PermissionVO()
                                        .setId(pDTO.getId())
                                        .setName(pDTO.getName())
                                        .setStatus(pDTO.getStatus())
                                        .setCreateTime(pDTO.getCreateTime())
                                        .setUpdateTime(pDTO.getUpdateTime());
                            })
                            .collect(Collectors.toList());
                    List<UserVO> uVOList = rDTO
                            .getUserDTOList()
                            .stream()
                            .map((UserDTO uDTO)->{
                                return new UserVO()
                                        .setId(uDTO.getId())
                                        .setUsername(uDTO.getUsername())
                                        .setStatus(uDTO.getStatus())
                                        .setCreateTime(uDTO.getCreateTime())
                                        .setUpdateTime(uDTO.getUpdateTime());
                            })
                            .collect(Collectors.toList());
                    return new RoleVO()
                            .setId(rDTO.getId())
                            .setName(rDTO.getName())
                            .setStatus(rDTO.getStatus())
                            .setCreateTime(rDTO.getCreateTime())
                            .setUpdateTime(rDTO.getUpdateTime())
                            .setPermissionVOList(pVOList)
                            .setUserVOList(uVOList);
                })
                .collect(Collectors.toList());
    }
}