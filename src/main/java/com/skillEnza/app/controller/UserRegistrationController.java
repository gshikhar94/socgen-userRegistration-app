package com.skillEnza.app.controller;

import javax.annotation.PostConstruct;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillEnza.app.entity.User;
import com.skillEnza.app.exception.SystemException;
import com.skillEnza.app.service.UserRegistrationService;

@RestController
public class UserRegistrationController {
	private static final Logger logger = Logger.getLogger(UserRegistrationController.class);
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private UserRegistrationService registrationService;

	@PostConstruct
	public void verify() {
		if (logger.isDebugEnabled())
			logger.debug("UserRegistrationService Implementation injected successfully");
	}

	/**
	 * First checks that the user exists in the database and create the new user
	 * with the userData provided,and returns the user details
	 * 
	 * @param userData
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public User createUser(@RequestBody User userData) {
		if (userData == null) {
			throw new SystemException("User Data is Empty. Exiting!");
		}
		try {
			return this.registrationService.createUser(userData);
		} catch (Exception e) {
			throw new SystemException("User Already Exists");
		}
	}

	/**
	 * Return the Iterable user containing all the users present in the database
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/fetchAllUsers", method = RequestMethod.GET)
	public Iterable<User> fetchAllUsers() {
		return this.registrationService.getRegisteredUsers();
	}

	/**
	 * Returns the userData based on the first name provided
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/getUserByName", method = RequestMethod.GET)
	public User getUserByName(@RequestBody String name) {
		return this.registrationService.getUserByName(name);
	}

	/**
	 * Handles the custom exception and returns the details of the exception to
	 * the UI.
	 * 
	 * @param ex
	 */
	@ExceptionHandler(SystemException.class)
	public ModelAndView handleCustomException(SystemException ex) {

		ModelAndView model = new ModelAndView();
		model.addObject("errMsg", ex.getMessage());

		return model;

	}

}
