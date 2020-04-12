package com.nagarro.nagp.msa2.orderservice.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nagarro.nagp.msa2.orderservice.entity.Order;
import com.nagarro.nagp.msa2.orderservice.entity.UserOrders;
import com.nagarro.nagp.msa2.orderservice.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	private List<UserOrders> allUserOrders() {
		List<UserOrders> userOrders = new ArrayList<>();
		userOrders.add(
				new UserOrders(1, Arrays.asList(new Order(1, 200, "10-04-2020"), new Order(2, 400, "11-04-2020"))));
		userOrders.add(
				new UserOrders(2, Arrays.asList(new Order(1, 100, "10-04-2020"), new Order(2, 350, "11-04-2020"))));
		userOrders.add(
				new UserOrders(3, Arrays.asList(new Order(1, 150, "10-04-2020"), new Order(2, 250, "11-04-2020"))));
		userOrders.add(
				new UserOrders(4, Arrays.asList(new Order(1, 250, "10-04-2020"), new Order(2, 450, "11-04-2020"))));
		userOrders.add(
				new UserOrders(5, Arrays.asList(new Order(1, 400, "10-04-2020"), new Order(2, 550, "11-04-2020"))));

		return userOrders;
	}

	@Override
	public List<Order> getOrdersByUserId(int id) {
		List<UserOrders> userOrdersList = allUserOrders();
		Optional<UserOrders> userOrders = userOrdersList.stream().filter(o -> o.getId() == id).findFirst();
		if (userOrders.isPresent()) {
			LOGGER.info("Returning orders for user id:{} - {}", id, userOrders.get().getOrders());
			return userOrders.get().getOrders();
		} else {
			LOGGER.info("No orders found for user id: {}", id);
			return new ArrayList<>();
		}
	}
}
