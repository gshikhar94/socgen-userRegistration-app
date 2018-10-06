package com.skillEnza.app.service;

import com.skillEnza.app.entity.User;

public interface UserRegistrationServiceI{

	public User createUser(User userData);
	
	public Iterable<User> getRegisteredUsers();
	
	public User getUserByName(String name) ;
}
