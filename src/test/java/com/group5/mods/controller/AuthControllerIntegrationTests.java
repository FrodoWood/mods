package com.group5.mods.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import com.group5.mods.ModsApplication;
import com.group5.mods.DTO.UserDTO;
import com.group5.mods.config.SecurityConfig;
import com.group5.mods.model.User;
import com.group5.mods.repository.UserRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
    "spring.datasource.url=jdbc:mysql://localhost:3306/mods_test",
    "spring.datasource.username=root",
    "spring.datasource.password=",
    "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver",
    "spring.jpa.hibernate.ddl-auto=create",
})
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration(classes = { SecurityConfig.class, ModsApplication.class })
public class AuthControllerIntegrationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegisterUserSuccess() throws Exception {
        UserDTO userDTO = new UserDTO("Test User", "testuser", "testuser@example.com", "password", "ROLE_USER");
        
        mockMvc.perform(post("/registerUser")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("userDTO", userDTO)
                .with(csrf())
                .session(new MockHttpSession())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(header().doesNotExist("Location"));

        Optional<User> user = userRepository.findByUsername(userDTO.getUsername());
        assertTrue(user.isPresent());
        assertEquals(userDTO.getName(), user.get().getName());
        assertEquals(userDTO.getEmail(), user.get().getEmail());
        assertEquals(userDTO.getRoles(), user.get().getRoles());
    }

    @Test
    public void testRegisterEmailTakenFailure() throws Exception {
        // Create a test user with email existinguser@gmail.com
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User existingUser = new User("TestName", "TestUsername", "existinguser@example.com", passwordEncoder.encode("testPassword"), "USER_ROLE");
        userRepository.save(existingUser);

        // attempt to register with same email
        UserDTO userDTO = new UserDTO("New User", "newUsername", "existinguser@example.com", "password", "ROLE_USER");

        mockMvc.perform(post("/registerUser")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("userDTO", userDTO)
                .with(csrf())
                .session(new MockHttpSession())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("/register"))
                .andExpect(model().attributeExists("emailTakenError"));
    }

    @Test
    public void testRegisterUsernameTakenFailure() throws Exception {
        // Create a test user with username existingUsername
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User existingUser = new User("TestName", "existingUsername", "TestEmail", passwordEncoder.encode("testPassword"), "USER_ROLE");
        userRepository.save(existingUser);

        // attempt to register with same email
        UserDTO userDTO = new UserDTO("New User", "existingUsername", "newEmail@gmail.com", "password", "ROLE_USER");

        mockMvc.perform(post("/registerUser")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("userDTO", userDTO)
                .with(csrf())
                .session(new MockHttpSession())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("/register"))
                .andExpect(model().attributeExists("usernameTakenError"));
    }

}
