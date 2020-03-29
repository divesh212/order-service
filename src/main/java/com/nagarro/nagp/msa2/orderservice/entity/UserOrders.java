package com.nagarro.nagp.msa2.orderservice.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrders {
	private int id;
	private List<Order> orders;
}
