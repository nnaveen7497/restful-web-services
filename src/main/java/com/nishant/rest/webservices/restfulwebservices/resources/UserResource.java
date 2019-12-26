package com.nishant.rest.webservices.restfulwebservices.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nishant.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.nishant.rest.webservices.restfulwebservices.model.User;
import com.nishant.rest.webservices.restfulwebservices.repository.UserRepository;

@RestController
public class UserResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{userId}")
	public Resource<User> retrieveUser(@PathVariable int userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new UserNotFoundException("id =" + userId);
		}
		
		Resource<User> model = new Resource<>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkTo.withRel("all-users"));
		
		return model;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		User user = userRepository.deleteById(userId);
		if (user == null) {
			throw new UserNotFoundException("id =" + userId);
		}
	}
}
