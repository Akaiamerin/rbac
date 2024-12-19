package com.rbac.mapper.provider;
import com.rbac.entity.Permission;
import com.rbac.utils.Pagination;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
public class PermissionProvider implements ProviderMethodResolver {
    public String insertPermission(Permission permission) {
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO permission ")
                .append("(id, name, status, create_time, update_time) ")
                .append("VALUES ")
                .append("(null, #{name}, #{status}, #{createTime}, #{updateTime})");
        return String.valueOf(sql);
    }
    public String insertPermissionList(@Param("permissionList") List<Permission> permissionList) {
        StringJoiner allInsertPermissionListColStr = new StringJoiner(", ");
        for (int i = 0; i < permissionList.size(); i++) {
            StringJoiner insertPermissionColStr = new StringJoiner(", ", "(", ")")
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].id}"))
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].name}"))
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].status}"))
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].createTime}"))
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].updateTime}"));
            allInsertPermissionListColStr.add(String.valueOf(insertPermissionColStr));
        }
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO permission ")
                .append("(id, name, status, create_time, update_time) ")
                .append("VALUES ")
                .append(allInsertPermissionListColStr);
        return String.valueOf(sql);
    }
    public String updatePermissionById(Permission permission) {
        StringJoiner updatePermissionColStr = new StringJoiner(", ");
        if (permission.getName() != null) {
            updatePermissionColStr.add("name = #{name}");
        }
        if (permission.getStatus() != null) {
            updatePermissionColStr.add("status = #{status}");
        }
        if (permission.getCreateTime() != null) {
            updatePermissionColStr.add("create_time = #{createTime}");
        }
        if (permission.getUpdateTime() != null) {
            updatePermissionColStr.add("update_time = #{updateTime}");
        }
        StringBuilder sql = new StringBuilder()
                .append("UPDATE permission SET ")
                .append(updatePermissionColStr)
                .append(" WHERE id = #{id}");
        return String.valueOf(sql);
    }
    public String updatePermissionByIdList(
            @Param("idList") List<Integer> idList,
            @Param("permission") Permission permission
    ) {
        StringJoiner updatePermissionColStr = new StringJoiner(", ");
        if (permission.getName() != null) {
            updatePermissionColStr.add("name = #{permission.name}");
        }
        if (permission.getStatus() != null) {
            updatePermissionColStr.add("status = #{permission.status}");
        }
        if (permission.getCreateTime() != null) {
            updatePermissionColStr.add("create_time = #{permission.createTime}");
        }
        if (permission.getUpdateTime() != null) {
            updatePermissionColStr.add("update_time = #{permission.updateTime}");
        }
        String idListStr = idList
                .stream()
                .map((Integer id)->{
                    return String.valueOf(id);
                })
                .collect(Collectors.joining(", ", "(", ")"));
        StringBuilder sql = new StringBuilder()
                .append("UPDATE permission SET ")
                .append(updatePermissionColStr)
                .append(" WHERE id IN ")
                .append(idListStr);
        return String.valueOf(sql);
    }
    public String updatePermissionList(@Param("permissionList") List<Permission> permissionList) {
        StringJoiner allInsertPermissionListColStr = new StringJoiner(", ");
        for (int i = 0; i < permissionList.size(); i++) {
            StringJoiner insertPermissionColStr = new StringJoiner(", ", "(", ")")
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].id}"))
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].name}"))
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].status}"))
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].createTime}"))
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].updateTime}"));
            allInsertPermissionListColStr.add(String.valueOf(insertPermissionColStr));
        }
        StringJoiner updatePermissionColStr = new StringJoiner(", ")
                .add("name = p.name")
                .add("status = p.status")
                .add("create_time = p.create_time")
                .add("update_time = p.update_time");
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO permission ")
                .append("(id, name, status, create_time, update_time) ")
                .append("VALUES ")
                .append(allInsertPermissionListColStr)
                .append(" AS p ")
                .append("ON DUPLICATE KEY UPDATE ")
                .append(updatePermissionColStr);
        return String.valueOf(sql);
    }
    public String selectPermissionById(Integer id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM permission WHERE id = #{id}");
        return String.valueOf(sql);
    }
    public String selectPermissionByIdList(@Param("idList") List<Integer> idList) {
        String idListStr = idList
                .stream()
                .map((Integer id)->{
                    return String.valueOf(id);
                })
                .collect(Collectors.joining(", ", "(", ")"));
        StringBuilder sql = new StringBuilder()
                .append("SELECT * FROM permission WHERE id IN ")
                .append(idListStr);
        return String.valueOf(sql);
    }
    public String selectPermissionByPagination(Pagination pagination) {
        StringBuilder sql = new StringBuilder("SELECT * FROM permission LIMIT #{offset}, #{limit}");
        return String.valueOf(sql);
    }
    public String selectPermissionCount() {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM permission");
        return String.valueOf(sql);
    }
}