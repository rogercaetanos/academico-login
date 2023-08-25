package com.itb.mif3an.academicologin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.itb.mif3an.academicologin.service.UserService;
import com.itb.mif3an.academicologin.web.dto.UserDto;

@Controller
public class AuthController {
	
	
	@Autowired
	private UserService userService;
	

	@ModelAttribute("user")
	public UserDto userDto() {
		return new UserDto();
	}
	
	@GetMapping("/registration")
	public String showRegistrationForm() {
		
		return "registration";
	}
	
	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("user") UserDto userDto) {
		// Salvar no banco
		
		userService.save(userDto);
		
		return "redirect:/registration?success";  // redireciona para uma "rota"
	}


}
