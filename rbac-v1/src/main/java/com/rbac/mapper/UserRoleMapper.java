package com.rbac.mapper;
import com.rbac.entity.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserRoleMapper {
    @Insert("INSERT INTO user_role (id, user_id, role_id) VALUES (NULL, #{userId}, #{roleId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUserRole(UserRole userRole);
    @Delete("DELETE FROM user_role WHERE user_id = #{userId}")
    int deleteUserRoleByUserId(Integer userId);
    @Delete("DELETE FROM user_role WHERE role_id = #{roleId}")
    int deleteUserRoleByRoleId(Integer roleId);
    @Select("SELECT * FROM user_role WHERE user_id = #{userId}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "role_id", property = "roleId")
    })
    List<UserRole> selectUserRoleByUserId(Integer userId);
    @Select("SELECT * FROM user_role WHERE role_id = #{roleId}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "role_id", property = "roleId")
    })
    List<UserRole> selectUserRoleByRoleId(Integer roleId);
}