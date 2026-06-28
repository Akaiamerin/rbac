package com.rbac.v2.controller;
import com.rbac.v2.converter.RoleControllerConverter;
import com.rbac.v2.pojo.dto.RoleDTO;
import com.rbac.v2.pojo.vo.RoleVO;
import com.rbac.v2.service.RoleService;
import com.rbac.v2.utils.Pagination;
import com.rbac.v2.utils.StackMetadataWalkerUtils;
import com.rbac.v2.utils.UnifiedResponseStructure;
import com.rbac.v2.utils.UnifiedResponseStructureFormatterUtils;
import jakarta.annotation.Resource;
import java.util.List;
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
    public UnifiedResponseStructure insertRole(@RequestBody RoleVO roleVO) {
        RoleDTO roleDTO = RoleControllerConverter.convertRequest(roleVO);
        roleDTO = roleService.insertRole(roleDTO);
        RoleVO data = RoleControllerConverter.convertResponse(roleDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @PostMapping("/batch")
    public UnifiedResponseStructure insertRoleList(@RequestBody List<RoleVO> roleVOList) {
        List<RoleDTO> roleDTOList = RoleControllerConverter.convertRequest(roleVOList);
        roleDTOList = roleService.insertRoleList(roleDTOList);
        List<RoleVO> data = RoleControllerConverter.convertResponse(roleDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @PutMapping("/{id}")
    public UnifiedResponseStructure updateRoleById(
            @PathVariable("id") Integer id,
            @RequestBody RoleVO roleVO
    ) {
        RoleDTO roleDTO = RoleControllerConverter.convertRequest(id, roleVO);
        roleDTO = roleService.updateRoleById(roleDTO);
        RoleVO data = RoleControllerConverter.convertResponse(roleDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @PutMapping("/batch/{idList}")
    public UnifiedResponseStructure updateRoleByIdList(
            @PathVariable("idList") List<Integer> idList,
            @RequestBody RoleVO roleVO
    ) {
        RoleDTO roleDTO = RoleControllerConverter.convertRequest(roleVO);
        List<RoleDTO> roleDTOList = roleService.updateRoleByIdList(idList, roleDTO);
        List<RoleVO> data = RoleControllerConverter.convertResponse(roleDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @PutMapping("/batch")
    public UnifiedResponseStructure updateRoleList(@RequestBody List<RoleVO> roleVOList) {
        List<RoleDTO> roleDTOList = RoleControllerConverter.convertRequest(roleVOList);
        roleDTOList = roleService.updateRoleList(roleDTOList);
        List<RoleVO> data = RoleControllerConverter.convertResponse(roleDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @GetMapping("/{id}")
    public UnifiedResponseStructure selectRoleById(@PathVariable("id") Integer id) {
        RoleDTO roleDTO = roleService.selectRoleById(id);
        RoleVO data = RoleControllerConverter.convertResponse(roleDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), "selectRoleById", data);
    }
    @GetMapping("/batch/{idList}")
    public UnifiedResponseStructure selectRoleByIdList(@PathVariable("idList") List<Integer> idList) {
        List<RoleDTO> roleDTOList = roleService.selectRoleByIdList(idList);
        List<RoleVO> data = RoleControllerConverter.convertResponse(roleDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getClassName(), data);
    }
    @GetMapping("")
    public UnifiedResponseStructure selectRoleByPagination(Pagination pagination) {
        List<RoleDTO> roleDTOList = roleService.selectRoleByPagination(pagination);
        List<RoleVO> data = RoleControllerConverter.convertResponse(roleDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
}