package com.rbac.v1.controller;
import com.rbac.v1.converter.UserControllerConverter;
import com.rbac.v1.pojo.dto.UserDTO;
import com.rbac.v1.pojo.vo.UserVO;
import com.rbac.v1.service.UserService;
import com.rbac.v1.utils.Pagination;
import com.rbac.v1.utils.StackMetadataWalkerUtils;
import com.rbac.v1.utils.UnifiedResponseStructure;
import com.rbac.v1.utils.UnifiedResponseStructureFormatterUtils;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping("")
    public UnifiedResponseStructure insertUser(@RequestBody UserVO userVO) {
        UserDTO userDTO = UserControllerConverter.convert(userVO);
        userDTO = userService.insertUser(userDTO);
        UserVO data = UserControllerConverter.convert(userDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @DeleteMapping("/{id}")
    public UnifiedResponseStructure deleteUserById(@PathVariable("id") Integer id) {
        UserDTO userDTO = userService.deleteUserById(id);
        UserVO data = UserControllerConverter.convert(userDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @PutMapping("/{id}")
    public UnifiedResponseStructure updateUserById(
            @PathVariable("id") Integer id,
            @RequestBody UserVO userVO
    ) {
        userVO.setId(id);
        UserDTO userDTO = UserControllerConverter.convert(userVO);
        userDTO = userService.updateUserById(userDTO);
        UserVO data = UserControllerConverter.convert(userDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @GetMapping("/{id}")
    public UnifiedResponseStructure selectUserById(@PathVariable("id") Integer id) {
        UserDTO userDTO = userService.selectUserById(id);
        UserVO data = UserControllerConverter.convert(userDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @GetMapping("")
    public UnifiedResponseStructure selectUserByPagination(Pagination pagination) {
        List<UserDTO> userDTOList = userService.selectUserByPagination(pagination);
        List<UserVO> data = UserControllerConverter.convertBatch(userDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
}