package com.yseop.springboot.query;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "spring.datasource.url=jdbc:h2:mem:test" })
@WebAppConfiguration
public class QueryTests {

    protected String route = "/parents";

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void test_getAll_shouldReturn200() throws Exception {
        mockMvc.perform(get(route)).andExpect(status().is(200));
    }

    @Test
    public void test_post_shouldReturn201() throws Exception {
        mockMvc.perform(post(route).content("{}")).andExpect(status().is(201));
    }

    @Test
    public void test_getSingle_shouldReturn200() throws Exception {
        mockMvc.perform(post(route).content("{}")).andExpect(status().is(201));
        mockMvc.perform(get(route + "/1")).andExpect(status().is(200));
    }

    @Test
    public void test_put_shouldReturn204() throws Exception {
        mockMvc.perform(put(route + "/1").content("{\"name\":\"modified\"}")).andExpect(status().is(204));
    }

    @Test
    public void test_delete_shouldReturn204() throws Exception {
        mockMvc.perform(delete(route + "/1")).andExpect(status().is(204));
    }

}
