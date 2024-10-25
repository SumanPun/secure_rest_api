package com.example.restApi;

import com.example.restApi.controllers.UserController;
import com.example.restApi.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RestApiApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
	}

	@Test
	void testAllBeansAreLoaded() {
		assertNotNull(applicationContext.getBean(UserController.class));
		assertNotNull(applicationContext.getBean(UserService.class));
		// Add more beans as needed
	}

}
