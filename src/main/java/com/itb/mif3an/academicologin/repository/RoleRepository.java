package com.itb.mif3an.academicologin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itb.mif3an.academicologin.model.Role;




@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String nameRole);

}
