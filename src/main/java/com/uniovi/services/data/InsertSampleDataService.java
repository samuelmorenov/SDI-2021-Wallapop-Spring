package com.uniovi.services.data;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;

// DONE: Para crear la base de datos de nuevo descomentar esta clase
@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;

	@Autowired
	private OffersService offersService;

	@PostConstruct
	public void init() {

		initUsers();

		initOffer();

	}

	private void initOffer() {
		Offer offer1 = new Offer("Oferta 1", "Descipcion de la oferta 1", 11.0);
		offer1.setCreator(usersService.getUserByEmail((UserList.usuarios(0).email)));
		offer1.setBuyer(usersService.getUserByEmail((UserList.usuarios(5).email)));
		Offer offer2 = new Offer("Oferta 2", "Descipcion de la oferta 2", 12.0);
		offer2.setCreator(usersService.getUserByEmail((UserList.usuarios(5).email)));
		offer2.setBuyer(usersService.getUserByEmail((UserList.usuarios(0).email)));
		Offer offer3 = new Offer("Oferta 3", "Descipcion de la oferta 3", 13.0);
		offer3.setCreator(usersService.getUserByEmail((UserList.usuarios(0).email)));
		Offer offer4 = new Offer("Oferta 4", "Descipcion de la oferta 4", 14.0);
		offer4.setCreator(usersService.getUserByEmail((UserList.usuarios(5).email)));
		Offer offer5 = new Offer("Oferta 5", "Descipcion de la oferta 5", 86.0);
		offer5.setCreator(usersService.getUserByEmail((UserList.usuarios(5).email)));
		Offer offer6 = new Offer("Oferta 6", "Descipcion de la oferta 6", 100.0);
		offer6.setCreator(usersService.getUserByEmail((UserList.usuarios(5).email)));

		offersService.addOffer(offer1);
		offersService.addOffer(offer2);
		offersService.addOffer(offer3);
		offersService.addOffer(offer4);
		offersService.addOffer(offer5);
		offersService.addOffer(offer6);
	}

	private void initUsers() {
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