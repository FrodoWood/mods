package com.group5.mods.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group5.mods.DTO.UserDTO;
import com.group5.mods.model.SecurityUser;
import com.group5.mods.model.User;
import com.group5.mods.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserDetails loadUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public void saveUser(UserDTO userDTO){
        userRepository.save(new User(userDTO.getName(), userDTO.getUsername(), userDTO.getEmail(),
                passwordEncoder().encode(userDTO.getPassword()), "ROLE_USER"));
    }
}
