package com.viktor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.management.AttributeChangeNotification;
import javax.management.MalformedObjectNameException;
import javax.management.Notification;
import javax.management.ObjectName;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viktor.beans.User;
import com.viktor.exception.UserNotFoundException;
import com.viktor.listener.ResourceListener;
import com.viktor.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Viktor Angelutsa
 *
 */
@RestController
@RequestMapping("users")
@Api(value = "Users microservice", description = "This API has a CRUD for users")
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	private List<ResourceListener> listeners = new ArrayList<ResourceListener>();

	/**
	 * Adds a ResourceListener
	 * @param listener
	 */
    public void addListener(ResourceListener listener) {
        listeners.add(listener);
    }
    
	/**
	 * Gets a User by ID
	 * 
	 * @param String
	 *            id
	 * @return User
	 **/
	@GetMapping("/user/{id}")
	@ApiOperation(value = "Finds an user", notes = "Return a user by Id")
	public User getUser(@PathVariable String id) {
		User user = null;
		try {
			user = userService.findUser(Long.parseLong(id));
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		logger.info("getUser: " + user.toString());
		return user;
	}

	/**
	 * Finds all the Users
	 * 
	 * @return Iterable<User>
	 */
	@GetMapping
	@ApiOperation(value = "Finds all users", notes = "Returns all users")
	public Iterable<User> getAll() {
		logger.info("Getting all Users");
		return userService.findAll();
	}

	/**
	 * Finds all the Users by country
	 * 
	 * @return Iterable<User>
	 */
	@GetMapping("/country/{country}")
	@ApiOperation(value = "Finds all users by country", notes = "Returns all users by country")
	public List<User> getAllByCountry(@PathVariable String country) {
		logger.info("Getting all Users by country:" + country);
		return userService.findAllByCountry(country);
	}

	/**
	 * Saves a User (new or modified one)
	 * 
	 * @param user
	 * @return ResponseEntity<User>
	 */
	@PostMapping("/save")
	@ApiOperation(value = "Saves an user", notes = "Saves a new user")
	public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
		ResourceListener resourceListener = new ResourceListener();
		this.addListener(resourceListener);
		// Notify everybody that may be interested.
		ObjectName name = null;
		try {
			name = new ObjectName("Users:type=JMX,name=User");
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		}
		//There is place for new feature here depending upon requirements. Notifications can be managed.
		Notification notification = new Notification(AttributeChangeNotification.ATTRIBUTE_CHANGE, user, 0, "The User has changed.");
		
		notification.setUserData(user);
		for (ResourceListener rl : listeners){
            rl.handleNotification(notification, name);
        }
		logger.info("Saves user" + user.toString());
		return userService.saveUser(user);
	}

	/**
	 * Deletes a User
	 * 
	 * @param String
	 *            id
	 */
	@DeleteMapping("/user/{id}")
	@ApiOperation(value = "Delete an user", notes = "Delete a user by Id")
	public void deleteUser(@PathVariable String id) {

		logger.info("Delete user with the id=" + id);
		userService.deleteUser(Long.parseLong(id));
	}
}
