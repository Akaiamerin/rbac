package com.rbac.entity;
public class UserRole {
    private Integer id;
    private Integer userId;
    private Integer roleId;
    public UserRole() {

    }
    public UserRole(Integer id, Integer userId, Integer roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }
    public Integer getId() {
        return id;
    }
    public UserRole setId(Integer id) {
        this.id = id;
        return this;
    }
    public Integer getUserId() {
        return userId;
    }
    public UserRole setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public UserRole setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }
}