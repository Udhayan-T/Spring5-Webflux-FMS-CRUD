package com.fmsuser.controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmsuser.model.User;
import com.fmsuser.service.SecurityTokenGenerator;
import com.fmsuser.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/userservice")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	SecurityTokenGenerator tokenGenerator;

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User loginDetail) {
		try {
			String userId = loginDetail.getUsername();
			String password = loginDetail.getPassword();
			if (userId == null || password == null) {
				throw new Exception("username or passwrd cannot be empty");
			}
			Mono<User> MonoUser = userService.findByUserIdAndPassword(userId,
					password);
			User user = MonoUser.toFuture().get();
			Map<String, String> map = tokenGenerator.generateToken(user);
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("message" + e.getMessage(),
					HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/getPmoEmployees")
	public Flux<User> getPmoEmployees(){
		return userService.getPmoEmployees();
	}
	
	@PostMapping("/addpmo/{employeeId}")
	public Mono<User> addPMO(@PathVariable String employeeId) throws IOException{
		System.out.println(employeeId);
		return userService.addPMO(employeeId).log();
	}
	
	@PatchMapping("/removepmo/{employeeId}")
	public Mono<User> removePMO(@PathVariable String employeeId) throws InterruptedException, ExecutionException{
		return userService.removePMO(employeeId);
	}
	

}
