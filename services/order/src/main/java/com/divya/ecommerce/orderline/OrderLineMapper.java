package com.divya.ecommerce.orderline;

import org.springframework.stereotype.Service;

import com.divya.ecommerce.order.Order;

@Service
public class OrderLineMapper {

	public OrderLine toOrderLine(OrderLineRequest request) {
		// TODO Auto-generated method stub
		return OrderLine.builder()
				.id(request.id())
				.quantity(request.quantity())
				.productId(request.productId())
				.order(
						Order.builder()
						.id(request.orderId())
						.build()
						
						)
				
				.build();
			   
	}
	
	public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
		return new OrderLineResponse(
				orderLine.getId(),
				orderLine.getQuantity()
				);
	}

}
