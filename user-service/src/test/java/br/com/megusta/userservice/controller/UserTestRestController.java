package br.com.megusta.userservice.controller;

import br.com.megusta.userservice.UserServiceApplication;
import static br.com.megusta.userservice.builders.UserBuilder.*;

import static br.com.megusta.userservice.builders.UserDtoBuilder.*;

import br.com.megusta.userservice.exceptions.ObjectNotFoundException;
import br.com.megusta.userservice.model.domain.User;
import br.com.megusta.userservice.model.dto.UserDTO;
import br.com.megusta.userservice.repository.UserRepository;
import br.com.megusta.userservice.service.UserService;
import static br.com.megusta.userservice.util.TestUtils.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.http.MediaType.*;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;

import static org.mockito.BDDMockito.*;
/**
 * @author Guilherme Camargo
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserServiceApplication.class)
@WebAppConfiguration
public class UserTestRestController {
    @Autowired
    WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    public User user;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        user = createDefault().withId(1L).withPassword("12345678").build();
    }

    @Test
    public void givenId_WhenFindById_Return200AndUser()throws Exception{
        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
        mockMvc.perform(get("/api/1")
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(user.getName())));
    }
    @Test
    public void givenUser_WhenInsert_Return400AndErrors() throws Exception{
        user.setId(1L);
        given(userRepository.save(ArgumentMatchers.any(User.class)))
                .willReturn(user);
        mockMvc.perform(post("/api")
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(mapToJson(createDtoWithoutName().build())))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors", notNullValue()));
    }
    @Test
    public void givenUser_WhenInsert_Return201HeaderLocation() throws Exception{
        UserDTO dto = createDtoDefault().build();
        given(userRepository.findByEmail(ArgumentMatchers.anyString())).willReturn(Optional.empty());
        given(userRepository.save(ArgumentMatchers.any(User.class))).willReturn(user);
        mockMvc.perform(post("/api")
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(mapToJson(dto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"));
    }
    @Test
    public void givenPassword_WhenUpdatePassword_Return204() throws Exception{
        UserDTO dto = createDtoDefault().build();
        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
        given(userRepository.save(ArgumentMatchers.any(User.class))).willReturn(user);
        mockMvc.perform(put("/api/password/"+anyLong())
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(mapToJson(dto)))
                .andExpect(status().isNoContent());
    }

}
