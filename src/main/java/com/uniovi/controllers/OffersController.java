package com.uniovi.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.validators.OfferValidator;

@Controller
public class OffersController extends UtilsController {

	@Autowired
	private OffersService offersService;

	@Autowired
	private OfferValidator offerValidator;

	@RequestMapping(value = "/offer/post", method = RequestMethod.GET)
	public String offer_post_GET(Model model) {
		// Set active user
		this.setActiveUser(model);
		model.addAttribute("offer", new Offer());
		return "offer/post";
	}

	@RequestMapping(value = "/offer/post", method = RequestMethod.POST)
	public String offer_post_POST(@Validated Offer offer, BindingResult result, Model model) {
		offerValidator.validate(offer, result);
		if (result.hasErrors()) {
			// Set active user
			this.setActiveUser(model);
			model.addAttribute("offer", new Offer());
			return "offer/post";
		}
		offer.setCreator((User) httpSession.getAttribute("currentlyUser"));
		offer.setDate(new Date());
		offersService.addOffer(offer);
		return "redirect:/offer/own";
	}

	@RequestMapping(value = "/offer/all")
	public String offer_all_GET(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required = false) String searchText) {

		// Set active user
		User activeUser = this.setActiveUser(model);

		// Paginacion y busqueda
		// TODO: Mirar como hacer que se guarde la busqueda y la paginacion entre
		// paginas
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.getAllOffersByTitle(pageable, searchText);
		} else {
			offers = offersService.getAllOffers(pageable);
		}

		// Añadir elementos al modelo

		model.addAttribute("offersList", offers.getContent());
		model.addAttribute("page", offers);

		// TODO: Mirar como internacionalizar los botones
		List<Offer> offersPurchased = offersService.getPurchasedOffers(activeUser);
		model.addAttribute("offersPurchased", offersPurchased);

		List<Offer> offersOwn = offersService.getOwnOffers(activeUser);
		model.addAttribute("offersOwn", offersOwn);

		return "offer/all";
	}

	@RequestMapping(value = "/offer/own")
	public String offer_own_GET(Model model) {
		// Set active user
		User activeUser = this.setActiveUser(model);

		model.addAttribute("offersList", offersService.getOwnOffers(activeUser));
		return "offer/own";
	}

	@RequestMapping(value = "/offer/purchased")
	public String offer_purchased_GET(Model model) {
		// Set active user
		User activeUser = this.setActiveUser(model);

		model.addAttribute("offersList", offersService.getPurchasedOffers(activeUser));
		return "offer/purchased";
	}

	@RequestMapping(value = "/offer/delete", method = RequestMethod.POST)
	public String offer_delete_POST(@RequestParam Long OfferId, Model model, Principal principal) {
		// Set active user
		this.setActiveUser(model);

		Offer offer = offersService.getOfferById(OfferId);
		offersService.delete(offer);

		return "offer/own";
	}

	@RequestMapping(value = "/offer/buy", method = RequestMethod.POST)
	public String offer_buy_POST(@RequestParam Long OfferId, Model model, Principal principal) {
		// Set active user
		User activeUser = this.setActiveUser(model);

		Offer offer = offersService.getOfferById(OfferId);

		boolean correcto = offersService.buy(activeUser, offer);

		// Set active user
		this.setActiveUser(model);
		model.addAttribute("offer", new Offer());

		if (!correcto) {
			return "offer/all";
		}

		return "redirect:/offer/purchased";

	}

}
