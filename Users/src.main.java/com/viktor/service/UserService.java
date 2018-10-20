package com.viktor.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.viktor.beans.User;

/**
 * @author Viktor Angelutsa
 *
 */
public interface UserService {

	/**
	 * Finds all users
	 * 
	 * @return Iterable<User
	 */
	Iterable<User> findAll();

	/**
	 * Finds all users by country
	 * 
	 * @return List<User>
	 */
	List<User> findAllByCountry(String country);

	
	/**
	 * Finds a user
	 * 
	 * @param id
	 * @return user
	 */
	User findUser(long id);

	/**
	 * Saves a user
	 * 
	 * @param user
	 * @return ResponseEntity<User>
	 */
	ResponseEntity<User> saveUser(User user);

	/**
	 * Deletes a user
	 * 
	 * @param id
	 */
	void deleteUser(long id);
}
