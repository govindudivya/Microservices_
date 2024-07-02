package com.divya.ecommerce.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


import com.divya.ecommerce.customer.CustomerClient;
import com.divya.ecommerce.exception.BusinessException;
import com.divya.ecommerce.orderline.OrderLineRequest;
import com.divya.ecommerce.orderline.OrderLineService;
import com.divya.ecommerce.payment.PaymentClient;
import com.divya.ecommerce.payment.PaymentRequest;
import com.divya.ecommerce.product.ProductClient;
import com.divya.ecommerce.product.PurchaseRequest;
import com.divya.ecommerce.kafka.OrderConfirmation;
import com.divya.ecommerce.kafka.OrderProducer;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository repo;
	private final CustomerClient costumerClient;
	private final ProductClient productClient;
	private final OrderMapper mapper;
	private final OrderLineService orderLineService;
	
	private final OrderProducer OrderProducer;
	private final PaymentClient paymentClient;

	public Integer createOrder(OrderRequest request) {
		// TODO Auto-generated method stub
		
		// check the customer using openFeign
		var customer = this.costumerClient.findCustomerById(request.customerId())
					.orElseThrow(()-> new BusinessException("customer %s id not present+"));
		
		// purchase the product - using RestTemplate (OpenFeign also can be used)
		
		var purchaseProducts = this.productClient.purchaseProducts(request.products()); 
		
		var order = this.repo.save(mapper.toOrder(request)); // persisting the order.
		
		//persist the orderlines
		
		for(PurchaseRequest purchaseRequest : request.products())
		{
			orderLineService.saveOrderLine(
					new OrderLineRequest(
							null,
							order.getId(),
							purchaseRequest.productId(),
							purchaseRequest.quantity()
							
							)
					);
		}
		
		// to do payment process
		var paymentRequest = new PaymentRequest(
				request.amount(),
				request.paymentMethod(),
				order.getId(),
				order.getReference(),
				customer
				
				);
		paymentClient.requestOrderPayment(paymentRequest);
		
		// send the order notification - kafka 
		OrderProducer.sendOrderConfirmation(
				new OrderConfirmation(
						request.reference(),
						request.amount(),
						request.paymentMethod(),
						customer,
						purchaseProducts
						
						)

				);
		
		return order.getId();
	}
	
	public List<OrderResponse> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll()
				.stream()
				.map(mapper::toOrderResponse)
				.collect(Collectors.toList());
		
		
	}

	public OrderResponse findById(Integer orderId) {
		// TODO Auto-generated method stub
		return repo.findById(orderId)
				.map(mapper::toOrderResponse)
				.orElseThrow(() -> new EntityNotFoundException(String.format("no order-id found:%d", orderId)));
	}

}
