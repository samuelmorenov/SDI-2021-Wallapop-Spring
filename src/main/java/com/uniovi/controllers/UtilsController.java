package com.uniovi.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

@Controller
public class UtilsController {

	final static Logger logger = LoggerFactory.getLogger(UtilsController.class);

	@Autowired
	protected HttpSession httpSession;

	@Autowired
	protected UsersService usersService;

	protected User setActiveUser(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		httpSession.setAttribute("currentlyUser", activeUser);
		model.addAttribute("currentlyUser", activeUser);
		return activeUser;
	}
}
