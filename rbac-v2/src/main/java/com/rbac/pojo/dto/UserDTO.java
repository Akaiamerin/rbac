package com.rbac.pojo.dto;
import java.time.LocalDateTime;
import java.util.List;
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<RoleDTO> roleDTOList;
    public UserDTO() {

    }
    public UserDTO(Integer id, String username, String password, Integer status, LocalDateTime createTime, LocalDateTime updateTime, List<RoleDTO> roleDTOList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.roleDTOList = roleDTOList;
    }
    public Integer getId() {
        return id;
    }
    public UserDTO setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getUsername() {
        return username;
    }
    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }
    public String getPassword() {
        return password;
    }
    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }
    public Integer getStatus() {
        return status;
    }
    public UserDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public UserDTO setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
    public UserDTO setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }
    public List<RoleDTO> getRoleDTOList() {
        return roleDTOList;
    }
    public UserDTO setRoleDTOList(List<RoleDTO> roleDTOList) {
        this.roleDTOList = roleDTOList;
        return this;
    }
}