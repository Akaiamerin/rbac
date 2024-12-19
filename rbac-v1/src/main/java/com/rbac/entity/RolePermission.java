package com.rbac.entity;
public class RolePermission {
    private Integer id;
    private Integer roleId;
    private Integer permissionId;
    public RolePermission() {

    }
    public RolePermission(Integer id, Integer roleId, Integer permissionId) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
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
}