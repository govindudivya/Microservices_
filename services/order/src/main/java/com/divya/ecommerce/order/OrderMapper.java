package com.divya.ecommerce.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

	public Order toOrder(OrderRequest request) {
		// TODO Auto-generated method stub
		return Order.builder()
				.id(request.id())
				.reference(request.reference())
				.totalAmount(request.amount())
				.customerId(request.customerId())
				.paymentMethod(request.paymentMethod())
				.build();
		
	}
	
	public OrderResponse toOrderResponse(Order order) {
		// TODO Auto-generated method stub
		return new OrderResponse(
				order.getId(),
				order.getReference(),
				order.getTotalAmount(),
				order.getPaymentMethod(),
				order.getCustomerId()
				
				
				);
	}


}
