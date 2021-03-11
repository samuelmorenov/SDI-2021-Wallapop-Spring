package com.uniovi.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.validators.OfferValidator;

@Controller
public class OffersController {

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private OffersService offersService;

	@Autowired
	private OfferValidator offerValidator;
	

	@RequestMapping(value = "/offer/post")
	public String offer_post_GET(Model model) {
		model.addAttribute("offer", new Offer());
		return "offer/post";
	}

	@RequestMapping(value = "/offer/post", method = RequestMethod.POST)
	public String offer_post_POST(@Validated Offer offer, BindingResult result, Model model) {
		offerValidator.validate(offer, result);
		if (result.hasErrors()) {
			return "offer/post";
		}
		offer.setCreator((User) httpSession.getAttribute("currentlyUser"));
		offer.setDate(new Date());
		offersService.addOffer(offer);
		return "redirect:/offer/own";
	}
	
	@RequestMapping(value = "/offer/all")
	public String offer_all_GET(Model model) {
		//TODO: Añadir lista de todas las ofertas
		return "offer/all";
	}
	
	@RequestMapping(value = "/offer/own")
	public String offer_own_GET(Model model) {
		//TODO: Añadir lista de ofertas propias
		return "offer/own";
	}
	
	@RequestMapping(value = "/offer/purchased")
	public String offer_purchased_GET(Model model) {
		//TODO: Añadir lista de ofertas compradas
		return "offer/purchased";
	}
	
}
