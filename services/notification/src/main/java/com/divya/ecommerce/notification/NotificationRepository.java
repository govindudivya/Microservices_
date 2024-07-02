package com.divya.ecommerce.notification;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.divya.ecommerce.kafka.payment.PaymentConfirmation;

public interface NotificationRepository extends MongoRepository<Notification, String> {

}
