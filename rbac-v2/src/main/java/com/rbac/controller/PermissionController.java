package com.rbac.controller;
import com.rbac.pojo.dto.PermissionDTO;
import com.rbac.pojo.dto.RoleDTO;
import com.rbac.pojo.vo.PermissionVO;
import com.rbac.pojo.vo.RoleVO;
import com.rbac.service.PermissionService;
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
@RequestMapping("/api/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;
    @PostMapping("")
    public ResponseResult insertPermission(@RequestBody PermissionVO permissionVO) {
        PermissionDTO permissionDTO = Optional
                .of(permissionVO)
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
                            .setName(pVO.getName())
                            .setStatus(pVO.getStatus())
                            .setCreateTime(pVO.getCreateTime())
                            .setUpdateTime(pVO.getUpdateTime())
                            .setRoleDTOList(rDTOList);
                })
                .get();
        permissionDTO = permissionService.insertPermission(permissionDTO);
        PermissionVO data = Optional
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "insertPermission", data);
    }
    @PostMapping("/batch")
    public ResponseResult insertPermissionList(@RequestBody List<PermissionVO> permissionVOList) {
        List<PermissionDTO> permissionDTOList = permissionVOList
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
                            .setName(pVO.getName())
                            .setStatus(pVO.getStatus())
                            .setCreateTime(pVO.getCreateTime())
                            .setUpdateTime(pVO.getUpdateTime())
                            .setRoleDTOList(rDTOList);
                })
                .collect(Collectors.toList());
        permissionDTOList = permissionService.insertPermissionList(permissionDTOList);
        List<PermissionVO> data = permissionDTOList
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "insertPermissionList", data);
    }
    @PutMapping("/{id}")
    public ResponseResult updatePermissionById(
            @PathVariable("id") Integer id,
            @RequestBody PermissionVO permissionVO
    ) {
        PermissionDTO permissionDTO = Optional
                .of(permissionVO)
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
                            .setId(id)
                            .setName(pVO.getName())
                            .setStatus(pVO.getStatus())
                            .setCreateTime(pVO.getCreateTime())
                            .setUpdateTime(pVO.getUpdateTime())
                            .setRoleDTOList(rDTOList);
                })
                .get();
        permissionDTO = permissionService.updatePermissionById(permissionDTO);
        PermissionVO data = Optional
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "updatePermissionById", data);
    }
    @PutMapping("/batch/{idList}")
    public ResponseResult updatePermissionByIdList(
            @PathVariable("idList") List<Integer> idList,
            @RequestBody PermissionVO permissionVO
    ) {
        PermissionDTO permissionDTO = Optional
                .of(permissionVO)
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
                .get();
        List<PermissionDTO> permissionDTOList = permissionService.updatePermissionByIdList(idList, permissionDTO);
        List<PermissionVO> data = permissionDTOList
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "updatePermissionByIdList", data);
    }
    @PutMapping("/batch")
    public ResponseResult updatePermissionList(@RequestBody List<PermissionVO> permissionVOList) {
        List<PermissionDTO> permissionDTOList = permissionVOList
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
        permissionDTOList = permissionService.updatePermissionList(permissionDTOList);
        List<PermissionVO> data = permissionDTOList
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "updatePermissionList", data);
    }
    @GetMapping("/{id}")
    public ResponseResult selectPermissionById(@PathVariable("id") Integer id) {
        PermissionDTO permissionDTO = permissionService.selectPermissionById(id);
        PermissionVO data = Optional
                .of(permissionDTO)
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "selectPermissionById", data);
    }
    @GetMapping("")
    public ResponseResult selectPermissionByPagination(Pagination pagination) {
        List<PermissionDTO> permissionDTOList = permissionService.selectPermissionByPagination(pagination);
        List<PermissionVO> data = permissionDTOList
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
        return FormatUtils.formatResponseResult(HttpStatus.OK.value(), "selectPermissionByPagination", data);
    }
}