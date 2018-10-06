package com.skillEnza.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skillEnza.app.entity.User;

@Repository
public interface UserRegistrationDaoI extends CrudRepository<User, Integer> {

	User findByFirstName(String name);

	boolean existsByFirstName(String firstName);

}
