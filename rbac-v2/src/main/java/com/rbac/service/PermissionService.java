package com.rbac.service;
import com.rbac.pojo.dto.PermissionDTO;
import com.rbac.utils.Pagination;
import java.util.List;
public interface PermissionService {
    PermissionDTO insertPermission(PermissionDTO permissionDTO);
    List<PermissionDTO> insertPermissionList(List<PermissionDTO> permissionDTOList);
    PermissionDTO updatePermissionById(PermissionDTO permissionDTO);
    List<PermissionDTO> updatePermissionByIdList(List<Integer> idList, PermissionDTO permissionDTO);
    List<PermissionDTO> updatePermissionList(List<PermissionDTO> permissionDTOList);
    PermissionDTO selectPermissionById(Integer id);
    List<PermissionDTO> selectPermissionByIdList(List<Integer> idList);
    List<PermissionDTO> selectPermissionByPagination(Pagination pagination);
}