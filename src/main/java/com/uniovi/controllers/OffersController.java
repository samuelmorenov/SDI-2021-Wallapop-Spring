package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.validators.OfferValidator;

@Controller
public class OffersController extends UtilsController {

	final static Logger LOG = LoggerFactory.getLogger(OffersController.class);

	@Autowired
	private OffersService offersService;

	@Autowired
	private OfferValidator offerValidator;

	@RequestMapping(value = "/offer/post", method = RequestMethod.GET)
	public String offer_post_GET(Model model) {
		LOG.info("Accediendo a /offer/post por el metodo GET");
		// Set active user
		this.setActiveUser(model);

		model.addAttribute("offer", new Offer());
		LOG.info("Añadido nueva oferta al modelo");
		return "offer/post";
	}

	@RequestMapping(value = "/offer/post", method = RequestMethod.POST)
	public String offer_post_POST(@Validated Offer offer, BindingResult result, Model model) {
		LOG.info("Accediendo a /offer/post por el metodo GET");
		// Set active user
		User activeUser = this.setActiveUser(model);

		offerValidator.validate(offer, result);
		if (result.hasErrors()) {
			LOG.error("La oferta proporcionada en el formulario no es valida");
			return "offer/post";
		}
		offersService.addOffer(offer, activeUser);
		LOG.info("Se ha añadido la oferta (" + offer.getTitle() + ") al sistema");
		return "redirect:/offer/own";
	}

	@RequestMapping(value = "/offer/all")
	public String offer_all_GET(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {
		LOG.info("Accediendo a /offer/all por el metodo GET");

		// Set active user
		User activeUser = this.setActiveUser(model);

		// Paginacion y busqueda
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.getAllOffersByTitle(pageable, searchText);
			LOG.info("Llamada al servicio de obtener todas las ofertas con busqueda");
		} else {
			offers = offersService.getAllOffers(pageable);
			LOG.info("Llamada al servicio de obtener todas las ofertas sin busqueda");
		}

		// Añadir elementos al modelo

		model.addAttribute("offersList", offers.getContent());
		model.addAttribute("page", offers);
		LOG.info("Añadido la lista de ofertas al Model");

		List<Offer> offersPurchased = offersService.getPurchasedOffers(activeUser);
		model.addAttribute("offersPurchased", offersPurchased);
		LOG.info("Añadido la lista de ofertas compradas al Model del usuario: " + activeUser.getEmail());

		List<Offer> offersOwn = offersService.getOwnOffers(activeUser);
		model.addAttribute("offersOwn", offersOwn);
		LOG.info("Añadido la lista de creadas compradas al Model del usuario: " + activeUser.getEmail());

		return "offer/all";
	}

	@RequestMapping(value = "/offer/own")
	public String offer_own_GET(Model model) {
		LOG.info("Accediendo a /offer/own por el metodo GET");
		// Set active user
		User activeUser = this.setActiveUser(model);

		model.addAttribute("offersList", offersService.getOwnOffers(activeUser));
		LOG.info("Añadido la lista de creadas compradas al Model del usuario: " + activeUser.getEmail());
		return "offer/own";
	}

	@RequestMapping(value = "/offer/purchased")
	public String offer_purchased_GET(Model model) {
		LOG.info("Accediendo a /offer/purchased por el metodo GET");
		// Set active user
		User activeUser = this.setActiveUser(model);

		model.addAttribute("offersList", offersService.getPurchasedOffers(activeUser));
		LOG.info("Añadido la lista de ofertas compradas al Model del usuario: " + activeUser.getEmail());
		return "offer/purchased";
	}

	@RequestMapping(value = "/offer/delete", method = RequestMethod.POST)
	public String offer_delete_POST(@RequestParam Long OfferId, Model model, Principal principal) {
		LOG.info("Accediendo a /offer/delete por el metodo POST");
		// Set active user
		User activeUser = this.setActiveUser(model);

		boolean borradoCorrecto = offersService.delete(OfferId, activeUser);

		if (!borradoCorrecto) {
			return "redirect:/offer/own";
		}

		return "offer/own";
	}

	@RequestMapping(value = "/offer/buy/{id}", method = RequestMethod.POST)
	public String offer_buy_POST(@PathVariable Long id, Model model, Principal principal) {
		LOG.info("Accediendo a /offer/buy/" + id + " por el metodo POST");
		// Set active user
		User activeUser = this.setActiveUser(model);

		Offer offer = offersService.getOfferById(id);
		if (offer == null) {
			LOG.error("Se ha intentado comprar una oferta que no exite: " + id);
			return "redirect:/offer/all";
		}

		boolean errores = !offersService.buy(activeUser, offer);

		if (errores) {
			LOG.error("Hay errores en la oferta y no se ha podido comprar: " + id);
			return "redirect:/offer/buy/error";
		}

		LOG.info("Se ha comprado con exito la oferta " + id + " por el usurio: " + activeUser.getEmail());
		return "redirect:/offer/purchased";

	}

	@RequestMapping(value = "/offer/buy/error", method = RequestMethod.GET)
	public String offer_buy_error_GET(Model model) {
		LOG.info("Accediendo a /offer/buy/error por el metodo POST");
		// Set active user
		this.setActiveUser(model);

		return "errors/buy";

	}

}
