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
public class HomeController extends UtilsController{

	final static Logger LOG = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private SignUpFormValidator signUpFormValidator;

	@RequestMapping("/")
	public String index() {
		LOG.info("Redireccionado de index a /user/profile");
		return "redirect:/user/profile";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup_GET(Model model) {
		LOG.info("Accediendo a /signup por el metodo GET");
		model.addAttribute("user", new User());
		LOG.info("Añadido nuevo usuario al modelo");
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup_POST(@Validated User user, BindingResult result, Model model) {
		LOG.info("Accediendo a /signup por el metodo POST");
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			LOG.error("El usuario proporcionado en el formulario no es valido");
			return "signup";
		}
		user.setRole(RolesService.getRoles()[0]);
		usersService.addUser(user);
		LOG.info("Se ha añadido el usuario al sistema con email: "+user.getEmail());
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		LOG.info("autoLogin para el nuevo usuario con email: "+user.getEmail());
		LOG.info("redireccion a /user/profile");
		return "redirect:/user/profile";
	}

	@RequestMapping(value = "/login")
	public String login(Model model) {
		LOG.info("Accediendo a /login por el metodo GET");
		this.setActiveUser(model);
		return "login";
	}
}