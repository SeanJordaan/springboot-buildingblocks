package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

//Service
@Service
public class UserService {

	// Autowire userRepo
	@Autowired
	private UserRepository userRepository;

	// getAllUsers Method
	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// Create User Method
	public User createUser(User user) throws UserExistException {
		//if user excist using username
		User exsistingUser = userRepository.findByUsername(user.getUsername());
		
		//if not then throw UserExcistException
		if(exsistingUser != null) {
			throw new UserExistException("User excist");
		}
		
		return userRepository.save(user);
	}

	// getUserbyId
	@GetMapping
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		
		if (!user.isPresent()) {
			throw new UserNotFoundException("User Not found");
		}
		
		return user;
	}

	// updateUserById
	@PutMapping
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		
		Optional<User> optionalUser = userRepository.findById(id);
		
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User Not found by ID");
		}
		
		user.setId(id);
		return userRepository.save(user);

	}

	// deleteUserById
	@DeleteMapping
	public void deleteUserById(Long id) {
		
		Optional<User> optionalUser = userRepository.findById(id);
		
		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not found by ID");
		}
		
		userRepository.deleteById(id);

	}

	// getUserByUsername
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
