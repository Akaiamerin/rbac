package com.rbac.v2.mapper.provider;
import com.rbac.v2.entity.Role;
import com.rbac.v2.utils.Pagination;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
public class RoleProvider implements ProviderMethodResolver {
    public String insertRole(Role role) {
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO rbac_role ")
                .append("(id, name, status, create_time, update_time) ")
                .append("VALUES ")
                .append("(null, #{name}, #{status}, #{createTime}, #{updateTime})");
        return String.valueOf(sql);
    }
    public String insertRoleList(@Param("roleList") List<Role> roleList) {
        StringJoiner insertRoleListString = new StringJoiner(", ");
        for (int i = 0; i < roleList.size(); i++) {
            StringJoiner insertRoleString = new StringJoiner(", ", "(", ")")
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].id}"))
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].name}"))
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].status}"))
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].createTime}"))
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].updateTime}"));
            insertRoleListString.add(String.valueOf(insertRoleString));
        }
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO rbac_role ")
                .append("(id, name, status, create_time, update_time) ")
                .append("VALUES ")
                .append(insertRoleListString);
        return String.valueOf(sql);
    }
    public String updateRoleById(Role role) {
        StringJoiner updateRoleString = new StringJoiner(", ");
        if (role.getName() != null) {
            updateRoleString.add("name = #{name}");
        }
        if (role.getStatus() != null) {
            updateRoleString.add("status = #{status}");
        }
        if (role.getCreateTime() != null) {
            updateRoleString.add("create_time = #{createTime}");
        }
        if (role.getUpdateTime() != null) {
            updateRoleString.add("update_time = #{updateTime}");
        }
        StringBuilder sql = new StringBuilder()
                .append("UPDATE rbac_role SET ")
                .append(updateRoleString)
                .append(" WHERE id = #{id}");
        return String.valueOf(sql);
    }
    public String updateRoleByIdList(
            @Param("idList") List<Integer> idList,
            @Param("role") Role role
    ) {
        StringJoiner updateRoleString = new StringJoiner(", ");
        if (role.getName() != null) {
            updateRoleString.add("name = #{role.name}");
        }
        if (role.getStatus() != null) {
            updateRoleString.add("status = #{role.status}");
        }
        if (role.getCreateTime() != null) {
            updateRoleString.add("create_time = #{role.createTime}");
        }
        if (role.getUpdateTime() != null) {
            updateRoleString.add("update_time = #{role.updateTime}");
        }
        String idListString = idList
                .stream()
                .map((Integer id)->{
                    return String.valueOf(id);
                })
                .collect(Collectors.joining(", ", "(", ")"));
        StringBuilder sql = new StringBuilder()
                .append("UPDATE rbac_role SET ")
                .append(updateRoleString)
                .append(" WHERE id IN ")
                .append(idListString);
        return String.valueOf(sql);
    }
    public String updateRoleList(@Param("roleList") List<Role> roleList) {
        StringJoiner allInsertRoleListString = new StringJoiner(", ");
        for (int i = 0; i < roleList.size(); i++) {
            StringJoiner insertRoleString = new StringJoiner(", ", "(", ")")
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].id}"))
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].name}"))
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].status}"))
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].createTime}"))
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].updateTime}"));
            allInsertRoleListString.add(String.valueOf(insertRoleString));
        }
        StringJoiner updateRoleString = new StringJoiner(", ")
                .add("name = r.name")
                .add("status = r.status")
                .add("create_time = r.create_time")
                .add("update_time = r.update_time");
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO rbac_role ")
                .append("(id, name, status, create_time, update_time) ")
                .append("VALUES ")
                .append(allInsertRoleListString)
                .append(" AS r ")
                .append("ON DUPLICATE KEY UPDATE ")
                .append(updateRoleString);
        return String.valueOf(sql);
    }
    public String selectRoleById(Integer id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM rbac_role WHERE id = #{id}");
        return String.valueOf(sql);
    }
    public String selectRoleByIdList(@Param("idList") List<Integer> idList) {
        String idListString = idList
                .stream()
                .map((Integer id)->{
                    return String.valueOf(id);
                })
                .collect(Collectors.joining(", ", "(", ")"));
        StringBuilder sql = new StringBuilder()
                .append("SELECT * FROM rbac_role WHERE id IN ")
                .append(idListString);
        return String.valueOf(sql);
    }
    public String selectRoleByPagination(Pagination pagination) {
        StringBuilder sql = new StringBuilder("SELECT * FROM rbac_role LIMIT #{offset}, #{limit}");
        return String.valueOf(sql);
    }
    public String selectRoleCount() {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM rbac_role");
        return String.valueOf(sql);
    }
}