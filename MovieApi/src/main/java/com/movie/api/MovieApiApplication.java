package com.movie.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.movie.api.constant.AppConstant;
import com.movie.api.model.Roles;
import com.movie.api.repository.RolesRepo;

import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableSwagger2
public class MovieApiApplication implements CommandLineRunner {

	@Autowired
	private RolesRepo rolesRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(MovieApiApplication.class, args);
		System.out.println("Movie Application is Start Running..");
	}

	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
		Roles admin=new Roles();
		Roles manager=new Roles();
		Roles normal=new Roles();
		
		admin.setRoleName("ROLE_ADMIN");
		admin.setRoleId(AppConstant.ADMIN_USER);
		
		manager.setRoleName("ROLE_MANAGER");
		manager.setRoleId(AppConstant.MANAGER_USER);
		
		
		normal.setRoleName("ROLE_NORMAL");
		normal.setRoleId(AppConstant.NORMAL_USER);
		
		List<Roles> roles=List.of(admin,manager,normal);
		List<Roles> rolesp= rolesRepo.saveAll(roles);
		rolesp.forEach(r->System.out.println(r.getRoleName()));
	}
}
