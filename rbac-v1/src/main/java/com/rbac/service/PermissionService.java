package com.rbac.service;
import com.rbac.pojo.dto.PermissionDTO;
import com.rbac.utils.Pagination;
import java.util.List;
public interface PermissionService {
    PermissionDTO insertPermission(PermissionDTO permissionDTO);
    PermissionDTO deletePermissionById(Integer id);
    PermissionDTO updatePermissionById(PermissionDTO permissionDTO);
    PermissionDTO selectPermissionById(Integer id);
    List<PermissionDTO> selectPermissionByPagination(Pagination pagination);
}