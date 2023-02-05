package com.group5.mods;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.group5.mods.model.User;
import com.group5.mods.repository.UserRepository;

@SpringBootApplication
public class ModsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModsApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		return args -> {
			userRepository.save(new User("jas", "jas@gmail.com",
					passwordEncoder.encode("pass"), "ROLE_USER"));
			userRepository.save(new User("luca", "luca@gmail.com",
					passwordEncoder.encode("pass"), "ROLE_ADMIN"));
		};
	}

}
