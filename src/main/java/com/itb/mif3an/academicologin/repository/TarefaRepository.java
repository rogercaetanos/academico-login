package com.itb.mif3an.academicologin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.itb.mif3an.academicologin.model.Tarefa;


@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
	
	@Query(value="SELECT * FROM tarefas t WHERE t.user_id=?", nativeQuery = true)
	List<Tarefa> findTarefasByIdUser(Long idUsuario);

}
