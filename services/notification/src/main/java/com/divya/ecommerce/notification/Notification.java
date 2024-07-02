package com.divya.ecommerce.notification;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.divya.ecommerce.kafka.order.OrderConfirmation;
import com.divya.ecommerce.kafka.payment.PaymentConfirmation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Document
public class Notification {
	
	@Id
	private String id;
	private NotificationType type;
	private LocalDateTime notificationDate;
	private OrderConfirmation orderConfirmation;
	private PaymentConfirmation paymentConfirmation;

}
