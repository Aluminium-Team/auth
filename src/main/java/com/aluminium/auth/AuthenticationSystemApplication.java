package com.aluminium.auth;

import com.aluminium.auth.model.Token;
import com.aluminium.auth.model.User;
import com.aluminium.auth.service.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class AuthenticationSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationSystemApplication.class, args);
	}
}
