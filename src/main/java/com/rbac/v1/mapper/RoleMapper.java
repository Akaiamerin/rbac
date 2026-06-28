package com.rbac.v1.mapper;
import com.rbac.v1.entity.Role;
import com.rbac.v1.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface RoleMapper {
    @Insert("INSERT INTO rbac_role (id, name) VALUES (NULL, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertRole(Role role);
    @Delete("DELETE FROM rbac_role WHERE id = #{id}")
    int deleteRoleById(Integer id);
    @Update("UPDATE rbac_role SET name = #{name} WHERE id = #{id}")
    int updateRoleById(Role role);
    @Select("SELECT * FROM rbac_role WHERE id = #{id}")
    Role selectRoleById(Integer id);
    @Select("SELECT * FROM rbac_role LIMIT #{offset}, #{limit}")
    List<Role> selectRoleByPagination(Pagination pagination);
    @Select("SELECT COUNT(*) FROM rbac_role")
    int selectRoleCount();
}