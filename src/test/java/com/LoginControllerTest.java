package com;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Description TODO
 * @Author naikuo
 * @Date 2020/3/1 9:08 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class LoginControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void loginTestNoCookie() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"));
    }

    @Test
    public void loginTestNoAnnotationMethod() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"));

    }

    @Test
    public void loginTestWithCookieAndErrorUserInfo() throws Exception {
    }

    @Test
    public void loginTestWithCookieAndRightUserInfo() throws Exception {
    }
}
