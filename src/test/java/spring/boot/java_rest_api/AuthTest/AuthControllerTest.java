package spring.boot.java_rest_api.AuthTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import spring.boot.java_rest_api.controller.AuthController;
import spring.boot.java_rest_api.config.SecurityConfig;
import spring.boot.java_rest_api.service.AuthService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({AuthController.class})
@Import({SecurityConfig.class, AuthService.class})
public class AuthControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void shouldReturnUnauthorizedWhenNoSendCredentials() throws Exception {
        mvc.perform(get("/")).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnUnauthorizedWhenSendBadCredentials() throws Exception {
        mvc.perform(post("/auth/token")
                        .with(httpBasic("user", "12345")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnOKWhenSendSuccessfullyCredentials() throws Exception {
        MvcResult result = mvc.perform(post("/auth/token")
                        .with(httpBasic("carlos.paez", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn();
    }
}
