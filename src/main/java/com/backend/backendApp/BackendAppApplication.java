package com.backend.backendApp;

import com.backend.backendApp.Entity.User;
import com.backend.backendApp.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendAppApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepo userRepo;

	@Value("${user.username}")
	private String usename;

	@Value("${user.password}")
	private String password;

	@Value("${user.role}")
	private String role;

	public static void main(String[] args) {
		SpringApplication.run(BackendAppApplication.class, args);
		System.out.println("Backend App is Started ");
	}

	@Override
	public void run(String... args) throws Exception {
		userRepo.save(new User(this.usename , passwordEncoder.encode(this.password) , this.role));
		System.out.println(passwordEncoder.encode("admin"));
	}
}
