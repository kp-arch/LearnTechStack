package com.learn.microservice.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.learn.microservice.entity.User;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<User>();

	private static long count = 0;
	static {
		users.add(new User(++count, "RAM", LocalDate.now().minusYears(30)));
		users.add(new User(++count, "SHYAM", LocalDate.now().minusYears(35)));
		users.add(new User(++count, "MOHAN", LocalDate.now().minusYears(25)));
	}

	public List<User> findAll() {
		return users;
	}

	public User find(long id) {
		return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
	}

	public User createUser(User user) {
		user.setId(++count);
		users.add(user);
		return user;
	}
}
