package com.rbac.v2.converter;
import com.rbac.v2.exception.BusinessException;
import com.rbac.v2.pojo.dto.RoleDTO;
import com.rbac.v2.pojo.dto.UserDTO;
import com.rbac.v2.pojo.vo.RoleVO;
import com.rbac.v2.pojo.vo.UserVO;
import com.rbac.v2.utils.StackMetadataWalkerUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
public class UserControllerConverter {
    private UserControllerConverter() {

    }
    public static UserDTO convertRequest(UserVO userVO) {
        return Optional
                .ofNullable(userVO)
                .map((UserVO uVO)->{
                    List<RoleDTO> rDTOList = uVO
                            .getRoleVOList()
                            .stream()
                            .map((RoleVO rVO)->{
                                return new RoleDTO()
                                        .setId(rVO.getId());
                            })
                            .collect(Collectors.toList());
                    return new UserDTO()
                            .setId(uVO.getId())
                            .setUsername(uVO.getUsername())
                            .setPassword(uVO.getPassword())
                            .setStatus(uVO.getStatus())
                            .setCreateTime(uVO.getCreateTime())
                            .setUpdateTime(uVO.getUpdateTime())
                            .setRoleDTOList(rDTOList);
                })
                .orElseThrow(()->{
                    return new BusinessException(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName());
                });
    }
    public static UserVO convertResponse(UserDTO userDTO) {
        return Optional
                .ofNullable(userDTO)
                .map((UserDTO uTDO)->{
                    List<RoleVO> rVOList = uTDO
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
                    return new UserVO()
                            .setId(uTDO.getId())
                            .setUsername(uTDO.getUsername())
                            .setStatus(uTDO.getStatus())
                            .setCreateTime(uTDO.getCreateTime())
                            .setUpdateTime(uTDO.getUpdateTime())
                            .setRoleVOList(rVOList);
                })
                .orElse(null);
    }
    public static UserDTO convertRequest(Integer id, UserVO userVO) {
        userVO.setId(id);
        return convertRequest(userVO);
    }
    public static List<UserDTO> convertRequest(List<UserVO> userVOList) {
        return userVOList
                .stream()
                .map((UserVO uVO)->{
                    List<RoleDTO> rDTOList = uVO
                            .getRoleVOList()
                            .stream()
                            .map((RoleVO rVO)->{
                                return new RoleDTO()
                                        .setId(rVO.getId());
                            })
                            .collect(Collectors.toList());
                    return new UserDTO()
                            .setId(uVO.getId())
                            .setUsername(uVO.getUsername())
                            .setPassword(uVO.getPassword())
                            .setStatus(uVO.getStatus())
                            .setCreateTime(uVO.getCreateTime())
                            .setUpdateTime(uVO.getUpdateTime())
                            .setRoleDTOList(rDTOList);
                })
                .collect(Collectors.toList());
    }
    public static List<UserVO> convertResponse(List<UserDTO> userDTOList) {
        return Optional
                .ofNullable(userDTOList)
                .orElse(new ArrayList<>())
                .stream()
                .map((UserDTO uTDO)->{
                    List<RoleVO> rVOList = uTDO
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
                    return new UserVO()
                            .setId(uTDO.getId())
                            .setUsername(uTDO.getUsername())
                            .setStatus(uTDO.getStatus())
                            .setCreateTime(uTDO.getCreateTime())
                            .setUpdateTime(uTDO.getUpdateTime())
                            .setRoleVOList(rVOList);
                })
                .collect(Collectors.toList());
    }
}