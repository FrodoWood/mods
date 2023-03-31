package com.group5.mods.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserTest {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private User user = new User("TestName", "TestUsername", "test@gmail.com", passwordEncoder.encode("testPassword"), "USER_ROLE");

    @Test
    void testGetEmail() {
        assertEquals("test@gmail.com", user.getEmail());
    }

    @Test
    void testGetId() {
        assertEquals(0, user.getId());
    }

    @Test
    void testGetName() {
        assertEquals("TestName", user.getName());
    }

    @Test
    void testGetPassword() {
        assertTrue(passwordEncoder.matches("testPassword", user.getPassword()));
    }

    @Test
    void testGetRoles() {
        assertEquals("USER_ROLE", user.getRoles());
    }

    @Test
    void testGetUsername() {
        assertEquals("TestUsername", user.getUsername());
    }

    @Test
    void testSetEmail() {
        String newEmail = "NewEmail";
        user.setEmail(newEmail);
        assertEquals(newEmail, user.getEmail());
    }

    @Test
    void testSetName() {
        String newName = "NewName";
        user.setName(newName);
        assertEquals(newName, user.getName());  
    }

    @Test
    void testSetPassword() {
        String newPassword = "newPassword";
        user.setPassword(passwordEncoder.encode(newPassword));
        assertTrue(passwordEncoder.matches(newPassword, user.getPassword()));
    }

    @Test
    void testSetRoles() {
        String newRoles = "ROLE_ADMIN";
        user.setRoles(newRoles);
        assertEquals(newRoles, user.getRoles());
    }

    @Test
    void testSetUsername() {
        String newUsername = "NewUsername";
        user.setUsername(newUsername);
        assertEquals(newUsername, user.getUsername());
    }
}
