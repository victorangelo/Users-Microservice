package com.viktor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.viktor.beans.User;

/**
 * @author Viktor Angelutsa
 *
 */
@Repository("userRepository")
public interface UserRepository  extends CrudRepository<User, Long> {
	
}
