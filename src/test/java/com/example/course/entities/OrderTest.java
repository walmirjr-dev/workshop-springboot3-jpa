package com.example.course.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.Test;

import com.example.course.entities.enums.OrderStatus;



public class OrderTest {

	@Test
	void setPaymentShouldChangeOrderStatusToPaid() {
		Order order = new Order(1L, Instant.now(), OrderStatus.WAITING_PAYMENT, null);
		Payment payment = new Payment(1L, Instant.now(), null);

		order.setPayment(payment);

		assertEquals(OrderStatus.PAID, order.getOrderStatus());
	}

	@Test
	void getTotalShouldReturnSumOfItems() {
		Order order = new Order(1L, Instant.now(), OrderStatus.WAITING_PAYMENT, null);
		OrderItem item = new OrderItem(null, null, 2, 200.00);

		order.getItems().add(item);

		assertEquals(400, order.getTotal(), 0.001);
	}


}
