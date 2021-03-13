package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

public interface OffersRepository extends CrudRepository<Offer, Long> {

	@Query("SELECT o FROM Offer o WHERE (LOWER(o.title) LIKE LOWER(?1))")
	Page<Offer> searchByTitle(Pageable pageable, String searchText);

	Page<Offer> findAll(Pageable pageable);

	@Query("SELECT o FROM Offer o WHERE o.creator = ?1")
	List<Offer> findOwnOffers(User activeUser);

	@Query("SELECT o FROM Offer o WHERE o.buyer = ?1")
	List<Offer> findPurchasedOffers(User activeUser);

}
