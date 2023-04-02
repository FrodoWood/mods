package com.group5.mods;

import java.util.Optional;

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
	public nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect layoutDialect() {
		return new nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect();
	}

}
