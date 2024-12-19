package com.rbac.pojo.vo;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
public class UserVO {
    private Integer id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @JsonProperty("roleList")
    private List<RoleVO> roleVOList;
    public UserVO() {

    }
    public UserVO(Integer id, String username, String password, Integer status, LocalDateTime createTime, LocalDateTime updateTime, List<RoleVO> roleVOList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.roleVOList = roleVOList;
    }
    public Integer getId() {
        return id;
    }
    public UserVO setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getUsername() {
        return username;
    }
    public UserVO setUsername(String username) {
        this.username = username;
        return this;
    }
    public String getPassword() {
        return password;
    }
    public UserVO setPassword(String password) {
        this.password = password;
        return this;
    }
    public Integer getStatus() {
        return status;
    }
    public UserVO setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public UserVO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
    public UserVO setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }
    public List<RoleVO> getRoleVOList() {
        return roleVOList;
    }
    public UserVO setRoleVOList(List<RoleVO> roleVOList) {
        this.roleVOList = roleVOList;
        return this;
    }
}