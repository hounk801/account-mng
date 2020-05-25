package com;

import com.hnk.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

/**
 * @Description TODO
 * @Author naikuo
 * @Date 2020/3/1 9:08 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class LoginControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginControllerTest.class);

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private String tokenValue = "";

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void loginTestWithUserInfo() {
        try {
            MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                    .post("/login")
                    .content("{\"account\": \"anny@qq.com\",\"password\": \"anny\"}").contentType("application/json"))
                    .andReturn().getResponse();

            Cookie token = response.getCookie("token");
            tokenValue = token.getValue();

            LOGGER.info("token:{}", tokenValue);
            LOGGER.info("data:{}", response.getContentAsString());
            LOGGER.info("---------------------------");
        } catch (Exception e) {
            LOGGER.error("exception={}", e);
        }

    }

    @Test
    public void loginTestWithErrorUserInfo() {
        try {
            MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                    .post("/login")
                    .content("{\"account\": \"nny@qq.com\",\"password\": \"anny\"}").contentType("application/json"))
                    .andReturn().getResponse();
            Cookie token = response.getCookie("token");
            tokenValue = token.getValue();
            LOGGER.info("token:{}", tokenValue);
            LOGGER.info("data:{}", response.getContentAsString());
            LOGGER.info("---------------------------");
        } catch (Exception e) {
            LOGGER.error("exception={}", e);
        }
    }

    @Test
    public void getUserInfoTestWithCookie() {
        try {

            loginTestWithUserInfo();

            MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                    .get("/get-user").cookie(new Cookie("token", tokenValue))
                    .contentType("application/json"))
                    .andReturn().getResponse();
            LOGGER.info("data:{}", response.getContentAsString());
        } catch (Exception e) {
            LOGGER.error("exception={}", e);
        }
    }

    @Test
    public void getUserInfoTestNoCookie() {
        try {
            MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                    .get("/get-user").cookie(new Cookie("token", tokenValue))
                    .contentType("application/json"))
                    .andReturn().getResponse();
            LOGGER.info("data:{}", response.getContentAsString());
        } catch (Exception e) {
            LOGGER.error("exception={}", e);
        }
    }
}
