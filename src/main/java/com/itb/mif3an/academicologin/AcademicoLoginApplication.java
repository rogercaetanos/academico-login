package com.itb.mif3an.academicologin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.itb.mif3an.academicologin.model.Role;
import com.itb.mif3an.academicologin.model.User;
import com.itb.mif3an.academicologin.repository.RoleRepository;
import com.itb.mif3an.academicologin.service.UserService;

@SpringBootApplication
public class AcademicoLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademicoLoginApplication.class, args);
		
		// Utilizamos o construtor para criar nossos objetos
		
	  //User user = new User();
	 // User user2 = new User("rogerio");
	 // User user3 = new User("rogerio", "Caetano");
	   
	}
	
	// Rotina que será executada no momento do start da aplicação!
	
	@Bean
	CommandLineRunner run(UserService userService, RoleRepository roleRepository) {
		return args -> {
			
		if(roleRepository.findAll().size()  == 0) {
			
			userService.saveRole(new Role("ROLE_USER"));
			userService.saveRole(new Role("ROLE_ADMIN"));
			userService.saveRole(new Role("ROLE_INSTRUCTOR"));
			userService.saveRole(new Role("ROLE_STUDENT"));
			
		}
			
			
		};
	}

}
