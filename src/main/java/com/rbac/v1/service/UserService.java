package com.rbac.v1.service;
import com.rbac.v1.pojo.dto.UserDTO;
import com.rbac.v1.utils.Pagination;
import java.util.List;
public interface UserService {
    UserDTO insertUser(UserDTO userDTO);
    UserDTO deleteUserById(Integer id);
    UserDTO updateUserById(UserDTO userDTO);
    UserDTO selectUserById(Integer id);
    List<UserDTO> selectUserByPagination(Pagination pagination);
}