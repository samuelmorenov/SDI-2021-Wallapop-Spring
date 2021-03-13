package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);

	@Deprecated
	@Query("SELECT r FROM User r WHERE (LOWER(r.email) LIKE LOWER(?1) OR LOWER(r.name) LIKE LOWER(?1) OR LOWER(r.lastName) LIKE LOWER(?1)and r.email <> ?2)")
	Page<User> searchByNameEmailAndLastname(Pageable pageable, String searchText, String email);

	@Deprecated
	@Query("SELECT u FROM User u where u.role <> ?2 and u.email <> ?1")
	Page<User> findOthersUsersWithNoThisRole(Pageable pageable, String email, String role);

}