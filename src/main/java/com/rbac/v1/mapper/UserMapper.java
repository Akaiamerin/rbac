package com.rbac.v1.mapper;
import com.rbac.v1.entity.User;
import com.rbac.v1.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO rbac_user (id, username, password) VALUES (NULL, #{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);
    @Delete("DELETE FROM rbac_user WHERE id = #{id}")
    int deleteUserById(Integer id);
    @Update("UPDATE rbac_user SET username = #{username}, password = #{password} WHERE id = #{id}")
    int updateUserById(User user);
    @Select("SELECT * FROM rbac_user WHERE id = #{id}")
    User selectUserById(Integer id);
    @Select("SELECT * FROM rbac_user LIMIT #{offset}, #{limit}")
    List<User> selectUserByPagination(Pagination pagination);
    @Select("SELECT COUNT(*) FROM rbac_user")
    int selectUserCount();
}