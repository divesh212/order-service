package com.nagarro.nagp.msa2.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	private int orderId;
	private double orderAmount;
	private String orderDate;
}
