package com.rbac.controller;
import com.rbac.utils.JacksonObjectMapper;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
@SpringBootTest
@AutoConfigureMockMvc
class RoleControllerTest {
    @Resource
    private MockMvc mockMvc;
    @Test
    public void insertRole() throws Exception {
        Map<String, Object> map = new HashMap<>(Map.ofEntries(
                Map.entry("name", "a"),
                Map.entry("permissionList", new ArrayList<>(Arrays.asList(
                        Map.entry("id", 1),
                        Map.entry("id", 2),
                        Map.entry("id", 3)
                ))),
                Map.entry("userList", new ArrayList<>(Arrays.asList(
                        Map.entry("id", 1),
                        Map.entry("id", 2),
                        Map.entry("id", 3)
                )))
        ));
        String json = JacksonObjectMapper.objectMapper.writeValueAsString(map);
        String resp = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/api/role")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(resp);
    }
    @Test
    public void deleteRoleById() throws Exception {
        Integer id = 1;
        String resp = mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/role/{id}", id)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(resp);
    }
    @Test
    public void updateRoleById() throws Exception {
        Integer id = 1;
        Map<String, Object> map = new HashMap<>(Map.ofEntries(
                Map.entry("name", "a"),
                Map.entry("permissionList", new ArrayList<>(Arrays.asList(
                        Map.entry("id", 2),
                        Map.entry("id", 3),
                        Map.entry("id", 4)
                ))),
                Map.entry("userList", new ArrayList<>(Arrays.asList(
                        Map.entry("id", 1),
                        Map.entry("id", 2),
                        Map.entry("id", 3)
                )))
        ));
        String json = JacksonObjectMapper.objectMapper.writeValueAsString(map);
        String resp = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/api/role/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(resp);
    }
    @Test
    public void selectRoleById() throws Exception {
        Integer id = 1;
        String resp = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/api/role/{id}", id)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(resp);
    }
    @Test
    public void selectRoleByPagination() throws Exception {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>(Map.ofEntries(
                Map.entry("page", new ArrayList<>(Arrays.asList("1"))),
                Map.entry("limit", new ArrayList<>(Arrays.asList("10")))
        ));
        String resp = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/api/role")
                                .params(multiValueMap)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(resp);
    }
}