package com.uniovi.services;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.repositories.OffersRepository;

public class OffersService {
	@Autowired
	private OffersRepository offersRepository;

	@PostConstruct
	public void init() {
	}

	@Autowired
	private HttpSession httpSession;
}
