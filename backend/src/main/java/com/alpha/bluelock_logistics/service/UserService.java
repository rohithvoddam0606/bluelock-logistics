package com.alpha.bluelock_logistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.bluelock_logistics.dto.ResponseStructure;
import com.alpha.bluelock_logistics.entity.User;
import com.alpha.bluelock_logistics.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public ResponseStructure<User> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		
		// Check if email already exists
		User existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser != null) {
			structure.setStatuscode(400);
			structure.setMessage("Email already exists");
			structure.setData(null);
			return structure;
		}
		
		structure.setStatuscode(201);
		structure.setMessage("User saved successfully");
		structure.setData(userRepository.save(user));
		return structure;
	}

	public ResponseStructure<User> findUserById(int id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		User user = userRepository.findById(id).orElse(null);
		if (user != null) {
			structure.setStatuscode(200);
			structure.setMessage("User found");
			structure.setData(user);
		} else {
			structure.setStatuscode(404);
			structure.setMessage("User not found");
		}
		return structure;
	}

	public ResponseStructure<List<User>> findAllUsers() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setStatuscode(200);
		structure.setMessage("All users retrieved");
		structure.setData(userRepository.findAll());
		return structure;
	}

	public ResponseStructure<User> updateUser(int id, User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		User existingUser = userRepository.findById(id).orElse(null);
		if (existingUser != null) {
			user.setId(id);
			structure.setStatuscode(200);
			structure.setMessage("User updated successfully");
			structure.setData(userRepository.save(user));
		} else {
			structure.setStatuscode(404);
			structure.setMessage("User not found");
		}
		return structure;
	}

	public ResponseStructure<String> deleteUser(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			structure.setStatuscode(200);
			structure.setMessage("User deleted successfully");
			structure.setData("User with id " + id + " deleted");
		} else {
			structure.setStatuscode(404);
			structure.setMessage("User not found");
		}
		return structure;
	}

	public ResponseStructure<User> login(String email, String password) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		User user = userRepository.findByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			structure.setStatuscode(200);
			structure.setMessage("Login successful");
			structure.setData(user);
		} else {
			structure.setStatuscode(401);
			structure.setMessage("Invalid email or password");
		}
		return structure;
	}
}
