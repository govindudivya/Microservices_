package com.divya.ecommerce.email;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.divya.ecommerce.kafka.order.Product;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

	//@Autowired
	private final JavaMailSender mailSender;
	private final SpringTemplateEngine templateEngine;
	
	@Async
	public void sendPaymentMail(
			String  destMail,
			String  custName,
			BigDecimal amount,
			String orderRef
			) throws MessagingException
	{
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = 
				new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED,
						StandardCharsets.UTF_8.name());
		
		mimeMessageHelper.setFrom("test@example.com");
		final String templateName = EmailTemplates.PAYMENT_CONFIRMATION.getTemplate();
		Map<String, Object> variables = new HashMap<>();
		variables.put("custName",custName);
		variables.put("amount", amount);
		variables.put("reference",orderRef);
		
		Context context = new Context();
		context.setVariables(variables);
		mimeMessageHelper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());
		try {
			
			String htmlTemplate = templateEngine.process(templateName, context);
			mimeMessageHelper.setText(htmlTemplate,true);
			mimeMessageHelper.setTo(destMail);
			mailSender.send(mimeMessage);
			log.info(String.format("MAIL SENT TO %S", destMail));
		}
		catch(MessagingException e){
			log.warn("WARNING WHILE SENDING MAIL TOOO-  ",destMail);
		}
		
	}
	
	@Async
	public void sendOrderConfirmationMail(
			String  destMail,
			String  custName,
			BigDecimal amount,
			String orderRef,
			List<Product> products
			) throws MessagingException
	{
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = 
				new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED,
						StandardCharsets.UTF_8.name());
		
		mimeMessageHelper.setFrom("test@example.com");
		final String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplate();
		Map<String, Object> variables = new HashMap<>();
		variables.put("custName",custName);
		variables.put("Totalamount", amount);
		variables.put("reference",orderRef);
		variables.put("products",products);
		Context context = new Context();
		context.setVariables(variables);
		mimeMessageHelper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());
		try {
			
			String htmlTemplate = templateEngine.process(templateName, context);
			mimeMessageHelper.setText(htmlTemplate,true);
			mimeMessageHelper.setTo(destMail);
			mailSender.send(mimeMessage);
			log.info(String.format("MAIL SENT TO %S", destMail));
		}
		catch(MessagingException e){
			log.warn("WARNING WHILE SENDING MAIL TOOO-  ",destMail);
		}
		
	}
}
