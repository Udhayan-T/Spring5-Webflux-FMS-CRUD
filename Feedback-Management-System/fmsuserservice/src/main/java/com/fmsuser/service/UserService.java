package com.fmsuser.service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.fmsuser.excception.UserNotFoundException;
import com.fmsuser.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
	public Mono<User> findByUserIdAndPassword(String userId,String password) throws UserNotFoundException, InterruptedException, ExecutionException;

	public Mono<User> findByUserName(String username) throws UserNotFoundException, InterruptedException, ExecutionException;
	
	public Flux<User> getPmoEmployees();
	
	public Mono<User> addPMO(String employeeId) throws IOException;
	
	public Mono<User> removePMO(String employeeId) throws InterruptedException, ExecutionException;


}
