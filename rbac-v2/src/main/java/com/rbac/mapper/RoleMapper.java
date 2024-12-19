package com.rbac.mapper;
import com.rbac.entity.Role;
import com.rbac.mapper.provider.RoleProvider;
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
public interface RoleMapper {
    @InsertProvider(RoleProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertRole(Role role);
    @InsertProvider(RoleProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertRoleList(@Param("roleList") List<Role> roleList);
    @UpdateProvider(RoleProvider.class)
    int updateRoleById(Role role);
    @UpdateProvider(RoleProvider.class)
    int updateRoleByIdList(
            @Param("idList") List<Integer> idList,
            @Param("role") Role role
    );
    @UpdateProvider(RoleProvider.class)
    int updateRoleList(@Param("roleList") List<Role> roleList);
    @SelectProvider(RoleProvider.class)
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    Role selectRoleById(Integer id);
    @SelectProvider(RoleProvider.class)
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<Role> selectRoleByIdList(@Param("idList") List<Integer> idList);
    @SelectProvider(RoleProvider.class)
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<Role> selectRoleByPagination(Pagination pagination);
    @SelectProvider(RoleProvider.class)
    int selectRoleCount();
}