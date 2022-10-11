package com.stacksimplify.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

//Controller
@RestController
public class UserController {
	
	//Autowire user servicse
	@Autowired
	private UserService userService;
	
	//getAllusers Method 
	
	@GetMapping("/users")
	public List <User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	//Create user
	//@RequestBody
	//@PostMapping
	
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		try {
		return userService.createUser(user);
		} catch(UserExistException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());	
					}
	}
	
	//getUserById
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id){//so it is looking for a long id? they guy
		//didnt explain this very well
		try {
			return userService.getUserById(id);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
	}
	
	//updateUserById
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		
		try {
			return userService.updateUserById(id, user);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
		
	}
	
	
	//deleteUserById
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
	
	//getUserByUsername
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
