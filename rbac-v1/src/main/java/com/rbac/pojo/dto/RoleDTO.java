package com.rbac.pojo.dto;
import java.util.List;
public class RoleDTO {
    private Integer id;
    private String name;
    private List<PermissionDTO> permissionDTOList;
    private List<UserDTO> userDTOList;
    public RoleDTO() {

    }
    public RoleDTO(Integer id, String name, List<PermissionDTO> permissionDTOList, List<UserDTO> userDTOList) {
        this.id = id;
        this.name = name;
        this.permissionDTOList = permissionDTOList;
        this.userDTOList = userDTOList;
    }
    public Integer getId() {
        return id;
    }
    public RoleDTO setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }
    public RoleDTO setName(String name) {
        this.name = name;
        return this;
    }
    public List<PermissionDTO> getPermissionDTOList() {
        return permissionDTOList;
    }
    public RoleDTO setPermissionDTOList(List<PermissionDTO> permissionDTOList) {
        this.permissionDTOList = permissionDTOList;
        return this;
    }
    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }
    public RoleDTO setUserDTOList(List<UserDTO> userDTOList) {
        this.userDTOList = userDTOList;
        return this;
    }
}