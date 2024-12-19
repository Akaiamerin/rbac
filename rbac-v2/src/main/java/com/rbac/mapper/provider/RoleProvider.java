package com.rbac.mapper.provider;
import com.rbac.entity.Role;
import com.rbac.utils.Pagination;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
public class RoleProvider implements ProviderMethodResolver {
    public String insertRole(Role role) {
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO role ")
                .append("(id, name, status, create_time, update_time) ")
                .append("VALUES ")
                .append("(null, #{name}, #{status}, #{createTime}, #{updateTime})");
        return String.valueOf(sql);
    }
    public String insertRoleList(@Param("roleList") List<Role> roleList) {
        StringJoiner allInsertRoleColStr = new StringJoiner(", ");
        for (int i = 0; i < roleList.size(); i++) {
            StringJoiner insertRoleColStr = new StringJoiner(", ", "(", ")")
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].id}"))
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].name}"))
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].status}"))
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].createTime}"))
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].updateTime}"));
            allInsertRoleColStr.add(String.valueOf(insertRoleColStr));
        }
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO role ")
                .append("(id, name, status, create_time, update_time) ")
                .append("VALUES ")
                .append(allInsertRoleColStr);
        return String.valueOf(sql);
    }
    public String updateRoleById(Role role) {
        StringJoiner updateRoleColStr = new StringJoiner(", ");
        if (role.getName() != null) {
            updateRoleColStr.add("name = #{name}");
        }
        if (role.getStatus() != null) {
            updateRoleColStr.add("status = #{status}");
        }
        if (role.getCreateTime() != null) {
            updateRoleColStr.add("create_time = #{createTime}");
        }
        if (role.getUpdateTime() != null) {
            updateRoleColStr.add("update_time = #{updateTime}");
        }
        StringBuilder sql = new StringBuilder()
                .append("UPDATE role SET ")
                .append(updateRoleColStr)
                .append(" WHERE id = #{id}");
        return String.valueOf(sql);
    }
    public String updateRoleByIdList(
            @Param("idList") List<Integer> idList,
            @Param("role") Role role
    ) {
        StringJoiner updateRoleColStr = new StringJoiner(", ");
        if (role.getName() != null) {
            updateRoleColStr.add("name = #{role.name}");
        }
        if (role.getStatus() != null) {
            updateRoleColStr.add("status = #{role.status}");
        }
        if (role.getCreateTime() != null) {
            updateRoleColStr.add("create_time = #{role.createTime}");
        }
        if (role.getUpdateTime() != null) {
            updateRoleColStr.add("update_time = #{role.updateTime}");
        }
        String idListStr = idList
                .stream()
                .map((Integer id)->{
                    return String.valueOf(id);
                })
                .collect(Collectors.joining(", ", "(", ")"));
        StringBuilder sql = new StringBuilder()
                .append("UPDATE role SET ")
                .append(updateRoleColStr)
                .append(" WHERE id IN ")
                .append(idListStr);
        return String.valueOf(sql);
    }
    public String updateRoleList(@Param("roleList") List<Role> roleList) {
        StringJoiner allInsertRoleColStr = new StringJoiner(", ");
        for (int i = 0; i < roleList.size(); i++) {
            StringJoiner insertRoleColStr = new StringJoiner(", ", "(", ")")
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].id}"))
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].name}"))
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].status}"))
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].createTime}"))
                    .add(new StringBuilder().append("#{roleList[").append(i).append("].updateTime}"));
            allInsertRoleColStr.add(String.valueOf(insertRoleColStr));
        }
        StringJoiner updateRoleColStr = new StringJoiner(", ")
                .add("name = r.name")
                .add("status = r.status")
                .add("create_time = r.create_time")
                .add("update_time = r.update_time");
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO role ")
                .append("(id, name, status, create_time, update_time) ")
                .append("VALUES ")
                .append(allInsertRoleColStr)
                .append(" AS r ")
                .append("ON DUPLICATE KEY UPDATE ")
                .append(updateRoleColStr);
        return String.valueOf(sql);
    }
    public String selectRoleById(Integer id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM role WHERE id = #{id}");
        return String.valueOf(sql);
    }
    public String selectRoleByIdList(@Param("idList") List<Integer> idList) {
        String idListStr = idList
                .stream()
                .map((Integer id)->{
                    return String.valueOf(id);
                })
                .collect(Collectors.joining(", ", "(", ")"));
        StringBuilder sql = new StringBuilder()
                .append("SELECT * FROM role WHERE id IN ")
                .append(idListStr);
        return String.valueOf(sql);
    }
    public String selectRoleByPagination(Pagination pagination) {
        StringBuilder sql = new StringBuilder("SELECT * FROM role LIMIT #{offset}, #{limit}");
        return String.valueOf(sql);
    }
    public String selectRoleCount() {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM role");
        return String.valueOf(sql);
    }
}