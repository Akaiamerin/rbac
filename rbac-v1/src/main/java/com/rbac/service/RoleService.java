package com.rbac.service;
import com.rbac.pojo.dto.RoleDTO;
import com.rbac.utils.Pagination;
import java.util.List;
public interface RoleService {
    RoleDTO insertRole(RoleDTO roleDTO);
    RoleDTO deleteRoleById(Integer id);
    RoleDTO updateRoleById(RoleDTO roleDTO);
    RoleDTO selectRoleById(Integer id);
    List<RoleDTO> selectRoleByPagination(Pagination pagination);
}