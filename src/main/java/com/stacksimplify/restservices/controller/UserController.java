package com.stacksimplify.restservices.controller;

import org.springframework.http.HttpHeaders;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistException;
import com.stacksimplify.restservices.exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

//Controller
@RestController
@Validated 
@RequestMapping(value ="/users")
public class UserController {
	
	//Autowire user servicse
	@Autowired
	private UserService userService;
	
	//getAllusers Method 
	
	@GetMapping
	public List <User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	//Create user
	//@RequestBody
	//@PostMapping
	
	
	@PostMapping
	public ResponseEntity<Void>  createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
		 userService.createUser(user);
		 HttpHeaders headers = new HttpHeaders();
		 headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
		 return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		 
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
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") @Min(1) Long id, @RequestBody User user) {
		
		try {
			return userService.updateUserById(id, user);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
		
	}
	
	
	//deleteUserById
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
	
	//getUserByUsername
	@GetMapping("/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
		User user = userService.getUserByUsername(username);
		if(user == null)
			throw new UserNameNotFoundException("Username: '" + username + "' User not found" );
		return user;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
