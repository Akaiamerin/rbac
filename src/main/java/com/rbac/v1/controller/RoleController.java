package com.rbac.v1.controller;
import com.rbac.v1.converter.RoleControllerConverter;
import com.rbac.v1.pojo.dto.RoleDTO;
import com.rbac.v1.pojo.vo.RoleVO;
import com.rbac.v1.service.RoleService;
import com.rbac.v1.utils.Pagination;
import com.rbac.v1.utils.StackMetadataWalkerUtils;
import com.rbac.v1.utils.UnifiedResponseStructure;
import com.rbac.v1.utils.UnifiedResponseStructureFormatterUtils;
import jakarta.annotation.Resource;
import java.util.List;
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
@RequestMapping("/api/role")
public class RoleController {
    @Resource
    private RoleService roleService;
    @PostMapping("")
    public UnifiedResponseStructure insertRole(@RequestBody RoleVO roleVO) {
        RoleDTO roleDTO = RoleControllerConverter.convert(roleVO);
        roleDTO = roleService.insertRole(roleDTO);
        RoleVO data = RoleControllerConverter.convert(roleDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @DeleteMapping("/{id}")
    public UnifiedResponseStructure deleteRoleById(@PathVariable("id") Integer id) {
        RoleDTO roleDTO = roleService.deleteRoleById(id);
        RoleVO data = RoleControllerConverter.convert(roleDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @PutMapping("/{id}")
    public UnifiedResponseStructure updateRoleById(
            @PathVariable("id") Integer id,
            @RequestBody RoleVO roleVO
    ) {
        roleVO.setId(id);
        RoleDTO roleDTO = RoleControllerConverter.convert(roleVO);
        roleDTO = roleService.updateRoleById(roleDTO);
        RoleVO data = RoleControllerConverter.convert(roleDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @GetMapping("/{id}")
    public UnifiedResponseStructure selectRoleById(@PathVariable("id") Integer id) {
        RoleDTO roleDTO = roleService.selectRoleById(id);
        RoleVO data = RoleControllerConverter.convert(roleDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @GetMapping("")
    public UnifiedResponseStructure selectRoleByPagination(Pagination pagination) {
        List<RoleDTO> roleDTOList = roleService.selectRoleByPagination(pagination);
        List<RoleVO> data = RoleControllerConverter.convertBatch(roleDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
}