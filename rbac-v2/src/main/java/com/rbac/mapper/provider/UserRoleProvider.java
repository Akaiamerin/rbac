package com.rbac.mapper.provider;
import com.rbac.entity.UserRole;
import java.util.List;
import java.util.StringJoiner;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
public class UserRoleProvider implements ProviderMethodResolver {
    public String updateUserRoleList(@Param("userRoleList") List<UserRole> userRoleList) {
        StringJoiner allInsertUserRoleColStr = new StringJoiner(", ");
        for (int i = 0; i < userRoleList.size(); i++) {
            StringJoiner insertUserRoleColStr = new StringJoiner(", ", "(", ")")
                    .add(new StringBuilder().append("#{userRoleList[").append(i).append("].id}"))
                    .add(new StringBuilder().append("#{userRoleList[").append(i).append("].userId}"))
                    .add(new StringBuilder().append("#{userRoleList[").append(i).append("].roleId}"))
                    .add(new StringBuilder().append("#{userRoleList[").append(i).append("].status}"))
                    .add(new StringBuilder().append("#{userRoleList[").append(i).append("].createTime}"))
                    .add(new StringBuilder().append("#{userRoleList[").append(i).append("].updateTime}"));
            allInsertUserRoleColStr.add(String.valueOf(insertUserRoleColStr));
        }
        StringJoiner updateUserRoleColStr = new StringJoiner(", ")
                .add("user_id = ur.user_id")
                .add("role_id = ur.role_id")
                .add("status = ur.status")
                .add("create_time = ur.create_time")
                .add("update_time = ur.update_time");
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO user_role ")
                .append("(id, user_id, role_id, status, create_time, update_time) ")
                .append("VALUES ")
                .append(allInsertUserRoleColStr)
                .append(" AS ur ")
                .append("ON DUPLICATE KEY UPDATE ")
                .append(updateUserRoleColStr);
        return String.valueOf(sql);
    }
    public String selectUserRoleByUserId(Integer userId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM user_role WHERE user_id = #{userId}");
        return String.valueOf(sql);
    }
    public String selectUserRoleByRoleId(Integer roleId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM user_role WHERE role_id = #{roleId}");
        return String.valueOf(sql);
    }
}