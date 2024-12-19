package com.rbac.utils;
import java.util.Objects;
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
    public Pagination setPage(String page) {
        String pageStr = Objects.requireNonNullElse(page, String.valueOf(this.page));
        return setPage(Integer.valueOf(pageStr));
    }
    public Pagination setLimit(String limit) {
        String limitStr = Objects.requireNonNullElse(limit, String.valueOf(this.limit));
        return setLimit(Integer.valueOf(limitStr));
    }
    public Integer getOffset() {
        return (page - 1) * limit;
    }
}