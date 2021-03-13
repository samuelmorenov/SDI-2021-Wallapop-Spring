package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;

@Service
public class OffersService {
	@Autowired
	private OffersRepository offersRepository;

	@PostConstruct
	public void init() {
	}

	public void addOffer(Offer offer) {
		offersRepository.save(offer);
	}

	public Page<Offer> getAllOffersByTitle(Pageable pageable, String searchText) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		searchText = "%" + searchText + "%";
		offers = offersRepository.searchByTitle(pageable, searchText);
		return offers;
	}

	public Page<Offer> getAllOffers(Pageable pageable) {
		return offersRepository.findAll(pageable);
	}

	public List<Offer> getOwnOffers(User activeUser) {
		return offersRepository.findOwnOffers(activeUser);
	}

	public List<Offer> getPurchasedOffers(User activeUser) {
		return offersRepository.findPurchasedOffers(activeUser);
	}

	public Offer getOfferById(Long offerId) {
		return offersRepository.findById(offerId).get();
	}

	public void delete(Offer offer) {
		offersRepository.delete(offer);
	}
}
