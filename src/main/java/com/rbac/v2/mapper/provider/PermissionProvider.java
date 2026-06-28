package com.rbac.v2.mapper.provider;
import com.rbac.v2.entity.Permission;
import com.rbac.v2.utils.Pagination;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
public class PermissionProvider implements ProviderMethodResolver {
    public String insertPermission(Permission permission) {
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO rbac_permission ")
                .append("(id, name, status, create_time, update_time) ")
                .append("VALUES ")
                .append("(null, #{name}, #{status}, #{createTime}, #{updateTime})");
        return String.valueOf(sql);
    }
    public String insertPermissionList(@Param("permissionList") List<Permission> permissionList) {
        StringJoiner insertPermissionListString = new StringJoiner(", ");
        for (int i = 0; i < permissionList.size(); i++) {
            StringJoiner insertPermissionString = new StringJoiner(", ", "(", ")")
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].id}"))
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].name}"))
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].status}"))
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].createTime}"))
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].updateTime}"));
            insertPermissionListString.add(String.valueOf(insertPermissionString));
        }
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO rbac_permission ")
                .append("(id, name, status, create_time, update_time) ")
                .append("VALUES ")
                .append(insertPermissionListString);
        return String.valueOf(sql);
    }
    public String updatePermissionById(Permission permission) {
        StringJoiner updatePermissionString = new StringJoiner(", ");
        if (permission.getName() != null) {
            updatePermissionString.add("name = #{name}");
        }
        if (permission.getStatus() != null) {
            updatePermissionString.add("status = #{status}");
        }
        if (permission.getCreateTime() != null) {
            updatePermissionString.add("create_time = #{createTime}");
        }
        if (permission.getUpdateTime() != null) {
            updatePermissionString.add("update_time = #{updateTime}");
        }
        StringBuilder sql = new StringBuilder()
                .append("UPDATE rbac_permission SET ")
                .append(updatePermissionString)
                .append(" WHERE id = #{id}");
        return String.valueOf(sql);
    }
    public String updatePermissionByIdList(
            @Param("idList") List<Integer> idList,
            @Param("permission") Permission permission
    ) {
        StringJoiner updatePermissionString = new StringJoiner(", ");
        if (permission.getName() != null) {
            updatePermissionString.add("name = #{permission.name}");
        }
        if (permission.getStatus() != null) {
            updatePermissionString.add("status = #{permission.status}");
        }
        if (permission.getCreateTime() != null) {
            updatePermissionString.add("create_time = #{permission.createTime}");
        }
        if (permission.getUpdateTime() != null) {
            updatePermissionString.add("update_time = #{permission.updateTime}");
        }
        String idListString = idList
                .stream()
                .map((Integer id)->{
                    return String.valueOf(id);
                })
                .collect(Collectors.joining(", ", "(", ")"));
        StringBuilder sql = new StringBuilder()
                .append("UPDATE rbac_permission SET ")
                .append(updatePermissionString)
                .append(" WHERE id IN ")
                .append(idListString);
        return String.valueOf(sql);
    }
    public String updatePermissionList(@Param("permissionList") List<Permission> permissionList) {
        StringJoiner insertPermissionListString = new StringJoiner(", ");
        for (int i = 0; i < permissionList.size(); i++) {
            StringJoiner insertPermissionString = new StringJoiner(", ", "(", ")")
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].id}"))
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].name}"))
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].status}"))
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].createTime}"))
                    .add(new StringBuilder().append("#{permissionList[").append(i).append("].updateTime}"));
            insertPermissionListString.add(String.valueOf(insertPermissionString));
        }
        StringJoiner updatePermissionString = new StringJoiner(", ")
                .add("name = p.name")
                .add("status = p.status")
                .add("create_time = p.create_time")
                .add("update_time = p.update_time");
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO rbac_permission ")
                .append("(id, name, status, create_time, update_time) ")
                .append("VALUES ")
                .append(insertPermissionListString)
                .append(" AS p ")
                .append("ON DUPLICATE KEY UPDATE ")
                .append(updatePermissionString);
        return String.valueOf(sql);
    }
    public String selectPermissionById(Integer id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM rbac_permission WHERE id = #{id}");
        return String.valueOf(sql);
    }
    public String selectPermissionByIdList(@Param("idList") List<Integer> idList) {
        String idListString = idList
                .stream()
                .map((Integer id)->{
                    return String.valueOf(id);
                })
                .collect(Collectors.joining(", ", "(", ")"));
        StringBuilder sql = new StringBuilder()
                .append("SELECT * FROM rbac_permission WHERE id IN ")
                .append(idListString);
        return String.valueOf(sql);
    }
    public String selectPermissionByPagination(Pagination pagination) {
        StringBuilder sql = new StringBuilder("SELECT * FROM rbac_permission LIMIT #{offset}, #{limit}");
        return String.valueOf(sql);
    }
    public String selectPermissionCount() {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM rbac_permission");
        return String.valueOf(sql);
    }
}