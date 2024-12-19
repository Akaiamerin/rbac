package com.rbac.entity;
import java.time.LocalDateTime;
public class RolePermission {
    private Integer id;
    private Integer roleId;
    private Integer permissionId;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    public RolePermission() {

    }
    public RolePermission(Integer id, Integer roleId, Integer permissionId, Integer status, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
    public Integer getId() {
        return id;
    }
    public RolePermission setId(Integer id) {
        this.id = id;
        return this;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public RolePermission setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }
    public Integer getPermissionId() {
        return permissionId;
    }
    public RolePermission setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
        return this;
    }
    public Integer getStatus() {
        return status;
    }
    public RolePermission setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public RolePermission setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
    public RolePermission setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}