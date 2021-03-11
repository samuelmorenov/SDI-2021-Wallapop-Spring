package com.uniovi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Offer;

@Controller
public class OffersController {

//	@Autowired
//	private HttpSession httpSession;
	
//	@Autowired
//	private OffersService offersService;
	

	@RequestMapping(value = "/offer/post")
	public String offer_post_GET(Model model) {
		//TODO: No se que se añade aqui xD
		return "offer/post";
	}

	@RequestMapping(value = "/offer/post", method = RequestMethod.POST)
	public String offer_post_POST(@ModelAttribute Offer offer) {
		//offersService.addOffer(offer);
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
