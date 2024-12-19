package com.rbac.mapper.provider;
import com.rbac.entity.User;
import com.rbac.utils.Pagination;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
public class UserProvider implements ProviderMethodResolver {
    public String insertUser(User user) {
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO user ")
                .append("(id, username, password, status, create_time, update_time) ")
                .append("VALUES ")
                .append("(null, #{username}, #{password}, #{status}, #{createTime}, #{updateTime})");
        return String.valueOf(sql);
    }
    public String insertUserList(@Param("userList") List<User> userList) {
        StringJoiner allInsertUserColStr = new StringJoiner(", ");
        for (int i = 0; i < userList.size(); i++) {
            StringJoiner insertUserColStr = new StringJoiner(", ", "(", ")")
                    .add(new StringBuilder().append("#{userList[").append(i).append("].id}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].username}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].password}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].status}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].createTime}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].updateTime}"));
            allInsertUserColStr.add(String.valueOf(insertUserColStr));
        }
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO user ")
                .append("(id, username, password, status, create_time, update_time) ")
                .append("VALUES ")
                .append(allInsertUserColStr);
        return String.valueOf(sql);
    }
    public String updateUserById(User user) {
        StringJoiner updateUserColStr = new StringJoiner(", ");
        if (user.getUsername() != null) {
            updateUserColStr.add("username = #{username}");
        }
        if (user.getPassword() != null) {
            updateUserColStr.add("password = #{password}");
        }
        if (user.getStatus() != null) {
            updateUserColStr.add("status = #{status}");
        }
        if (user.getCreateTime() != null) {
            updateUserColStr.add("create_time = #{createTime}");
        }
        if (user.getUpdateTime() != null) {
            updateUserColStr.add("update_time = #{updateTime}");
        }
        StringBuilder sql = new StringBuilder()
                .append("UPDATE user SET ")
                .append(updateUserColStr)
                .append(" WHERE id = #{id}");
        return String.valueOf(sql);
    }
    public String updateUserByIdList(
            @Param("idList") List<Integer> idList,
            @Param("user") User user
    ) {
        StringJoiner updateUserColStr = new StringJoiner(", ");
        if (user.getUsername() != null) {
            updateUserColStr.add("username = #{user.username}");
        }
        if (user.getPassword() != null) {
            updateUserColStr.add("password = #{user.password}");
        }
        if (user.getStatus() != null) {
            updateUserColStr.add("status = #{user.status}");
        }
        if (user.getCreateTime() != null) {
            updateUserColStr.add("create_time = #{user.createTime}");
        }
        if (user.getUpdateTime() != null) {
            updateUserColStr.add("update_time = #{user.updateTime}");
        }
        String idListStr = idList
                .stream()
                .map((Integer id)->{
                    return String.valueOf(id);
                })
                .collect(Collectors.joining(", ", "(", ")"));
        StringBuilder sql = new StringBuilder()
                .append("UPDATE user SET ")
                .append(updateUserColStr)
                .append(" WHERE id IN ")
                .append(idListStr);
        return String.valueOf(sql);
    }
    public String updateUserList(@Param("userList") List<User> userList) {
        StringJoiner allInsertUserColStr = new StringJoiner(", ");
        for (int i = 0; i < userList.size(); i++) {
            StringJoiner insertUserColStr = new StringJoiner(", ", "(", ")")
                    .add(new StringBuilder().append("#{userList[").append(i).append("].id}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].username}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].password}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].status}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].createTime}"))
                    .add(new StringBuilder().append("#{userList[").append(i).append("].updateTime}"));
            allInsertUserColStr.add(String.valueOf(insertUserColStr));
        }
        StringJoiner updateUserColStr = new StringJoiner(", ")
                .add("username = u.username")
                .add("password = u.password")
                .add("status = u.status")
                .add("create_time = u.create_time")
                .add("update_time = u.update_time");
        StringBuilder sql = new StringBuilder()
                .append("INSERT INTO user ")
                .append("(id, username, password, status, create_time, update_time) ")
                .append("VALUES ")
                .append(allInsertUserColStr)
                .append(" AS u ")
                .append("ON DUPLICATE KEY UPDATE ")
                .append(updateUserColStr);
        return String.valueOf(sql);
    }
    public String selectUserById(Integer id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM user WHERE id = #{id}");
        return String.valueOf(sql);
    }
    public String selectUserByIdList(@Param("idList") List<Integer> idList) {
        String idListStr = idList
                .stream()
                .map((Integer id)->{
                    return String.valueOf(id);
                })
                .collect(Collectors.joining(", ", "(", ")"));
        StringBuilder sql = new StringBuilder()
                .append("SELECT * FROM user WHERE id IN ")
                .append(idListStr);
        return String.valueOf(sql);
    }
    public String selectUserByPagination(Pagination pagination) {
        StringBuilder sql = new StringBuilder("SELECT * FROM user LIMIT #{offset}, #{limit}");
        return String.valueOf(sql);
    }
    public String selectUserCount() {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM user");
        return String.valueOf(sql);
    }
}