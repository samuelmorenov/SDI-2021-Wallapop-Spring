package com.uniovi.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.uniovi.services.OffersService;

@Controller
public class OffersController {

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private OffersService offersService;
}
