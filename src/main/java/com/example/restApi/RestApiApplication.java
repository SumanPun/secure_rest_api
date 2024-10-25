package com.example.restApi;

import com.example.restApi.config.AppConstants;
import com.example.restApi.model.Role;
import com.example.restApi.model.User;
import com.example.restApi.repositories.RoleRepository;
import com.example.restApi.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.restApi"})
public class RestApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		try{
			Optional<Role> adminRole = this.roleRepository.findRoleById(AppConstants.ADMIN_USER);
			if(adminRole.isEmpty()){
				Role addAdminRole = new Role();
				addAdminRole.setId(AppConstants.ADMIN_USER);
				addAdminRole.setName("ROLE_ADMIN");
				this.roleRepository.save(addAdminRole);
				System.out.println("ROLE_ADMIN created");
			}
			Optional<Role> userRole = this.roleRepository.findRoleById(AppConstants.NORMAL_USER);
			if(userRole.isEmpty()){
				Role addNormalUserRole = new Role();
				addNormalUserRole.setId(AppConstants.NORMAL_USER);
				addNormalUserRole.setName("ROLE_NORMAL");
				this.roleRepository.save(addNormalUserRole);
				System.out.println("ROLE_NORMAL created");
			}

			Optional<User> adminUser = this.userRepository.findByEmail("admin@test.com");
			if(adminUser.isEmpty()){
				User user = new User();
				user.setEmail("admin@test.com");
				user.setPassword(this.passwordEncoder.encode("password"));
				user.setFirstName("Admin");
				user.setLastName("Admin");
				user.setActive(true);
				user.setCreatedAt(LocalDateTime.now());

				Role role = this.roleRepository.findById(AppConstants.ADMIN_USER).get();
				user.getRoles().add(role);
				this.userRepository.save(user);
				System.out.println("admin user created with admin@test.com");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
