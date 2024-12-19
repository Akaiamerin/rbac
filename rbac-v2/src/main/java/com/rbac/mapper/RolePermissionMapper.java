package com.rbac.mapper;
import com.rbac.entity.RolePermission;
import com.rbac.mapper.provider.RolePermissionProvider;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
@Mapper
public interface RolePermissionMapper {
    @UpdateProvider(RolePermissionProvider.class)
    int updateRolePermissionList(@Param("rolePermissionList") List<RolePermission> rolePermissionList);
    @SelectProvider(RolePermissionProvider.class)
    @Results({
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "permission_id", property = "permissionId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<RolePermission> selectRolePermissionByRoleId(Integer roleId);
    @SelectProvider(RolePermissionProvider.class)
    @Results({
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "permission_id", property = "permissionId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<RolePermission> selectRolePermissionByPermissionId(Integer permissionId);
}