package com.rbac.service;
import com.rbac.pojo.dto.RoleDTO;
import com.rbac.utils.Pagination;
import java.util.List;
public interface RoleService {
    RoleDTO insertRole(RoleDTO roleDTO);
    List<RoleDTO> insertRoleList(List<RoleDTO> roleDTOList);
    RoleDTO updateRoleById(RoleDTO roleDTO);
    List<RoleDTO> updateRoleByIdList(List<Integer> idList, RoleDTO roleDTO);
    List<RoleDTO> updateRoleList(List<RoleDTO> roleDTOList);
    RoleDTO selectRoleById(Integer id);
    List<RoleDTO> selectRoleByIdList(List<Integer> idList);
    List<RoleDTO> selectRoleByPagination(Pagination pagination);
}