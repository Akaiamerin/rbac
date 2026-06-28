package com.rbac.v2.controller;
import com.rbac.v2.converter.PermissionControllerConverter;
import com.rbac.v2.pojo.dto.PermissionDTO;
import com.rbac.v2.pojo.vo.PermissionVO;
import com.rbac.v2.service.PermissionService;
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
@RequestMapping("/api/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;
    @PostMapping("")
    public UnifiedResponseStructure insertPermission(@RequestBody PermissionVO permissionVO) {
        PermissionDTO permissionDTO = PermissionControllerConverter.convertRequest(permissionVO);
        permissionDTO = permissionService.insertPermission(permissionDTO);
        PermissionVO data = PermissionControllerConverter.convertResponse(permissionDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getClassName(), data);
    }
    @PostMapping("/batch")
    public UnifiedResponseStructure insertPermissionList(@RequestBody List<PermissionVO> permissionVOList) {
        List<PermissionDTO> permissionDTOList = PermissionControllerConverter.convertRequest(permissionVOList);
        permissionDTOList = permissionService.insertPermissionList(permissionDTOList);
        List<PermissionVO> data = PermissionControllerConverter.convertResponse(permissionDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getClassName(), data);
    }
    @PutMapping("/{id}")
    public UnifiedResponseStructure updatePermissionById(
            @PathVariable("id") Integer id,
            @RequestBody PermissionVO permissionVO
    ) {
        PermissionDTO permissionDTO = PermissionControllerConverter.convertRequest(id, permissionVO);
        permissionDTO = permissionService.updatePermissionById(permissionDTO);
        PermissionVO data = PermissionControllerConverter.convertResponse(permissionDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getClassName(), data);
    }
    @PutMapping("/batch/{idList}")
    public UnifiedResponseStructure updatePermissionByIdList(
            @PathVariable("idList") List<Integer> idList,
            @RequestBody PermissionVO permissionVO
    ) {
        PermissionDTO permissionDTO = PermissionControllerConverter.convertRequest(permissionVO);
        List<PermissionDTO> permissionDTOList = permissionService.updatePermissionByIdList(idList, permissionDTO);
        List<PermissionVO> data = PermissionControllerConverter.convertResponse(permissionDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getClassName(), data);
    }
    @PutMapping("/batch")
    public UnifiedResponseStructure updatePermissionList(@RequestBody List<PermissionVO> permissionVOList) {
        List<PermissionDTO> permissionDTOList = PermissionControllerConverter.convertRequest(permissionVOList);
        permissionDTOList = permissionService.updatePermissionList(permissionDTOList);
        List<PermissionVO> data = PermissionControllerConverter.convertResponse(permissionDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getClassName(), data);
    }
    @GetMapping("/{id}")
    public UnifiedResponseStructure selectPermissionById(@PathVariable("id") Integer id) {
        PermissionDTO permissionDTO = permissionService.selectPermissionById(id);
        PermissionVO data = PermissionControllerConverter.convertResponse(permissionDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getClassName(), data);
    }
    @GetMapping("/batch/{idList}")
    public UnifiedResponseStructure selectPermissionByIdList(@PathVariable("idList") List<Integer> idList) {
        List<PermissionDTO> permissionDTOList = permissionService.selectPermissionByIdList(idList);
        List<PermissionVO> data = PermissionControllerConverter.convertResponse(permissionDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getClassName(), data);
    }
    @GetMapping("")
    public UnifiedResponseStructure selectPermissionByPagination(Pagination pagination) {
        List<PermissionDTO> permissionDTOList = permissionService.selectPermissionByPagination(pagination);
        List<PermissionVO> data = PermissionControllerConverter.convertResponse(permissionDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getClassName(), data);
    }
}