package com.divya.ecommerce.kafka;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.divya.ecommerce.email.EmailService;
import com.divya.ecommerce.kafka.order.OrderConfirmation;
import com.divya.ecommerce.kafka.payment.PaymentConfirmation;
import com.divya.ecommerce.notification.Notification;
import com.divya.ecommerce.notification.NotificationRepository;
import com.divya.ecommerce.notification.NotificationType;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
	
	private final NotificationRepository notificationRepo;
	private final EmailService emailService;
	
	@KafkaListener(topics = "payment-topic", groupId= "paymentGroup")
	public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
		log.info(String.format("consuming nsg:: %s", paymentConfirmation));
		notificationRepo.save(
				Notification.builder()
				.type(NotificationType.PAYMENT_CONFIRMATION)
				.paymentConfirmation(paymentConfirmation)
				.notificationDate(LocalDateTime.now())
				.build()
				);
		
		emailService.sendPaymentMail(
				paymentConfirmation.customerEmail(),
				paymentConfirmation.customerFirstName(),
				paymentConfirmation.amount(),
				paymentConfirmation.orderReference()
				);
		
	}
	
	
	@KafkaListener(topics = "order-topic",groupId= "orderGroup")
	public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
		log.info(String.format("consuming nsg:: %s", orderConfirmation));
		notificationRepo.save(
				Notification.builder()
				.type(NotificationType.ORDER_CONFIRMATION)
				.orderConfirmation(orderConfirmation)
				.notificationDate(LocalDateTime.now())
				.build()
				);
		
		emailService.sendOrderConfirmationMail(
				orderConfirmation.customer().email(),
				orderConfirmation.customer().firstName(),
				orderConfirmation.totalAmount(),
				orderConfirmation.orderReference(),
				orderConfirmation.products()
				);
		
	}

}
