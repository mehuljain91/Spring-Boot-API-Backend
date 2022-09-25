package com.example.springbackend.infrastructure.security;

import com.example.springbackend.infrastructure.SpringProfiles;
import com.example.springbackend.user.UserService;
import com.example.springbackend.user.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author mehul jain
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(SpringProfiles.TEST)
public class OAuth2ServerConfigurationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService userService;

    @Test
    public void testGetAccessTokenAsAgent() throws Exception {

        userService.createAgent(Users.AGENT_EMAIL, Users.AGENT_PASSWORD);

        String clientId = "backend-mobile-client";
        String clientSecret = "ccUyb6vS4S8nxfbKPCrN";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("username", Users.AGENT_EMAIL);
        params.add("password", Users.AGENT_PASSWORD);

        mvc.perform(post("/oauth/token")
                .params(params)
                .with(httpBasic(clientId, clientSecret))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(jsonPath("access_token").isString())
                .andExpect(jsonPath("token_type").value("bearer"))
                .andExpect(jsonPath("refresh_token").isString())
                .andExpect(jsonPath("expires_in").isNumber())
                .andExpect(jsonPath("scope").value("mobile_app"));
    }
}
