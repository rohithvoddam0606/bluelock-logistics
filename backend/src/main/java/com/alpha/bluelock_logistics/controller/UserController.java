package com.alpha.bluelock_logistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.bluelock_logistics.dto.ResponseStructure;
import com.alpha.bluelock_logistics.entity.User;
import com.alpha.bluelock_logistics.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseStructure<User> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@GetMapping("/{id}")
	public ResponseStructure<User> findUserById(@PathVariable int id) {
		return userService.findUserById(id);
	}

	@GetMapping
	public ResponseStructure<List<User>> findAllUsers() {
		return userService.findAllUsers();
	}

	@PutMapping("/{id}")
	public ResponseStructure<User> updateUser(@PathVariable int id, @RequestBody User user) {
		return userService.updateUser(id, user);
	}

	@DeleteMapping("/{id}")
	public ResponseStructure<String> deleteUser(@PathVariable int id) {
		return userService.deleteUser(id);
	}
}
