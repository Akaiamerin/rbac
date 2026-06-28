package com.rbac.v1.utils;
public class Pagination {
    private Integer page = 1;
    private Integer limit = 10;
    public Pagination() {

    }
    public Pagination(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }
    public Integer getPage() {
        return page;
    }
    public Pagination setPage(Integer page) {
        this.page = page;
        return this;
    }
    public Integer getLimit() {
        return limit;
    }
    public Pagination setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
    public Integer getOffset() {
        return (page - 1) * limit;
    }
}