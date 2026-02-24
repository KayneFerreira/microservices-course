package com.msstudy.ms_cards.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CardApprovalSubscriber {
	
	@RabbitListener(queues = "${mq.queues.card-approval}")
	public void requestEmission(@Payload String payload) {
		System.out.println(payload);
	}

}
