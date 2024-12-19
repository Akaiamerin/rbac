package com.rbac.pojo.dto;
import java.time.LocalDateTime;
import java.util.List;
public class PermissionDTO {
    private Integer id;
    private String name;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<RoleDTO> roleDTOList;
    public PermissionDTO() {

    }
    public PermissionDTO(Integer id, String name, Integer status, LocalDateTime createTime, LocalDateTime updateTime, List<RoleDTO> roleDTOList) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
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
    public Integer getStatus() {
        return status;
    }
    public PermissionDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public PermissionDTO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
    public PermissionDTO setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
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