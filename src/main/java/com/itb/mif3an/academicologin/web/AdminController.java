package com.itb.mif3an.academicologin.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itb.mif3an.academicologin.model.Role;
import com.itb.mif3an.academicologin.model.User;
import com.itb.mif3an.academicologin.service.UserService;

@Controller
//@RequestMapping("/petshop/admin")  (nome-do-projeto/model manipulado)
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;

	
	@GetMapping("/home")
	public String homeAdmin(Model model) {
		
		String home = "index-admin";
		User user = userService.getAuthenticatedUser();
		String username = user.getEmail();
		model.addAttribute("username", username);
		return home;
		
	}
	
	@GetMapping("/usuarios/todos-usuarios")
	public String showUsuarios(Model model) {
		
		User user = userService.getAuthenticatedUser();
		String username = user.getEmail();
		List<User> usuarios = userService.findAllUsersByExceptPrincipalRole("ROLE_ADMIN");
		model.addAttribute("username", username);
		model.addAttribute("usuarios", usuarios);
		
		return "lista-usuarios-admin";
	}
	
	@GetMapping("/usuarios/editar-user/{id}")
	public String showUpdateFormUser(@PathVariable("id") Long id, Model model) {
		User user = userService.getAuthenticatedUser();
		List<Role> roles = userService.findAllRoles();
		String username = user.getEmail();
		
		User userDb = userService.findUserById(id);
		
		model.addAttribute("usuario", userDb);
		model.addAttribute("allRoles", roles);
		model.addAttribute("username", username);
		
		return "update-usuario";
	}
	
}
