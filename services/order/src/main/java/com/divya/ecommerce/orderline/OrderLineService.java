package com.divya.ecommerce.orderline;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderLineService {
	
	private final OrderLineRepository repo;
	private final OrderLineMapper mapper;

	public OrderLine saveOrderLine(OrderLineRequest orderLineRequest) {
		
		var order = mapper.toOrderLine(orderLineRequest);
		return repo.save(order);
		
		
	}

	public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		return repo.findAllByOrderId(orderId)
				.stream()
				.map(mapper::toOrderLineResponse)
				.collect(Collectors.toList());
	}
	

}
