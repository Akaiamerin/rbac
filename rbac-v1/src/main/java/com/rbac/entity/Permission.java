package com.rbac.entity;
public class Permission {
    private Integer id;
    private String name;
    public Permission() {

    }
    public Permission(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}