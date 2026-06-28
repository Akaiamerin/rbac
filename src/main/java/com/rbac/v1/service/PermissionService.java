package com.rbac.v1.service;
import com.rbac.v1.pojo.dto.PermissionDTO;
import com.rbac.v1.utils.Pagination;
import java.util.List;
public interface PermissionService {
    PermissionDTO insertPermission(PermissionDTO permissionDTO);
    PermissionDTO deletePermissionById(Integer id);
    PermissionDTO updatePermissionById(PermissionDTO permissionDTO);
    PermissionDTO selectPermissionById(Integer id);
    List<PermissionDTO> selectPermissionByPagination(Pagination pagination);
}