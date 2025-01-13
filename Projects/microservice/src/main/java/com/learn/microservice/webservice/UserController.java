package com.learn.microservice.webservice;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.learn.microservice.dao.UserDaoService;
import com.learn.microservice.entity.User;
import com.learn.microservice.webservice.exception.UserNotExistException;

import jakarta.validation.Valid;

@RequestMapping(path = "/users")
@RestController
public class UserController {

	@Autowired
	UserDaoService service;

	public UserController(UserDaoService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<User>> retrieveAllUsers() {
		return new ResponseEntity<List<User>>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<User> retrieveUser(@PathVariable long id) {

		User user = service.find(id);
		if (null == user) {
			throw new UserNotExistException(String.format("User Not Exist "));
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", params = "version=2")
	public ResponseEntity<User> retrieveUser1(@PathVariable long id) {

		User user = service.find(1);
		if (null == user) {
			throw new UserNotExistException(String.format("User Not Exist "));
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", params = "version=3")
	public ResponseEntity<MappingJacksonValue> retrieveUserFiltering(@PathVariable long id) {

		MappingJacksonValue returnFilteredValues = new MappingJacksonValue(service.find(id));
		
		SimpleBeanPropertyFilter  filter= SimpleBeanPropertyFilter.serializeAllExcept("birthdate");
		
		FilterProvider flProvider = new SimpleFilterProvider().addFilter("IgoreBrithdate", filter);
		
		returnFilteredValues.setFilters(flProvider);

		return new ResponseEntity<MappingJacksonValue>(returnFilteredValues, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		user = service.createUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();

		return new ResponseEntity<>(location, HttpStatus.CREATED);
	}
}
