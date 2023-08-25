package com.itb.mif3an.academicologin.service;

import com.itb.mif3an.academicologin.model.User;
import com.itb.mif3an.academicologin.web.dto.UserDto;

public interface UserService {
	
	User save(UserDto userDto);
	User findByEmail(String email);

}
