package com.rbac.v2.mapper.provider;
import com.rbac.v2.entity.RolePermission;
import java.util.List;
import java.util.StringJoiner;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
public class RolePermissionProvider implements ProviderMethodResolver {
    public String updateRolePermissionList(@Param("rolePermissionList") List<RolePermission> rolePermissionList) {
        StringJoiner insertRolePermissionListString = new StringJoiner(", ");
        for (int i = 0; i < rolePermissionList.size(); i++) {
            StringJoiner insertRolePermissionString = new StringJoiner(", ", "(", ")")
                    .add(new StringBuilder().append("#{rolePermissionList[").append(i).append("].id}"))
                    .add(new StringBuilder().append("#{rolePermissionList[").append(i).append("].roleId}"))
                    .add(new StringBuilder().append("#{rolePermissionList[").append(i).append("].permissionId}"))
                    .add(new StringBuilder().append("#{rolePermissionList[").append(i).append("].status}"))
                    .add(new StringBuilder().append("#{rolePermissionList[").append(i).append("].createTime}"))
                    .add(new StringBuilder().append("#{rolePermissionList[").append(i).append("].updateTime}"));
            insertRolePermissionListString.add(String.valueOf(insertRolePermissionString));
        }
        StringJoiner updateUserRoleString = new StringJoiner(", ")
                .add("role_id = rp.role_id")
                .add("permission_id = rp.permission_id")
                .add("status = rp.status")
                .add("create_time = rp.create_time")
                .add("update_time = rp.update_time");
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO rbac_role_permission ")
                .append("(id, role_id, permission_id, status, create_time, update_time) ")
                .append("VALUES ")
                .append(insertRolePermissionListString)
                .append(" AS rp ")
                .append("ON DUPLICATE KEY UPDATE ")
                .append(updateUserRoleString);
        return String.valueOf(sql);
    }
    public String selectRolePermissionByRoleId(Integer roleId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM rbac_role_permission WHERE role_id = #{roleId}");
        return String.valueOf(sql);
    }
    public String selectRolePermissionByPermissionId(Integer permissionId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM rbac_role_permission WHERE permission_id = #{permissionId}");
        return String.valueOf(sql);
    }
}