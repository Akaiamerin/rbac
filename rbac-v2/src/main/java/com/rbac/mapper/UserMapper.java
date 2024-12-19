package com.rbac.mapper;
import com.rbac.entity.User;
import com.rbac.mapper.provider.UserProvider;
import com.rbac.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
@Mapper
public interface UserMapper {
    @InsertProvider(UserProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);
    @InsertProvider(UserProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUserList(@Param("userList") List<User> userList);
    @UpdateProvider(UserProvider.class)
    int updateUserById(User user);
    @UpdateProvider(UserProvider.class)
    int updateUserByIdList(
            @Param("idList") List<Integer> idList,
            @Param("user") User user
    );
    @UpdateProvider(UserProvider.class)
    int updateUserList(@Param("userList") List<User> userList);
    @SelectProvider(UserProvider.class)
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    User selectUserById(Integer id);
    @SelectProvider(UserProvider.class)
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<User> selectUserByIdList(@Param("idList") List<Integer> idList);
    @SelectProvider(UserProvider.class)
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<User> selectUserByPagination(Pagination pagination);
    @SelectProvider(UserProvider.class)
    int selectUserCount();
}