package com.fmsdoc.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.fmsdoc.model.EventEmployee;

public interface EventEmployeeRepository extends ReactiveCrudRepository<EventEmployee, Long>{

}
