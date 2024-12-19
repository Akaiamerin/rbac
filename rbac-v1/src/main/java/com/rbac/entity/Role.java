package com.rbac.entity;
public class Role {
    private Integer id;
    private String name;
    public Role() {

    }
    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public Role setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }
    public Role setName(String name) {
        this.name = name;
        return this;
    }
}