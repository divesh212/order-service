package com.nagarro.nagp.msa2.orderservice.service;

import java.util.List;

import com.nagarro.nagp.msa2.orderservice.entity.Order;

public interface OrderService {
	List<Order> getOrdersByUserId(int id);
}
