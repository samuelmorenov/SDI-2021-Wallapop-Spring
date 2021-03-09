package com.uniovi.services.data;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

// DONE: Para crear la base de datos de nuevo descomentar esta clase
@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;

	@PostConstruct
	public void init() {

		for (int i = 0; i < UserList.maxUser; i++) {
			User user = new User(UserList.usuarios(i).email, UserList.usuarios(i).name, UserList.usuarios(i).lastName);
			user.setPassword(UserList.usuarios(i).password);
			user.setRole(UserList.usuarios(i).role);
			usersService.addUser(user);
		}

		User user = new User(UserList.admin.email, UserList.admin.name, UserList.admin.lastName);
		user.setPassword(UserList.admin.password);
		user.setRole(UserList.admin.role);
		usersService.addUser(user);

	}
}