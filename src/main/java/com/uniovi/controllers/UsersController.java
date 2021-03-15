package com.uniovi.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;

@Controller
public class UsersController extends UtilsController {

	final static Logger LOG = LoggerFactory.getLogger(UsersController.class);

	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public String user_delete_id(Model model, @RequestParam(defaultValue = "false") String[] checkbox) {
		LOG.info("Accediendo a /user/delete por el metodo POST");
		// Set active user
		this.setActiveUser(model);
		
		for (int i = 0; i < checkbox.length; i++) {
			User user = usersService.getUserByEmail(checkbox[i]);
			if(user == null) {
				LOG.error("Se ha intentado borrar un usuario que no exite: "+checkbox[i]);
				continue;
			}
			String email = user.getEmail();
			usersService.deleteUser(user.getId());
			LOG.info("Se ha eliminado el usuario "+email);
		}
		return "redirect:/user/list";
	}

	@RequestMapping("/user/list")
	public String user_list(Model model, Principal principal) {
		LOG.info("Accediendo a /user/delete por el metodo GET");
		
		// Set active user
		User activeUser = this.setActiveUser(model);

		model.addAttribute("usersList", usersService.getUsersButOne(activeUser));
		LOG.info("Añadida lista de todos los usuarios al Model menos "+activeUser.getEmail());
		return "user/list";
	}

	@RequestMapping("/user/profile")
	public String index(Model model) {
		LOG.info("Accediendo a /user/profile por el metodo GET");
		// Set active user
		this.setActiveUser(model);

		// TODO Añadir lista de ofertas creadas

		return "user/profile";
	}
}