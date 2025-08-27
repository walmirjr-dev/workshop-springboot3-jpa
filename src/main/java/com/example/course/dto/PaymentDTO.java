package com.example.course.dto;

import java.time.Instant;

import com.example.course.entities.Payment;

public class PaymentDTO {

	private Long id;
	private Instant moment;

	private Long orderId;

	public PaymentDTO() {
	}

	public PaymentDTO(Payment entity) {
		this.id = entity.getId();
		this.moment = entity.getMoment();
		this.orderId = entity.getOrder().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



}
