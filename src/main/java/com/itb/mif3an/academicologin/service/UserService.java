package com.itb.mif3an.academicologin.service;

import com.itb.mif3an.academicologin.model.User;

public interface UserService {
	
	User findByEmail(String email);

}
