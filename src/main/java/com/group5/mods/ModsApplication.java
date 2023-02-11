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

	// @Bean
	// CommandLineRunner commandLineRunner(UserRepository userRepository,
	// PasswordEncoder passwordEncoder) {
	// return args -> {
	// userRepository.save(new User("Jas", "jas", "jas@gmail.com",
	// passwordEncoder.encode("pass"), "ROLE_USER"));
	// userRepository.save(new User("Luca", "luca", "luca@gmail.com",
	// passwordEncoder.encode("pass"), "ROLE_ADMIN"));
	// };
	// }

	// @Bean
	// CommandLineRunner commandLineRunner(UserRepository userRepository,
	// PasswordEncoder passwordEncoder) {
	// return args -> {
	// userRepository.save(new User("Luigi", "luigi", "luigi@gmail.com",
	// passwordEncoder.encode("pass"), "ROLE_USER"));
	// userRepository.save(new User("Laura", "laura", "laura@gmail.com",
	// passwordEncoder.encode("pass"), "ROLE_ADMIN"));
	// };
	// }

	@Bean
	public nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect layoutDialect() {
		return new nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect();
	}

}
