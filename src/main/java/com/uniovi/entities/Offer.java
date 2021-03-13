package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Offer {
	public enum OfferStatus {
		CREATED, SOLDOUT
	}

	@Id
	@GeneratedValue
	private long id;

	private String title; // título descriptivo de la oferta
	private String description; // detalle textual de la oferta

	@Temporal(TemporalType.TIMESTAMP)
	private Date date; // fecha de alta de la oferta(Esta fecha puede ser la del sistema)

	private Double price; // cantidad solicitada en euros

	@Enumerated(EnumType.STRING)
	private OfferStatus status; // estado actual de la oferta

	@ManyToOne
	private User creator; // usuario que ha creado la oferta

	@ManyToOne
	private User buyer; // usuario que ha comprado la oferta

	public Offer() {
		this.status = OfferStatus.CREATED;
	}

	public Offer(String title, String description, Double price) {
		this.title = title;
		this.description = description;
		this.price = price;
		this.date = new Date();
		this.status = OfferStatus.CREATED;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public OfferStatus getStatus() {
		return status;
	}

	public void setSoldout() {
		this.status = OfferStatus.SOLDOUT;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

}
