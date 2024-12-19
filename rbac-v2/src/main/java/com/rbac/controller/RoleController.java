package com.rbac.controller;
import com.rbac.pojo.dto.PermissionDTO;
import com.rbac.pojo.dto.RoleDTO;
import com.rbac.pojo.dto.UserDTO;
import com.rbac.pojo.vo.PermissionVO;
import com.rbac.pojo.vo.RoleVO;
import com.rbac.pojo.vo.UserVO;
import com.rbac.service.RoleService;
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
@RequestMapping("/api/role")
public class RoleController {
    @Resource
    private RoleService roleService;
    @PostMapping("")
    public ResponseResult insertRole(@RequestBody RoleVO roleVO) {
        RoleDTO roleDTO = Optional
                .of(roleVO)
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
                            .setName(rVO.getName())
                            .setStatus(rVO.getStatus())
                            .setCreateTime(rVO.getCreateTime())
                            .setUpdateTime(rVO.getUpdateTime())
                            .setPermissionDTOList(pDTOList)
                            .setUserDTOList(uDTOList);
                })
                .get();
        roleDTO = roleService.insertRole(roleDTO);
        RoleVO data = Optional
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "insertRole", data);
    }
    @PostMapping("/batch")
    public ResponseResult insertRoleList(@RequestBody List<RoleVO> roleVOList) {
        List<RoleDTO> roleDTOList = roleVOList
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
                    List<UserDTO> uDTOList = rVO.getUserVOList()
                            .stream()
                            .map((UserVO uVO)->{
                                return new UserDTO()
                                        .setId(uVO.getId());
                            })
                            .collect(Collectors.toList());
                    return new RoleDTO()
                            .setName(rVO.getName())
                            .setStatus(rVO.getStatus())
                            .setCreateTime(rVO.getCreateTime())
                            .setUpdateTime(rVO.getUpdateTime())
                            .setPermissionDTOList(pDTOList)
                            .setUserDTOList(uDTOList);
                })
                .collect(Collectors.toList());
        roleDTOList = roleService.insertRoleList(roleDTOList);
        List<RoleVO> data = roleDTOList
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "insertRoleList", data);
    }
    @PutMapping("/{id}")
    public ResponseResult updateRoleById(
            @PathVariable("id") Integer id,
            @RequestBody RoleVO roleVO
    ) {
        RoleDTO roleDTO = Optional
                .of(roleVO)
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
                            .setId(id)
                            .setName(rVO.getName())
                            .setStatus(rVO.getStatus())
                            .setCreateTime(rVO.getCreateTime())
                            .setUpdateTime(rVO.getUpdateTime())
                            .setPermissionDTOList(pDTOList)
                            .setUserDTOList(uDTOList);
                })
                .get();
        roleDTO = roleService.updateRoleById(roleDTO);
        RoleVO data = Optional
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "updateRoleById", data);
    }
    @PutMapping("/batch/{idList}")
    public ResponseResult updateRoleByIdList(
            @PathVariable("idList") List<Integer> idList,
            @RequestBody RoleVO roleVO
    ) {
        RoleDTO roleDTO = Optional
                .of(roleVO)
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
                .get();
        List<RoleDTO> roleDTOList = roleService.updateRoleByIdList(idList, roleDTO);
        List<RoleVO> data = roleDTOList
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "updateRoleByIdList", data);
    }
    @PutMapping("/batch")
    public ResponseResult updateRoleList(@RequestBody List<RoleVO> roleVOList) {
        List<RoleDTO> roleDTOList = roleVOList
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
                .collect(Collectors.toList());
        roleDTOList = roleService.updateRoleList(roleDTOList);
        List<RoleVO> data = roleDTOList
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "updateRoleList", data);
    }
    @GetMapping("/{id}")
    public ResponseResult selectRoleById(@PathVariable("id") Integer id) {
        RoleDTO roleDTO = roleService.selectRoleById(id);
        RoleVO data = Optional
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "selectRoleById", data);
    }
    @GetMapping("")
    public ResponseResult selectRoleByPagination(Pagination pagination) {
        List<RoleDTO> roleDTOList = roleService.selectRoleByPagination(pagination);
        List<RoleVO> data = roleDTOList
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "selectRoleByPagination", data);
    }
}