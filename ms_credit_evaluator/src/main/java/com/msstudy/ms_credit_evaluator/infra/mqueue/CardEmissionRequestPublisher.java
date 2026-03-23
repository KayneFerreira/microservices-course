package com.msstudy.ms_credit_evaluator.infra.mqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.msstudy.ms_credit_evaluator.domain.model.CardEmissionData;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class CardEmissionRequestPublisher {
	
	private final RabbitTemplate rabbitTemplate;
	private final Queue cardEmissionQueue;
	
	public void requestCard(CardEmissionData data) throws JsonProcessingException {
		String json = toJson(data);
		String name = cardEmissionQueue.getName();
		rabbitTemplate.convertAndSend(name, json);
	}
	
	public String toJson(CardEmissionData data) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(data);
	}

}
