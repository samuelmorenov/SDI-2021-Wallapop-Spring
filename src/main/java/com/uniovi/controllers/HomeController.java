package com.uniovi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.User;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class HomeController {

	final static Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private SignUpFormValidator signUpFormValidator;

	@RequestMapping("/")
	public String index() {
		return "redirect:/user/profile";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup_GET(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup_POST(@Validated User user, BindingResult result, Model model) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		user.setRole(RolesService.getRoles()[0]);
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:/user/profile";
	}

	@RequestMapping(value = "/login")
	public String login(Model model) {
		return "login";
	}
}