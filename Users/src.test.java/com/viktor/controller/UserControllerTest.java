package com.viktor.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.viktor.beans.User;
import com.viktor.repository.UserRepository;

/**
 * @author Viktor Angelutsa
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;
    
    @Test
    public void getsAllUsers() throws Exception {
        User user1 = new User("John", "Lewis", "jlewis", "pass", "j.l@gmail.com", "uk");
        User user2 = new User("Nick", "Stuart", "nstuart", "pass", "n.s@gmail.com", "uk");
        
        List<User> users = new ArrayList<User>();
        users.add(user1); 
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Lewis")))
                .andExpect(jsonPath("$[1].firstName", is("Nick")))
                .andExpect(jsonPath("$[1].lastName", is("Stuart")));
    }
    
    @Test
    public void getsAllUsersByCountry() throws Exception {
        User user1 = new User("John", "Lewis", "jlewis", "pass", "j.l@gmail.com", "uk");
        User user2 = new User("Vik", "Angel", "vangel", "pass", "v.a@gmail.com", "fr");
        User user3 = new User("Nick", "Stuart", "nstuart", "pass", "n.s@gmail.com", "uk");
        
        List<User> users = new ArrayList<User>();
        users.add(user1); 
        users.add(user2);
        users.add(user3);
        
        when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(get("/users/country/uk"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Lewis")))
		        .andExpect(jsonPath("$[1].firstName", is("Nick")))
		        .andExpect(jsonPath("$[1].lastName", is("Stuart")));
                
    }
    
    @Test
    public void getsUser() throws Exception {
    	User user = new User("John", "Lewis", "jlewis", "pass", "j.l@gmail.com", "uk");

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));

        mockMvc.perform(get("/users/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Lewis")));
    }
    
    @Test
    public void postsNewUser() throws Exception {
        when(userRepository.save(any(User.class)))
                .thenReturn(new User() {{setId(1);}});

        mockMvc.perform(post("/users/save")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content("{\"firstName\":\"Vik\",\"lastName\":\"Angelo\",\"nickName\":\"vangelo\",\"password\":\"pass\",\"email\":\"v.a@gmail.com\",\"country\":\"uk\"}"))
                .andExpect(status().isCreated());
    }
    
    @Test
    public void deleteUser() throws Exception {
    	User user = new User("John", "Lewis", "jlewis", "pass", "j.l@gmail.com", "uk");

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));

        mockMvc.perform(delete("/users/user/1"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void getThrowsExceptionIfUserNotFound() throws Exception {
        mockMvc.perform(get("/user/5"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteThrowsExceptionIfUserNotFound() throws Exception {
        mockMvc.perform(delete("/user/1"))
                .andExpect(status().isNotFound());
    }
}
