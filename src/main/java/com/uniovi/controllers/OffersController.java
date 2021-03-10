package com.uniovi.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.services.OffersService;

public class OffersController {

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private OffersService offersService;
}
