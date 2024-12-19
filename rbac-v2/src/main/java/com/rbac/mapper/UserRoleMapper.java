package com.rbac.mapper;
import com.rbac.entity.UserRole;
import com.rbac.mapper.provider.UserRoleProvider;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
@Mapper
public interface UserRoleMapper {
    @UpdateProvider(UserRoleProvider.class)
    int updateUserRoleList(@Param("userRoleList") List<UserRole> userRoleList);
    @SelectProvider(UserRoleProvider.class)
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<UserRole> selectUserRoleByUserId(Integer userId);
    @SelectProvider(UserRoleProvider.class)
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<UserRole> selectUserRoleByRoleId(Integer roleId);
}