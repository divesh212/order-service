package com.nagarro.nagp.msa2.orderservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nagp.msa2.orderservice.entity.Order;
import com.nagarro.nagp.msa2.orderservice.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	OrderService orderService;

	@GetMapping("/{id}")
	public List<Order> getOrdersForUser(@PathVariable("id") int id) {
		LOGGER.info("Get Orders for User request received for user id: {}", id);
		return orderService.getOrdersByUserId(id);
	}
}
