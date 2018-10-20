/**
 * 
 */
package com.viktor.service.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.viktor.beans.User;
import com.viktor.exception.UserNotFoundException;
import com.viktor.repository.UserRepository;
import com.viktor.service.UserService;

/**
 * @author Viktor Angelutsa
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findAllByCountry(String country) {
		Iterable<User> allUsers = userRepository.findAll();
		List<User> filteredUsers = new ArrayList<User>();
		for(User user : allUsers){
			if(user.getCountry().equals(country)){
				filteredUsers.add(user);
			}
		}
		return filteredUsers;
	}

	@Override
	public User findUser(long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException(id);
		} else {
			return optionalUser.get();
		}
	}

	@Override
	public ResponseEntity<User> saveUser(User user) {
		User returnedUser = userRepository.save(user);
		if (returnedUser == null) {
			throw new UserNotFoundException(user.getId());
		} else {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id")
					.buildAndExpand(returnedUser.getId()).toUri();

			return ResponseEntity.created(location).build();
		}
	}

	@Override
	public void deleteUser(long id) {
		User user = userRepository.findById(id).get();
		if (user == null) {
			throw new UserNotFoundException(id);
		} else {
			userRepository.delete(user);
		}
	}
}
