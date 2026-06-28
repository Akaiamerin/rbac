package com.rbac.v2.mapper.provider;
import com.rbac.v2.entity.User;
import com.rbac.v2.utils.Pagination;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
public class UserProvider implements ProviderMethodResolver {
    public String insertUser(User user) {
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO rbac_user ")
                .append("(id, username, password, status, create_time, update_time) ")
                .append("VALUES ")
                .append("(null, #{username}, #{password}, #{status}, #{createTime}, #{updateTime})");
        return String.valueOf(sql);
    }
    public String insertUserList(@Param("userList") List<User> userList) {
        StringJoiner insertUserListString = new StringJoiner(", ");
        for (int i = 0; i < userList.size(); i++) {
            StringJoiner insertUserString = new StringJoiner(", ", "(", ")")
                    .add(new StringBuilder().append("#{userList[").append(i).append("].id}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].username}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].password}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].status}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].createTime}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].updateTime}"));
            insertUserListString.add(String.valueOf(insertUserString));
        }
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO rbac_user ")
                .append("(id, username, password, status, create_time, update_time) ")
                .append("VALUES ")
                .append(insertUserListString);
        return String.valueOf(sql);
    }
    public String updateUserById(User user) {
        StringJoiner updateUserString = new StringJoiner(", ");
        if (user.getUsername() != null) {
            updateUserString.add("username = #{username}");
        }
        if (user.getPassword() != null) {
            updateUserString.add("password = #{password}");
        }
        if (user.getStatus() != null) {
            updateUserString.add("status = #{status}");
        }
        if (user.getCreateTime() != null) {
            updateUserString.add("create_time = #{createTime}");
        }
        if (user.getUpdateTime() != null) {
            updateUserString.add("update_time = #{updateTime}");
        }
        StringBuilder sql = new StringBuilder()
                .append("UPDATE rbac_user SET ")
                .append(updateUserString)
                .append(" WHERE id = #{id}");
        return String.valueOf(sql);
    }
    public String updateUserByIdList(
            @Param("idList") List<Integer> idList,
            @Param("user") User user
    ) {
        StringJoiner updateUserString = new StringJoiner(", ");
        if (user.getUsername() != null) {
            updateUserString.add("username = #{user.username}");
        }
        if (user.getPassword() != null) {
            updateUserString.add("password = #{user.password}");
        }
        if (user.getStatus() != null) {
            updateUserString.add("status = #{user.status}");
        }
        if (user.getCreateTime() != null) {
            updateUserString.add("create_time = #{user.createTime}");
        }
        if (user.getUpdateTime() != null) {
            updateUserString.add("update_time = #{user.updateTime}");
        }
        String idListString = idList
                .stream()
                .map((Integer id)->{
                    return String.valueOf(id);
                })
                .collect(Collectors.joining(", ", "(", ")"));
        StringBuilder sql = new StringBuilder()
                .append("UPDATE rbac_user SET ")
                .append(updateUserString)
                .append(" WHERE id IN ")
                .append(idListString);
        return String.valueOf(sql);
    }
    public String updateUserList(@Param("userList") List<User> userList) {
        StringJoiner insertUserListString = new StringJoiner(", ");
        for (int i = 0; i < userList.size(); i++) {
            StringJoiner insertUserString = new StringJoiner(", ", "(", ")")
                    .add(new StringBuilder().append("#{userList[").append(i).append("].id}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].username}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].password}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].status}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].createTime}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].updateTime}"));
            insertUserListString.add(String.valueOf(insertUserString));
        }
        StringJoiner updateUserString = new StringJoiner(", ")
                .add("username = u.username")
                .add("password = u.password")
                .add("status = u.status")
                .add("create_time = u.create_time")
                .add("update_time = u.update_time");
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO rbac_user ")
                .append("(id, username, password, status, create_time, update_time) ")
                .append("VALUES ")
                .append(insertUserListString)
                .append(" AS u ")
                .append("ON DUPLICATE KEY UPDATE ")
                .append(updateUserString);
        return String.valueOf(sql);
    }
    public String selectUserById(Integer id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM rbac_user WHERE id = #{id}");
        return String.valueOf(sql);
    }
    public String selectUserByIdList(@Param("idList") List<Integer> idList) {
        String idListString = idList
                .stream()
                .map((Integer id)->{
                    return String.valueOf(id);
                })
                .collect(Collectors.joining(", ", "(", ")"));
        StringBuilder sql = new StringBuilder()
                .append("SELECT * FROM rbac_user WHERE id IN ")
                .append(idListString);
        return String.valueOf(sql);
    }
    public String selectUserByPagination(Pagination pagination) {
        StringBuilder sql = new StringBuilder("SELECT * FROM rbac_user LIMIT #{offset}, #{limit}");
        return String.valueOf(sql);
    }
    public String selectUserCount() {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM rbac_user");
        return String.valueOf(sql);
    }
}