package com.munaf.ERP_SYSTEM;

import com.munaf.ERP_SYSTEM.configs.security.JwtService;
import com.munaf.ERP_SYSTEM.controllers.UserController;
import com.munaf.ERP_SYSTEM.entities.User;
import com.munaf.ERP_SYSTEM.services.UserService;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class ErpSystemApplicationTests {

	@Test
	void contextLoads() {
	}


	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserService userService;

	User user = User.builder()
			.id(1L)
			.email("hello@gmail.com")
			.password("Sd")
			.build();


	@Test
	void generateToken() {

//		ResponseModel responseModel = .loginUser("munaf@gmail.com", "Munaf@786");
//		System.out.println(responseModel);




	}
}
