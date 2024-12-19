package com.rbac.pojo.dto;
import java.util.List;
public class PermissionDTO {
    private Integer id;
    private String name;
    private List<RoleDTO> roleDTOList;
    public PermissionDTO() {

    }
    public PermissionDTO(Integer id, String name, List<RoleDTO> roleDTOList) {
        this.id = id;
        this.name = name;
        this.roleDTOList = roleDTOList;
    }
    public Integer getId() {
        return id;
    }
    public PermissionDTO setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }
    public PermissionDTO setName(String name) {
        this.name = name;
        return this;
    }
    public List<RoleDTO> getRoleDTOList() {
        return roleDTOList;
    }
    public PermissionDTO setRoleDTOList(List<RoleDTO> roleDTOList) {
        this.roleDTOList = roleDTOList;
        return this;
    }
}