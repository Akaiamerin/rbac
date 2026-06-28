package com.rbac.v1.converter;
import com.rbac.v1.exception.BusinessException;
import com.rbac.v1.pojo.dto.RoleDTO;
import com.rbac.v1.pojo.dto.UserDTO;
import com.rbac.v1.pojo.vo.RoleVO;
import com.rbac.v1.pojo.vo.UserVO;
import com.rbac.v1.utils.StackMetadataWalkerUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
public class UserControllerConverter {
    private UserControllerConverter() {

    }
    public static UserDTO convert(UserVO userVO) {
        return Optional
                .ofNullable(userVO)
                .map((UserVO uVO)->{
                    List<RoleDTO> rDTOList = uVO
                            .getRoleVOList()
                            .stream()
                            .map((RoleVO rvo)->{
                                return new RoleDTO()
                                        .setId(rvo.getId());
                            })
                            .collect(Collectors.toList());
                    return new UserDTO()
                            .setId(uVO.getId())
                            .setUsername(uVO.getUsername())
                            .setPassword(uVO.getPassword())
                            .setRoleDTOList(rDTOList);
                })
                .orElseThrow(()->{
                    return new BusinessException(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName());
                });
    }
    public static UserVO convert(UserDTO userDTO) {
        return Optional
                .ofNullable(userDTO)
                .map((UserDTO uTDO)->{
                    List<RoleVO> rVOList = uTDO
                            .getRoleDTOList()
                            .stream()
                            .map((RoleDTO rDTO)->{
                                return new RoleVO()
                                        .setId(rDTO.getId())
                                        .setName(rDTO.getName());
                            })
                            .collect(Collectors.toList());
                    return new UserVO()
                            .setId(uTDO.getId())
                            .setUsername(uTDO.getUsername())
                            .setRoleVOList(rVOList);
                })
                .orElse(null);
    }
    public static List<UserVO> convertBatch(List<UserDTO> userDTOList) {
        return Optional
                .ofNullable(userDTOList)
                .orElse(new ArrayList<>())
                .stream()
                .map((UserDTO uDTO)->{
                    return convert(uDTO);
                })
                .collect(Collectors.toList());
    }
}