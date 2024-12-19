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
                            .map((RoleVO rVO)->{
                                return new RoleDTO()
                                        .setId(rVO.getId());
                            })
                            .collect(Collectors.toList());
                    return new UserDTO()
                            .setUsername(uVO.getUsername())
                            .setPassword(uVO.getPassword())
                            .setStatus(uVO.getStatus())
                            .setCreateTime(uVO.getCreateTime())
                            .setUpdateTime(uVO.getUpdateTime())
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "insertUser", data);
    }
    @PostMapping("/batch")
    public ResponseResult insertUserList(@RequestBody List<UserVO> userVOList) {
        List<UserDTO> userDTOList = userVOList
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
                            .setUsername(uVO.getUsername())
                            .setPassword(uVO.getPassword())
                            .setStatus(uVO.getStatus())
                            .setCreateTime(uVO.getCreateTime())
                            .setUpdateTime(uVO.getUpdateTime())
                            .setRoleDTOList(rDTOList);
                })
                .collect(Collectors.toList());
        userDTOList = userService.insertUserList(userDTOList);
        List<UserVO> data = userDTOList
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "insertUserList", data);
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
                            .map((RoleVO rVO)->{
                                return new RoleDTO()
                                        .setId(rVO.getId());
                            })
                            .collect(Collectors.toList());
                    return new UserDTO()
                            .setId(id)
                            .setUsername(uVO.getUsername())
                            .setPassword(uVO.getPassword())
                            .setStatus(uVO.getStatus())
                            .setCreateTime(uVO.getCreateTime())
                            .setUpdateTime(uVO.getUpdateTime())
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "updateUserById", data);
    }
    @PutMapping("/batch/{idList}")
    public ResponseResult updateUserByIdList(
            @PathVariable("idList") List<Integer> idList,
            @RequestBody UserVO userVO
    ) {
        UserDTO userDTO = Optional
                .of(userVO)
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
                .get();
        List<UserDTO> userDTOList = userService.updateUserByIdList(idList, userDTO);
        List<UserVO> data = userDTOList
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "updateUserByIdList", data);
    }
    @PutMapping("/batch")
    public ResponseResult updateUserList(@RequestBody List<UserVO> userVOList) {
        List<UserDTO> userDTOList = userVOList
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
        userDTOList = userService.updateUserList(userDTOList);
        List<UserVO> data = userDTOList
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "updateUserList", data);
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "selectUserByPagination", data);
    }
}