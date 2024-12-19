package com.rbac.entity;
import java.time.LocalDateTime;
public class Permission {
    private Integer id;
    private String name;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    public Permission() {

    }
    public Permission(Integer id, String name, Integer status, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
    public Integer getId() {
        return id;
    }
    public Permission setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }
    public Permission setName(String name) {
        this.name = name;
        return this;
    }
    public Integer getStatus() {
        return status;
    }
    public Permission setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public Permission setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
    public Permission setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}