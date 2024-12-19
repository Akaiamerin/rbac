package com.rbac.entity;
import java.time.LocalDateTime;
public class UserRole {
    private Integer id;
    private Integer userId;
    private Integer roleId;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    public UserRole() {

    }
    public UserRole(Integer id, Integer userId, Integer roleId, Integer status, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
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
    public Integer getStatus() {
        return status;
    }
    public UserRole setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public UserRole setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
    public UserRole setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}