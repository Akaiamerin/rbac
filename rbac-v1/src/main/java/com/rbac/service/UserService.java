package com.rbac.service;
import com.rbac.pojo.dto.UserDTO;
import com.rbac.utils.Pagination;
import java.util.List;
public interface UserService {
    UserDTO insertUser(UserDTO userDTO);
    UserDTO deleteUserById(Integer id);
    UserDTO updateUserById(UserDTO userDTO);
    UserDTO selectUserById(Integer id);
    List<UserDTO> selectUserByPagination(Pagination pagination);
}