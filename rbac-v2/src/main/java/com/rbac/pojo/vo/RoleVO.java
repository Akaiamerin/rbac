package com.rbac.pojo.vo;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
public class RoleVO {
    private Integer id;
    private String name;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @JsonProperty("permissionList")
    private List<PermissionVO> permissionVOList;
    @JsonProperty("userList")
    private List<UserVO> userVOList;
    public RoleVO() {

    }
    public RoleVO(Integer id, String name, Integer status, LocalDateTime createTime, LocalDateTime updateTime, List<PermissionVO> permissionVOList, List<UserVO> userVOList) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.permissionVOList = permissionVOList;
        this.userVOList = userVOList;
    }
    public Integer getId() {
        return id;
    }
    public RoleVO setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }
    public RoleVO setName(String name) {
        this.name = name;
        return this;
    }
    public Integer getStatus() {
        return status;
    }
    public RoleVO setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public RoleVO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
    public RoleVO setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }
    public List<PermissionVO> getPermissionVOList() {
        return permissionVOList;
    }
    public RoleVO setPermissionVOList(List<PermissionVO> permissionVOList) {
        this.permissionVOList = permissionVOList;
        return this;
    }
    public List<UserVO> getUserVOList() {
        return userVOList;
    }
    public RoleVO setUserVOList(List<UserVO> userVOList) {
        this.userVOList = userVOList;
        return this;
    }
}