package com.rbac.pojo.vo;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
public class PermissionVO {
    private Integer id;
    private String name;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @JsonProperty("roleList")
    private List<RoleVO> roleVOList;
    public PermissionVO() {

    }
    public PermissionVO(Integer id, String name, Integer status, LocalDateTime createTime, LocalDateTime updateTime, List<RoleVO> roleVOList) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.roleVOList = roleVOList;
    }
    public Integer getId() {
        return id;
    }
    public PermissionVO setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }
    public PermissionVO setName(String name) {
        this.name = name;
        return this;
    }
    public Integer getStatus() {
        return status;
    }
    public PermissionVO setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public PermissionVO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
    public PermissionVO setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }
    public List<RoleVO> getRoleVOList() {
        return roleVOList;
    }
    public PermissionVO setRoleVOList(List<RoleVO> roleVOList) {
        this.roleVOList = roleVOList;
        return this;
    }
}