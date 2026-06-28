package com.rbac.v1.converter;
import com.rbac.v1.exception.BusinessException;
import com.rbac.v1.pojo.dto.PermissionDTO;
import com.rbac.v1.pojo.dto.RoleDTO;
import com.rbac.v1.pojo.dto.UserDTO;
import com.rbac.v1.pojo.vo.PermissionVO;
import com.rbac.v1.pojo.vo.RoleVO;
import com.rbac.v1.pojo.vo.UserVO;
import com.rbac.v1.utils.StackMetadataWalkerUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
public class RoleControllerConverter {
    private RoleControllerConverter() {

    }
    public static RoleDTO convert(RoleVO roleVO) {
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
                            .setPermissionDTOList(pDTOList)
                            .setUserDTOList(uDTOList);
                })
                .orElseThrow(()->{
                    return new BusinessException(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName());
                });
    }
    public static RoleVO convert(RoleDTO roleDTO) {
        return Optional
                .ofNullable(roleDTO)
                .map((RoleDTO rDTO)->{
                    List<PermissionVO> pVOList = rDTO
                            .getPermissionDTOList()
                            .stream()
                            .map((PermissionDTO pDTO)->{
                                return new PermissionVO()
                                        .setId(pDTO.getId())
                                        .setName(pDTO.getName());
                            })
                            .collect(Collectors.toList());
                    List<UserVO> uVOList = rDTO
                            .getUserDTOList()
                            .stream()
                            .map((UserDTO uDTO)->{
                                return new UserVO()
                                        .setId(uDTO.getId())
                                        .setUsername(uDTO.getUsername());
                            })
                            .collect(Collectors.toList());
                    return new RoleVO()
                            .setId(rDTO.getId())
                            .setName(rDTO.getName())
                            .setPermissionVOList(pVOList)
                            .setUserVOList(uVOList);
                })
                .orElse(null);
    }
    public static List<RoleVO> convertBatch(List<RoleDTO> roleDTOList) {
        return Optional
                .ofNullable(roleDTOList)
                .orElse(new ArrayList<>())
                .stream()
                .map((RoleDTO rDTO)->{
                    return convert(rDTO);
                })
                .collect(Collectors.toList());
    }
}