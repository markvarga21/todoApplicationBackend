package com.markvarga21;

import com.markvarga21.entity.AppUser;
import com.markvarga21.entity.Role;
import com.markvarga21.service.AppUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class TodoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

	@Bean
	CommandLineRunner run(@Qualifier("appUserServiceImpl") AppUserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new AppUser(null, "John Cena", "john", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Milly Alcock", "milly", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Johnny Depp", "jdepp", "1234", new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Stevan Segal", "steve", "steve123", new ArrayList<>()));

			userService.addRoleToAppUser("john", "ROLE_USER");
			userService.addRoleToAppUser("john", "ROLE_MANAGER");
			userService.addRoleToAppUser("milly", "ROLE_MANAGER");
			userService.addRoleToAppUser("jdepp", "ROLE_ADMIN");
			userService.addRoleToAppUser("steve", "ROLE_SUPER_ADMIN");
			userService.addRoleToAppUser("steve", "ROLE_ADMIN");
			userService.addRoleToAppUser("steve", "ROLE_USER");

		};
	}
}
