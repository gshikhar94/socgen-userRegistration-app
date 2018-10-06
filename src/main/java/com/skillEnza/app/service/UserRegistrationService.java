package com.skillEnza.app.service;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillEnza.app.dao.UserRegistrationDaoI;
import com.skillEnza.app.entity.User;
import com.skillEnza.app.exception.SystemException;

@Service
public class UserRegistrationService implements UserRegistrationServiceI {

	private static final Logger logger = Logger.getLogger(UserRegistrationService.class);

	@Autowired
	private UserRegistrationDaoI registrationdao;

	@PostConstruct
	public void verify() {
		if (logger.isDebugEnabled())
			logger.debug("UserRegistrationDao Implementation injected successfully");
	}

	@Override
	public User createUser(User userData) {
		if (!this.registrationdao.existsByFirstName(userData.getFirstName())) {
			return this.registrationdao.save(userData);

		} else {
			throw new SystemException("User Already Exists");
		}
	}

	@Override
	public Iterable<User> getRegisteredUsers() {
		return this.registrationdao.findAll();
	}

	public User getUserByName(String name) {
		return this.registrationdao.findByFirstName(name);
	}
}
