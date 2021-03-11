package com.uniovi.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

@Controller
public class UsersController {

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private UsersService usersService;

	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public String user_delete_id(Model model) {
		
		//TODO: Obtener la lista de marcados y borrarlos
		
		return "redirect:/user/list";
	}

	@RequestMapping("/user/list")
	public String user_list(Model model, Principal principal) {

		// Set active user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		httpSession.setAttribute("currentlyUser", activeUser);
		
		model.addAttribute("usersList", usersService.getUsers());
		return "user/list";
	}
	
	@RequestMapping("/user/profile")
	public String index() {
		return "user/profile";
	}
}