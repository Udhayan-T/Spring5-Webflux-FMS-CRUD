package com.fmsuser.service;

import java.util.Map;

import com.fmsuser.model.User;

public interface SecurityTokenGenerator {
	
	Map<String,String> generateToken(User user);

}
