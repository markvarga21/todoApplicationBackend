package com.markvarga21;

import com.markvarga21.configuration.security.RsaKeyProperties;
import com.markvarga21.entity.AppUser;
import com.markvarga21.entity.Role;
import com.markvarga21.service.AppUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class TodoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

	@Bean
	CommandLineRunner run(@Qualifier("appUserServiceImpl") AppUserService userService, PasswordEncoder encoder) {
		return args -> {
			userService.saveRole(new Role(null, "admin"));
			userService.saveRole(new Role(null, "user"));

			userService.saveUser(new AppUser(null, "Admin", "admin", "{noop}admin", new ArrayList<>()));

			userService.addRoleToAppUser("admin", "admin");
			userService.addRoleToAppUser("admin", "user");

		};
	}
}
