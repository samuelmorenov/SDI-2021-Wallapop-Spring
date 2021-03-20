package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

@Service
public class UsersService {

	final static Logger LOG = LoggerFactory.getLogger(UsersService.class);

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void init() {
	}

	@Deprecated
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}

	@Deprecated
	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	@Deprecated
	public void update(User user) {
		usersRepository.save(user);
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}

	public List<User> getUsersButOne(User activeUser) {
		return usersRepository.findAllButOne(activeUser);
	}

	public void deleteUsers(String[] emails) {
		for (int i = 0; i < emails.length; i++) {
			User user = getUserByEmail(emails[i]);
			if (user == null) {
				LOG.error("Se ha intentado borrar un usuario que no exite: " + emails[i]);
				continue;
			}
			String email = user.getEmail();
			deleteUser(user.getId());
			LOG.info("Se ha eliminado el usuario " + email);
		}

	}

}
