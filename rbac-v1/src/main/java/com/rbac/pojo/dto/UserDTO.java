package com.rbac.pojo.dto;
import java.util.List;
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private List<RoleDTO> roleDTOList;
    public UserDTO() {

    }
    public UserDTO(Integer id, String username, String password, List<RoleDTO> roleDTOList) {
        this.id = id;
        this.username = username;
        this.password = password;
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
    public List<RoleDTO> getRoleDTOList() {
        return roleDTOList;
    }
    public UserDTO setRoleDTOList(List<RoleDTO> roleDTOList) {
        this.roleDTOList = roleDTOList;
        return this;
    }
}