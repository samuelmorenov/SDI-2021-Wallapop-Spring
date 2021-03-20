package com.uniovi.services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class OffersService {

	final static Logger LOG = LoggerFactory.getLogger(OffersService.class);

	@Autowired
	private OffersRepository offersRepository;

	@Autowired
	private UsersRepository usersRepository;

	@PostConstruct
	public void init() {
	}

	public void addOffer(Offer offer, User activeUser) {
		offer.setCreator(activeUser);
		LOG.info("Se ha establecido el creador de la oferta (" + offer.getTitle() + ") a " + activeUser.getEmail());
		addOffer(offer);
	}

	public void addOffer(Offer offer) {
		Date now = new Date();
		offer.setDate(now);
		LOG.info("Se ha establecido la fecha de la oferta (" + offer.getTitle() + ") a " + now.toString());
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

	public boolean delete(Long offerId, User activeUser) {

		Offer offer = getOfferById(offerId);
		if (offer == null) {
			LOG.error("Se ha intentado borrar una oferta que no exite: " + offerId);
			return false;
		}

		if (activeUser.getId() != offer.getCreator().getId()) {
			LOG.error("Se ha intentado borrar una oferta (id = " + offerId + " que no es del usuario: "
					+ activeUser.getEmail());
			return false;
		}

		offersRepository.delete(offer);

		LOG.info("Eliminada offerta con id: " + offerId);
		return true;
	}

	public boolean buy(User activeUser, Offer offer) {

		if (activeUser.getMoney() < offer.getPrice()) {
			return false;
		}
		if (offer.getStatus().equals(Offer.OfferStatus.SOLDOUT)) {
			return false;
		}
		if (activeUser.getId() == offer.getCreator().getId()) {
			return false;
		}
		if (offer.getBuyer() != null) {
			return false;
		}

		offer.setSoldout();
		offer.setBuyer(activeUser);
		activeUser.setMoney(activeUser.getMoney() - offer.getPrice());
		activeUser.getBought().add(offer);

		offersRepository.save(offer);
		usersRepository.save(activeUser);

		return true;

	}
}
