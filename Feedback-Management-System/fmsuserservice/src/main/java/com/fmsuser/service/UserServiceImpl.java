package com.fmsuser.service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.stereotype.Service;

import com.fmsuser.excception.UserNotFoundException;
import com.fmsuser.model.User;
import com.fmsuser.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public Mono<User> findByUserIdAndPassword(String username, String password)
			throws UserNotFoundException, InterruptedException, ExecutionException {

		try {

			Mono<User> user = userRepo.findByUsernameAndPassword(username, password);
			/*
			 * if(user.toFuture().get() == null) { throw new
			 * UserNotFoundException("User doesnt exists"); }
			 */
			System.out.println(user.toFuture().get().toString());
			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	@Override
	public Mono<User> findByUserName(String username)
			throws UserNotFoundException, InterruptedException, ExecutionException {
		Mono<User> user = userRepo.findByUsername(username);
		if (user.toFuture().get() == null) {
			throw new UserNotFoundException("Employee doesnt exists");
		}
		return user;
	}

	@Override
	public Flux<User> getPmoEmployees() {
		System.out.println("From userServiceImpl line no-56");
		Flux<User> users = userRepo.findByRoletype("pmo");
		System.out.println("--> " + users.subscribe(System.out::println));
		return users;
	}

	@Override
	public Mono<User> addPMO(String employeeId) throws IOException {
		User user = new User();
		System.out.println("employeeId - " + employeeId);
		// user=userRepo.findByEmployee(employeeId).toFuture().get();
		user.setUsername(employeeId);
		user.setPassword(employeeId);
		user.setFirstname(employeeId);
		user.setLastname(employeeId);
		user.setRole(employeeId);
		System.out.println(user);
		return userRepo.save(user);

	}

	@Override
	public Mono<User> removePMO(String employeeId) throws InterruptedException, ExecutionException {
		User user = userRepo.findByEmployee(employeeId).toFuture().get();
		user.setRole("");
		return userRepo.save(user);

	}
}
