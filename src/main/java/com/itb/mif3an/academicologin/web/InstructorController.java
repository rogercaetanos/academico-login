package com.itb.mif3an.academicologin.web;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itb.mif3an.academicologin.model.Tarefa;
import com.itb.mif3an.academicologin.model.User;
import com.itb.mif3an.academicologin.service.TarefaService;
import com.itb.mif3an.academicologin.service.UserService;

@Controller
//@RequestMapping("/petshop/admin")  (nome-do-projeto/model manipulado)
@RequestMapping("/instructor")
public class InstructorController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TarefaService tarefaService;

	
	@GetMapping("/home")
	public String homeInstructor(Model model) {
		
		String home = "index-professor";
		User user = userService.getAuthenticatedUser();
		String username = user.getEmail();
		model.addAttribute("username", username);
		return home;
		
	}
	
	@GetMapping("/tarefas/nova-tarefa")
	public String showNovaTarefa(Model model, Tarefa tarefa) {
		
		model.addAttribute("tarefa", tarefa);
		
		return "nova-tarefa-professor";
	}
	
	
	@PostMapping("/tarefas/add-tarefa")
	public String addTarefa(@RequestParam(value="file", required = false) MultipartFile file,
			@ModelAttribute("tarefa") Tarefa tarefa, BindingResult result) {
		
		User user = userService.getAuthenticatedUser();
		tarefa.setUser(user);
		if(file != null && !file.getOriginalFilename().isEmpty()) {
		     try {
				tarefa.setFoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}	
		} else {
			tarefa.setFoto(null);
		}
		
		tarefaService.save(tarefa);
		
		return "redirect:/instructor/tarefas/todas-tarefas";
	}
	
	
	@GetMapping("/tarefas/todas-tarefas")
	public String showTarefasInstructor(Model model) {
		
		User user = userService.getAuthenticatedUser();
		String username = user.getEmail();
		
		List<Tarefa> tarefasDb = tarefaService.findTarefasByIdUser(user.getId());
		
		if(tarefasDb != null) {
			for(Tarefa t: tarefasDb) {
				t.setFotoString(Base64.getEncoder().encodeToString(t.getFoto()));
			}
		}
		
		model.addAttribute("username", username);
		model.addAttribute("tarefas", tarefasDb);
		
		return "lista-tarefas-professor";
	}
	
	
		
}
