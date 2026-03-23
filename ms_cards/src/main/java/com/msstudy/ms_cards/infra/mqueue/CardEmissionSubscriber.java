package com.msstudy.ms_cards.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msstudy.ms_cards.domain.entities.CardEntity;
import com.msstudy.ms_cards.domain.entities.CustomerCard;
import com.msstudy.ms_cards.dtos.CardEmissionDataRequest;
import com.msstudy.ms_cards.infra.repository.CardRepository;
import com.msstudy.ms_cards.infra.repository.CustomerCardRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CardEmissionSubscriber {
	
	private final CardRepository cardRepository;
	private final CustomerCardRepository customerCardRepository;
	
	@RabbitListener(queues = "${mq.queues.card-emission}")
	public void requestEmission(@Payload String payload) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			CardEmissionDataRequest data = mapper.readValue(payload, CardEmissionDataRequest.class);
			CardEntity card = cardRepository.findById(data.getCardId()).orElseThrow();
			
			CustomerCard customerCard =  new CustomerCard();
			customerCard.setCard(card);
			customerCard.setCpf(data.getCpf());
			customerCard.setBaseLimit(data.getApprovedLimit());
			customerCardRepository.save(customerCard);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
