package com.rbac.mapper;
import com.rbac.entity.Role;
import com.rbac.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface RoleMapper {
    @Insert("INSERT INTO role (id, name) VALUES (NULL, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertRole(Role role);
    @Delete("DELETE FROM role WHERE id = #{id}")
    int deleteRoleById(Integer id);
    @Update("UPDATE role SET name = #{name} WHERE id = #{id}")
    int updateRoleById(Role role);
    @Select("SELECT * FROM role WHERE id = #{id}")
    Role selectRoleById(Integer id);
    @Select("SELECT * FROM role LIMIT #{offset}, #{limit}")
    List<Role> selectRoleByPagination(Pagination pagination);
    @Select("SELECT COUNT(*) FROM role")
    int selectRoleCount();
}