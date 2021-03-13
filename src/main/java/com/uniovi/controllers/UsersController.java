package com.uniovi.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsersController extends UtilsController {

	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public String user_delete_id(Model model) {
		// TODO: Obtener la lista de marcados y borrarlos
		return "redirect:/user/list";
	}

	@RequestMapping("/user/list")
	public String user_list(Model model, Principal principal) {
		this.setActiveUser(model);
		model.addAttribute("usersList", usersService.getUsers());
		return "user/list";
	}

	@RequestMapping("/user/profile")
	public String index(Model model) {
		this.setActiveUser(model);
		return "user/profile";
	}
}