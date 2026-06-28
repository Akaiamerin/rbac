package com.rbac.v2.controller;
import com.rbac.v2.converter.UserControllerConverter;
import com.rbac.v2.pojo.dto.UserDTO;
import com.rbac.v2.pojo.vo.UserVO;
import com.rbac.v2.service.UserService;
import com.rbac.v2.utils.Pagination;
import com.rbac.v2.utils.StackMetadataWalkerUtils;
import com.rbac.v2.utils.UnifiedResponseStructure;
import com.rbac.v2.utils.UnifiedResponseStructureFormatterUtils;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.http.HttpStatus;
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
        UserDTO userDTO = UserControllerConverter.convertRequest(userVO);
        userDTO = userService.insertUser(userDTO);
        UserVO data = UserControllerConverter.convertResponse(userDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @PostMapping("/batch")
    public UnifiedResponseStructure insertUserList(@RequestBody List<UserVO> userVOList) {
        List<UserDTO> userDTOList = UserControllerConverter.convertRequest(userVOList);
        userDTOList = userService.insertUserList(userDTOList);
        List<UserVO> data = UserControllerConverter.convertResponse(userDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @PutMapping("/{id}")
    public UnifiedResponseStructure updateUserById(
            @PathVariable("id") Integer id,
            @RequestBody UserVO userVO
    ) {
        UserDTO userDTO = UserControllerConverter.convertRequest(id, userVO);
        userDTO = userService.updateUserById(userDTO);
        UserVO data = UserControllerConverter.convertResponse(userDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @PutMapping("/batch/{idList}")
    public UnifiedResponseStructure updateUserByIdList(
            @PathVariable("idList") List<Integer> idList,
            @RequestBody UserVO userVO
    ) {
        UserDTO userDTO = UserControllerConverter.convertRequest(userVO);
        List<UserDTO> userDTOList = userService.updateUserByIdList(idList, userDTO);
        List<UserVO> data = UserControllerConverter.convertResponse(userDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @PutMapping("/batch")
    public UnifiedResponseStructure updateUserList(@RequestBody List<UserVO> userVOList) {
        List<UserDTO> userDTOList = UserControllerConverter.convertRequest(userVOList);
        userDTOList = userService.updateUserList(userDTOList);
        List<UserVO> data = UserControllerConverter.convertResponse(userDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @GetMapping("/{id}")
    public UnifiedResponseStructure selectUserById(@PathVariable("id") Integer id) {
        UserDTO userDTO = userService.selectUserById(id);
        UserVO data = UserControllerConverter.convertResponse(userDTO);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
    @GetMapping("/batch/{idList}")
    public UnifiedResponseStructure selectUserByIdList(@PathVariable("idList") List<Integer> idList) {
        List<UserDTO> userDTOList = userService.selectUserByIdList(idList);
        List<UserVO> data = UserControllerConverter.convertResponse(userDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getClassName(), data);
    }
    @GetMapping("")
    public UnifiedResponseStructure selectUserByPagination(Pagination pagination) {
        List<UserDTO> userDTOList = userService.selectUserByPagination(pagination);
        List<UserVO> data = UserControllerConverter.convertResponse(userDTOList);
        return UnifiedResponseStructureFormatterUtils.format(HttpStatus.OK.value(), StackMetadataWalkerUtils.getMethodName(), data);
    }
}