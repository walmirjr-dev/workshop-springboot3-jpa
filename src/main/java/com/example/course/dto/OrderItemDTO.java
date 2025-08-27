package com.example.course.dto;

import com.example.course.entities.OrderItem;

public class OrderItemDTO {

	private Long productId;
	private Integer quantity;

	public OrderItemDTO() {
	}

	public OrderItemDTO(OrderItem entity) {
		super();
		this.productId = entity.getProduct().getId();
		this.quantity = entity.getQuantity();
	}

	public OrderItemDTO(long productId, Integer quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
