package com.rbac.mapper;
import com.rbac.entity.RolePermission;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface RolePermissionMapper {
    @Insert("INSERT INTO role_permission (id, role_id, permission_id) VALUES (NULL, #{roleId}, #{permissionId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertRolePermission(RolePermission rolePermission);
    @Delete("DELETE FROM role_permission WHERE role_id = #{roleId}")
    int deleteRolePermissionByRoleId(Integer roleId);
    @Delete("DELETE FROM role_permission WHERE permission_id = #{permissionId}")
    int deleteRolePermissionByPermissionId(Integer permissionId);
    @Select("SELECT * FROM role_permission WHERE role_id = #{roleId}")
    @Results({
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "permission_id", property = "permissionId")
    })
    List<RolePermission> selectRolePermissionByURoleId(Integer roleId);
    @Select("SELECT * FROM role_permission WHERE permission_id = #{permissionId}")
    @Results({
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "permission_id", property = "permissionId")
    })
    List<RolePermission> selectRolePermissionByPermissionId(Integer permissionId);
}