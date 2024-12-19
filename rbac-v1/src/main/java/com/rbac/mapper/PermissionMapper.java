package com.rbac.mapper;
import com.rbac.entity.Permission;
import com.rbac.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface PermissionMapper {
    @Insert("INSERT INTO permission (id, name) VALUES (NULL, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertPermission(Permission permission);
    @Delete("DELETE FROM permission WHERE id = #{id}")
    int deletePermissionById(Integer id);
    @Update("UPDATE permission SET name = #{name} WHERE id = #{id}")
    int updatePermissionById(Permission permission);
    @Select("SELECT * FROM permission WHERE id = #{id}")
    Permission selectPermissionById(Integer id);
    @Select("SELECT * FROM permission LIMIT #{offset}, #{limit}")
    List<Permission> selectPermissionByPagination(Pagination pagination);
    @Select("SELECT COUNT(*) FROM permission")
    int selectPermissionCount();
}