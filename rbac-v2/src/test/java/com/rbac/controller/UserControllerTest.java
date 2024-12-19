package com.rbac.controller;
import com.rbac.utils.JacksonObjectMapper;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
class UserControllerTest {
    @Resource
    private MockMvc mockMvc;
    @Test
    public void insertUser() throws Exception {
        Map<String, Object> map = new HashMap<>(Map.ofEntries(
                Map.entry("username", "a"),
                Map.entry("password", "a"),
                Map.entry("status", 0),
                Map.entry("createTime", LocalDateTime.now()),
                Map.entry("updateTime", LocalDateTime.now()),
                Map.entry("roleList", new ArrayList<>(Arrays.asList(
                        Map.entry("id", 1),
                        Map.entry("id", 2),
                        Map.entry("id", 3)
                )))
        ));
        String json = JacksonObjectMapper.objectMapper.writeValueAsString(map);
        String resp = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/api/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(resp);
    }
    @Test
    public void insertUserList() throws Exception {
        List<Map<String, Object>> list = new ArrayList<>(Arrays.asList(
                new HashMap<>(Map.ofEntries(
                        Map.entry("username", "a1"),
                        Map.entry("password", "a"),
                        Map.entry("status", 0),
                        Map.entry("createTime", LocalDateTime.now()),
                        Map.entry("updateTime", LocalDateTime.now()),
                        Map.entry("roleList", new ArrayList<>(Arrays.asList(
                                Map.entry("id", 1),
                                Map.entry("id", 2),
                                Map.entry("id", 3)
                        )))
                )),
                new HashMap<>(Map.ofEntries(
                        Map.entry("username", "a2"),
                        Map.entry("password", "a"),
                        Map.entry("status", 0),
                        Map.entry("createTime", LocalDateTime.now()),
                        Map.entry("updateTime", LocalDateTime.now()),
                        Map.entry("roleList", new ArrayList<>(Arrays.asList(
                                Map.entry("id", 1),
                                Map.entry("id", 2),
                                Map.entry("id", 3)
                        )))
                )),
                new HashMap<>(Map.ofEntries(
                        Map.entry("username", "a3"),
                        Map.entry("password", "a"),
                        Map.entry("status", 0),
                        Map.entry("createTime", LocalDateTime.now()),
                        Map.entry("updateTime", LocalDateTime.now()),
                        Map.entry("roleList", new ArrayList<>(Arrays.asList(
                                Map.entry("id", 1),
                                Map.entry("id", 2),
                                Map.entry("id", 3)
                        )))
                ))
        ));
        String json = JacksonObjectMapper.objectMapper.writeValueAsString(list);
        String resp = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/api/user/batch")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(resp);
    }
    @Test
    public void updateUserById() throws Exception {
        Integer id = 1;
        Map<String, Object> map = new HashMap<>(Map.ofEntries(
                Map.entry("username", "a"),
                Map.entry("password", "a"),
                Map.entry("status", Integer.MAX_VALUE),
                Map.entry("updateTime", LocalDateTime.now()),
                Map.entry("roleList", new ArrayList<>(Arrays.asList(
                        Map.entry("id", 2),
                        Map.entry("id", 3),
                        Map.entry("id", 4)
                )))
        ));
        String json = JacksonObjectMapper.objectMapper.writeValueAsString(map);
        String resp = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/api/user/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(resp);
    }
    @Test
    public void updateUserByIdList() throws Exception {
        String str = Stream
                .of(1, 2, 3)
                .map((Integer id)->{
                    return String.valueOf(id);
                })
                .collect(Collectors.joining(","));
        Map<String, Object> map = new HashMap<>(Map.ofEntries(
                Map.entry("status", Integer.MAX_VALUE),
                Map.entry("createTime", LocalDateTime.now()),
                Map.entry("updateTime", LocalDateTime.now()),
                Map.entry("roleList", new ArrayList<>(Arrays.asList(
                        Map.entry("id", 2),
                        Map.entry("id", 3),
                        Map.entry("id", 4)
                )))
        ));
        String json = JacksonObjectMapper.objectMapper.writeValueAsString(map);
        String resp = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/api/user/batch/{idList}", str)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(resp);
    }
    @Test
    public void updateUserList() throws Exception {
        List<Map<String, Object>> list = new ArrayList<>(Arrays.asList(
                new HashMap<>(Map.ofEntries(
                        Map.entry("id", 1),
                        Map.entry("username", "a1"),
                        Map.entry("password", "a"),
                        Map.entry("status", Integer.MAX_VALUE),
                        Map.entry("createTime", LocalDateTime.now()),
                        Map.entry("updateTime", LocalDateTime.now()),
                        Map.entry("roleList", new ArrayList<>(Arrays.asList(
                                Map.entry("id", 2),
                                Map.entry("id", 3),
                                Map.entry("id", 4)
                        )))
                )),
                new HashMap<>(Map.ofEntries(
                        Map.entry("id", 2),
                        Map.entry("username", "a2"),
                        Map.entry("password", "a"),
                        Map.entry("status", Integer.MAX_VALUE),
                        Map.entry("createTime", LocalDateTime.now()),
                        Map.entry("updateTime", LocalDateTime.now()),
                        Map.entry("roleList", new ArrayList<>(Arrays.asList(
                                Map.entry("id", 2),
                                Map.entry("id", 3),
                                Map.entry("id", 4)
                        )))
                )),
                new HashMap<>(Map.ofEntries(
                        Map.entry("id", 3),
                        Map.entry("username", "a3"),
                        Map.entry("password", "a"),
                        Map.entry("status", Integer.MAX_VALUE),
                        Map.entry("createTime", LocalDateTime.now()),
                        Map.entry("updateTime", LocalDateTime.now()),
                        Map.entry("roleList", new ArrayList<>(Arrays.asList(
                                Map.entry("id", 2),
                                Map.entry("id", 3),
                                Map.entry("id", 4)
                        )))
                ))
        ));
        String json = JacksonObjectMapper.objectMapper.writeValueAsString(list);
        String resp = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .put("/api/user/batch")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(resp);
    }
    @Test
    public void selectUserById() throws Exception {
        Integer id = 1;
        String resp = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/api/user/{id}", id)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(resp);
    }
    @Test
    public void selectUserByPagination() throws Exception {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>(Map.ofEntries(
                Map.entry("page", new ArrayList<>(Arrays.asList("1"))),
                Map.entry("limit", new ArrayList<>(Arrays.asList("10")))
        ));
        String resp = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/api/user")
                                .params(multiValueMap)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(resp);
    }
}