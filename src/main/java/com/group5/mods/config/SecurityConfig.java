package com.group5.mods.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.group5.mods.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    // UserDetails admin = User.withUsername("admin")
    // .password(encoder.encode("pass"))
    // .roles("ADMIN")
    // .build();
    // UserDetails user = User.withUsername("user")
    // .password(encoder.encode("pass"))
    // .roles("USER")
    // .build();
    // return new InMemoryUserDetailsManager(admin, user);
    // }

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/user").hasAuthority("ROLE_USER")
                        .anyRequest().authenticated())
                .userDetailsService(userService)
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .invalidateHttpSession(true)
                        .permitAll()

                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
