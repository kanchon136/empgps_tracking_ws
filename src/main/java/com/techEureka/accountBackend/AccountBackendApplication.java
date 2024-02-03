package com.techEureka.accountBackend;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.techEureka.accountBackend.entity.UsersInfo;
import com.techEureka.accountBackend.entity.UsersRole;
import com.techEureka.accountBackend.repository.UsersInfoRepo;
import com.techEureka.accountBackend.repository.UsersRoleRepo;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class AccountBackendApplication implements CommandLineRunner {

	@Autowired
	private UsersInfoRepo userRepo;

	@Autowired
	private UsersRoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(AccountBackendApplication.class, args);
	}
	
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.techEureka.accountBackend.controller"))
				.paths(PathSelectors.any()).build();
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("===============start the run method================");
//		UsersInfo user = new UsersInfo();
//		user.setAddress("Mymensingh");
//		user.setBlood("A+");
//		user.setCreatedDate(new Date());
//		user.setDob(new Date());
//		user.setEmail("miakanchon5100@gmail.com");
//		user.setName("kanchon");
//		user.setPassword("kanchon11");
//		user.setPhone("01715625011");
//		user.setTeamId(0L);
//		user.setTeamLeadFlg(1);
//		user.setTeamName("Java");
//		user.setUserName("kanchon");
//		//user.setUsersRole(usersRole);
//		
//		userRepo.save(user);
//		
//		UsersRole role = new UsersRole();
//		role.setCreatedDate(new Date());
//		role.setDefaultRoleId(0L);
//		role.setRoleName("ADMIN");
//		role.setSpecPagePermission("Y");
//		role.setUserName("kanchon");
//		role.setUsersInfo(user);
//		role.setVerifyFlg(1);
//		roleRepo.save(role);

		System.out.println("==============EmpTrack Service is Running====================");

	}

}
