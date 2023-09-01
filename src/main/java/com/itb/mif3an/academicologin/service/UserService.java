package com.itb.mif3an.academicologin.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.itb.mif3an.academicologin.model.User;
import com.itb.mif3an.academicologin.web.dto.UserDto;

public interface UserService extends UserDetailsService{
	
	User save(UserDto userDto);
	User findByEmail(String email);

}
