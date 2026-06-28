package com.rbac.v1.service;
import com.rbac.v1.pojo.dto.RoleDTO;
import com.rbac.v1.utils.Pagination;
import java.util.List;
public interface RoleService {
    RoleDTO insertRole(RoleDTO roleDTO);
    RoleDTO deleteRoleById(Integer id);
    RoleDTO updateRoleById(RoleDTO roleDTO);
    RoleDTO selectRoleById(Integer id);
    List<RoleDTO> selectRoleByPagination(Pagination pagination);
}