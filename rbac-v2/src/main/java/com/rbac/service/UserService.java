package com.rbac.service;
import com.rbac.pojo.dto.UserDTO;
import com.rbac.utils.Pagination;
import java.util.List;
public interface UserService {
    UserDTO insertUser(UserDTO userDTO);
    List<UserDTO> insertUserList(List<UserDTO> userDTOList);
    UserDTO updateUserById(UserDTO userDTO);
    List<UserDTO> updateUserByIdList(List<Integer> idList, UserDTO userDTO);
    List<UserDTO> updateUserList(List<UserDTO> userDTOList);
    UserDTO selectUserById(Integer id);
    List<UserDTO> selectUserByIdList(List<Integer> idList);
    List<UserDTO> selectUserByPagination(Pagination pagination);
}