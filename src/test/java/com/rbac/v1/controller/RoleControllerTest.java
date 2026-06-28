package com.rbac.v1.controller;
import com.rbac.v1.config.H2DataSourceConfig;
import com.rbac.v1.utils.ConsoleLoggerUtils;
import com.rbac.v1.utils.JSONUtils;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
@SpringBootTest
@AutoConfigureMockMvc
@Import({H2DataSourceConfig.class})
class RoleControllerTest {
    @Resource
    private MockMvc mockMvc;
    @Test
    public void testInsertRole() throws Exception {
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
        String jsonString = JSONUtils.serialize(map);
        String responseString = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/api/role")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        ConsoleLoggerUtils.log(responseString);
    }
    @Test
    public void testDeleteRoleById() throws Exception {
        Integer id = 1;
        String responseString = mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/api/role/{id}", id)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        ConsoleLoggerUtils.log(responseString);
    }
    @Test
    public void testUpdateRoleById() throws Exception {
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
        String jsonString = JSONUtils.serialize(map);
        String responseString = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/api/role/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        ConsoleLoggerUtils.log(responseString);
    }
    @Test
    public void testSelectRoleById() throws Exception {
        Integer id = 1;
        String responseString = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/api/role/{id}", id)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        ConsoleLoggerUtils.log(responseString);
    }
    @Test
    public void testSelectRoleByPagination() throws Exception {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>(Map.ofEntries(
                Map.entry("page", new ArrayList<>(Arrays.asList("1"))),
                Map.entry("limit", new ArrayList<>(Arrays.asList("10")))
        ));
        String responseString = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/api/role")
                                .params(multiValueMap)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        ConsoleLoggerUtils.log(responseString);
    }
}