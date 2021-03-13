package com.uniovi.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

public class UtilsController {
	@Autowired
	protected HttpSession httpSession;

	@Autowired
	protected UsersService usersService;

	protected void setActiveUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		httpSession.setAttribute("currentlyUser", activeUser);
	}
}
