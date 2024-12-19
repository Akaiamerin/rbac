package com.rbac.controller;
import com.rbac.pojo.dto.RoleDTO;
import com.rbac.pojo.dto.UserDTO;
import com.rbac.pojo.vo.RoleVO;
import com.rbac.pojo.vo.UserVO;
import com.rbac.service.UserService;
import com.rbac.utils.FormatUtils;
import com.rbac.utils.Pagination;
import com.rbac.utils.ResponseResult;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping("")
    public ResponseResult insertUser(@RequestBody UserVO userVO) {
        UserDTO userDTO = Optional
                .of(userVO)
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
                            .setUsername(uVO.getUsername())
                            .setPassword(uVO.getPassword())
                            .setRoleDTOList(rDTOList);
                })
                .get();
        userDTO = userService.insertUser(userDTO);
        UserVO data = Optional
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "insertUser", data);
    }
    @DeleteMapping("/{id}")
    public ResponseResult deleteUserById(@PathVariable("id") Integer id) {
        UserDTO userDTO = userService.deleteUserById(id);
        UserVO data = Optional
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "deleteUserById", data);
    }
    @PutMapping("/{id}")
    public ResponseResult updateUserById(
            @PathVariable("id") Integer id,
            @RequestBody UserVO userVO
    ) {
        UserDTO userDTO = Optional
                .of(userVO)
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
                            .setId(id)
                            .setUsername(uVO.getUsername())
                            .setRoleDTOList(rDTOList);
                })
                .get();
        userDTO = userService.updateUserById(userDTO);
        UserVO data = Optional
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "updateUserById", data);
    }
    @GetMapping("/{id}")
    public ResponseResult selectUserById(@PathVariable("id") Integer id) {
        UserDTO userDTO = userService.selectUserById(id);
        UserVO data = Optional
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "selectUserById", data);
    }
    @GetMapping("")
    public ResponseResult selectUserByPagination(Pagination pagination) {
        List<UserDTO> userDTOList = userService.selectUserByPagination(pagination);
        List<UserVO> data = userDTOList
                .stream()
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
                .collect(Collectors.toList());
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "selectUserByPagination", data);
    }
}