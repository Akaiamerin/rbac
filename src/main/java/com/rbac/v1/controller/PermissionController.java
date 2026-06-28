package com.rbac.v1.controller;
import com.rbac.v1.converter.PermissionControllerConverter;
import com.rbac.v1.pojo.dto.PermissionDTO;
import com.rbac.v1.pojo.vo.PermissionVO;
import com.rbac.v1.service.PermissionService;
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
@RequestMapping("/api/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;
    @PostMapping("")
    public UnifiedResponseStructure insertPermission(@RequestBody PermissionVO permissionVO) {
        PermissionDTO permissionDTO = PermissionControllerConverter.convert(permissionVO);
        permissionDTO = permissionService.insertPermission(permissionDTO);
        PermissionVO data = PermissionControllerConverter.convert(permissionDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @DeleteMapping("/{id}")
    public UnifiedResponseStructure deletePermissionById(@PathVariable("id") Integer id) {
        PermissionDTO permissionDTO = permissionService.deletePermissionById(id);
        PermissionVO data = PermissionControllerConverter.convert(permissionDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @PutMapping("/{id}")
    public UnifiedResponseStructure updatePermissionById(
            @PathVariable("id") Integer id,
            @RequestBody PermissionVO permissionVO
    ) {
        permissionVO.setId(id);
        PermissionDTO permissionDTO = PermissionControllerConverter.convert(permissionVO);
        permissionDTO = permissionService.updatePermissionById(permissionDTO);
        PermissionVO data = PermissionControllerConverter.convert(permissionDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @GetMapping("/{id}")
    public UnifiedResponseStructure selectPermissionById(@PathVariable("id") Integer id) {
        PermissionDTO permissionDTO = permissionService.selectPermissionById(id);
        PermissionVO data = PermissionControllerConverter.convert(permissionDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @GetMapping("")
    public UnifiedResponseStructure selectPermissionByPagination(Pagination pagination) {
        List<PermissionDTO> permissionDTOList = permissionService.selectPermissionByPagination(pagination);
        List<PermissionVO> data = PermissionControllerConverter.convertBatch(permissionDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
}