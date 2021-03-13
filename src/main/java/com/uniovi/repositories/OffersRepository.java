package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Offer;

public interface OffersRepository extends CrudRepository<Offer, Long> {

	@Query("SELECT r FROM Offer r WHERE (LOWER(r.title) LIKE LOWER(?1))")
	Page<Offer> searchByTitle(Pageable pageable, String searchText);

	Page<Offer> findAll(Pageable pageable);

}
