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
class UserControllerTest {
    @Resource
    private MockMvc mockMvc;
    @Test
    public void testInsertUser() throws Exception {
        Map<String, Object> map = new HashMap<>(Map.ofEntries(
                Map.entry("username", "a"),
                Map.entry("password", "a"),
                Map.entry("roleList", new ArrayList<>(Arrays.asList(
                        Map.entry("id", 1),
                        Map.entry("id", 2),
                        Map.entry("id", 3)
                )))
        ));
        String jsonString = JSONUtils.serialize(map);
        String responseString = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/api/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        ConsoleLoggerUtils.log(responseString);
    }
    @Test
    public void testDeleteUserById() throws Exception {
        Integer id = 1;
        String responseString = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .delete("/api/user/{id}", id)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        ConsoleLoggerUtils.log(responseString);
    }
    @Test
    public void testUpdateUserById() throws Exception {
        Integer id = 1;
        Map<String, Object> map = new HashMap<>(Map.ofEntries(
                Map.entry("username", "a"),
                Map.entry("password", "a"),
                Map.entry("roleList", new ArrayList<>(Arrays.asList(
                        Map.entry("id", 2),
                        Map.entry("id", 3),
                        Map.entry("id", 4)
                )))
        ));
        String jsonString = JSONUtils.serialize(map);
        String responseString = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/api/user/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        ConsoleLoggerUtils.log(responseString);
    }
    @Test
    public void testSelectUserById() throws Exception {
        Integer id = 1;
        String responseString = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/api/user/{id}", id)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        ConsoleLoggerUtils.log(responseString);
    }
    @Test
    public void testSelectUserByPagination() throws Exception {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>(Map.ofEntries(
                Map.entry("page", new ArrayList<>(Arrays.asList("1"))),
                Map.entry("limit", new ArrayList<>(Arrays.asList("10")))
        ));
        String responseString = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/api/user")
                                .params(multiValueMap)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        ConsoleLoggerUtils.log(responseString);
    }
}