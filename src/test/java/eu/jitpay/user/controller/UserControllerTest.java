package eu.jitpay.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.jitpay.user.TestUtils;
import eu.jitpay.user.service.UserService;
import eu.jitpay.user.service.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {
    @Autowired
    protected WebApplicationContext webApplicationContext;
    protected MockMvc mvc;

    @Autowired
    UserService userService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void saveUser_whenUserExist_thenStatus400() throws Exception {
        String uri = "/api/v1/users";
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("arezoo");
        userDTO.setSecondName("azhdari");
        userDTO.setEmail("arezoo.azhdari@gmail.com");
        String inputJson = mapToJson(userDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        Object object = TestUtils.convertJsonToObject(mvcResult.getResponse().getContentAsString());
        Assertions.assertNotNull(object);
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

}