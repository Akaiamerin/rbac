package com.rbac.mapper;
import com.rbac.entity.Permission;
import com.rbac.mapper.provider.PermissionProvider;
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
public interface PermissionMapper {
    @InsertProvider(PermissionProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertPermission(Permission permission);
    @InsertProvider(PermissionProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertPermissionList(@Param("permissionList") List<Permission> permissionList);
    @UpdateProvider(PermissionProvider.class)
    int updatePermissionById(Permission permission);
    @UpdateProvider(PermissionProvider.class)
    int updatePermissionByIdList(
            @Param("idList") List<Integer> idList,
            @Param("permission") Permission permission
    );
    @UpdateProvider(PermissionProvider.class)
    int updatePermissionList(@Param("permissionList") List<Permission> permissionList);
    @SelectProvider(PermissionProvider.class)
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    Permission selectPermissionById(Integer id);
    @SelectProvider(PermissionProvider.class)
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<Permission> selectPermissionByIdList(@Param("idList") List<Integer> idList);
    @SelectProvider(PermissionProvider.class)
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<Permission> selectPermissionByPagination(Pagination pagination);
    @SelectProvider(PermissionProvider.class)
    int selectPermissionCount();
}