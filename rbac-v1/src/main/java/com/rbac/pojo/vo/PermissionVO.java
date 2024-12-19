package com.rbac.pojo.vo;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
public class PermissionVO {
    private Integer id;
    private String name;
    @JsonProperty("roleList")
    private List<RoleVO> roleVOList;
    public PermissionVO() {

    }
    public PermissionVO(Integer id, String name, List<RoleVO> roleVOList) {
        this.id = id;
        this.name = name;
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
    public List<RoleVO> getRoleVOList() {
        return roleVOList;
    }
    public PermissionVO setRoleVOList(List<RoleVO> roleVOList) {
        this.roleVOList = roleVOList;
        return this;
    }
}