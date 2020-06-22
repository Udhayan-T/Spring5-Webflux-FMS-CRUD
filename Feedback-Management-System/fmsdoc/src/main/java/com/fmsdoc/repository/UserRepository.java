package com.fmsdoc.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.fmsdoc.model.User;


public interface UserRepository extends ReactiveCrudRepository<User, Long>{
	

}
