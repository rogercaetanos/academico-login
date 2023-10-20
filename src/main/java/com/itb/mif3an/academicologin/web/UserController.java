package com.itb.mif3an.academicologin.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.itb.mif3an.academicologin.model.Role;
import com.itb.mif3an.academicologin.model.User;
import com.itb.mif3an.academicologin.service.UserService;
import com.itb.mif3an.academicologin.web.dto.UserDto;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/users/living-room")
	public String livingRoom() {
		String home = "redirect:/users/index";
		
		User user = userService.getAuthenticatedUser();
		
		String principalRole = user.getPrincipalRole();
		Collection<Role> roles = user.getRoles();
		
		for(Role r :  roles) {
			if(r.getName().equals("ROLE_ADMIN") && principalRole.equals("ROLE_ADMIN")) {
				home = "redirect:/admin/home";
			}else if(r.getName().equals("ROLE_USER") && principalRole.equals("ROLE_USER")) {
				home = "redirect:/users/home";
			}else if(r.getName().equals("ROLE_INSTRUCTOR") && principalRole.equals("ROLE_INSTRUCTOR")) {
				home = "redirect:/instructor/home";
			}
			
		}

		return home;
	}
	
	
	
	
	
	
	
	@GetMapping("/users/home")
	public String homeUser(Model model) {
		
		String home = "index";
		User user = userService.getAuthenticatedUser();
		String username = user.getEmail();
		model.addAttribute("username", username);
		
		return home;
	}
	
	
	@GetMapping("/users/perfil/{username}")
	public String showPerfilForm(@PathVariable("username") String username, ModelMap model) {
		
		UserDto userDto = new UserDto();
		userDto.setEmail(username);
		User user = userService.findByEmail(userDto.getEmail());
		model.addAttribute("user", user);
		
		return "update-registration";
	}
	
	
	@PostMapping("/users/perfil")
	public String updatePerfilAccount(@ModelAttribute("user")UserDto userDto) {
		
		User user = userService.update(userDto);
		
	//	System.out.println(user.getEnderecos().get(0).getId());
	//	System.out.println(user.getEnderecos().get(0).getCep());
		
		return "redirect:/users/perfil/" + user.getEmail();
	}

}
