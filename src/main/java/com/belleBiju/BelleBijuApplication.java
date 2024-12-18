package com.belleBiju;

import com.belleBiju.DTOs.requests.UserRequest;
import com.belleBiju.entities.Enums.ROLES_PERMISSIONS;
import com.belleBiju.entities.User;
import com.belleBiju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class BelleBijuApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BelleBijuApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		UserRequest userRequest = new UserRequest();

		userRequest.setNome("teste");
		userRequest.setUsername("teste");
		userRequest.setPassword("teste");
		userRequest.setRoles(ROLES_PERMISSIONS.ADMIN);

		userService.SaveNewUser(userRequest);
	}
}
