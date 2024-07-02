package com.divya.ecommerce.email;

import lombok.Getter;

public enum EmailTemplates {

	
	PAYMENT_CONFIRMATION("payment-confirmation.html","PAYMENT PROCESSED SUCCESFULLY!!"),
	
	ORDER_CONFIRMATION("order-confirmation.html","ORDER CONFIRMATION!!");
	
	@Getter
	private final String template;
	@Getter
	private final String subject;
	
	EmailTemplates(String template,String subject)
	{
		this.template = template;
		this.subject = subject;
	}
	
}
