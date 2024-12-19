package com.rbac.pojo.dto;
import java.time.LocalDateTime;
import java.util.List;
public class RoleDTO {
    private Integer id;
    private String name;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<PermissionDTO> permissionDTOList;
    private List<UserDTO> userDTOList;
    public RoleDTO() {

    }
    public RoleDTO(Integer id, String name, Integer status, LocalDateTime createTime, LocalDateTime updateTime, List<PermissionDTO> permissionDTOList, List<UserDTO> userDTOList) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
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
    public Integer getStatus() {
        return status;
    }
    public RoleDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public RoleDTO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
    public RoleDTO setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
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