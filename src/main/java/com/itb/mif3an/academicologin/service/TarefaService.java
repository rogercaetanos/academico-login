package com.itb.mif3an.academicologin.service;

import java.util.List;

import com.itb.mif3an.academicologin.model.Tarefa;

public interface TarefaService {
	
	Tarefa save(Tarefa tarefa);
	List<Tarefa> findAll();
	List<Tarefa> findTarefasByIdUser(Long idUsuario);

}
