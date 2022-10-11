package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.stacksimplify.restservices.entities.User;
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

	// reate User Method
	public User createUser(User user) {
		return userRepository.save(user);
	}

	// getUserbyId
	@GetMapping
	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}

	// updateUserById
	@PutMapping
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);

	}

	// deleteUserById
	@DeleteMapping
	public void deleteUserById(Long id) {
		if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);

		}

	}

	// getUserByUsername
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
