package com.divya.ecommerce.payment;

import org.springframework.stereotype.Service;

import com.divya.ecommerce.notification.NotificationProducer;
import com.divya.ecommerce.notification.PaymentNotificationRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	
	private final PaymentRepository repo;
	private final PaymentMapper mapper;
	private final NotificationProducer notificationProducer;

	public Integer createPayment(PaymentRequest request) {
		// TODO Auto-generated method stub
		var payment = repo.save(mapper.toPayment(request));
		notificationProducer.sendNotification(
				new PaymentNotificationRequest(
						
						request.orderReference(),
						request.amount(),
						request.paymentMethod(),
						request.customer().firstName(),
						request.customer().lastName(),
						request.customer().email()
						)
				
				);
		
		return payment.getId();
	}

}
