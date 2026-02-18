package com.msstudy.ms_credit_evaluator.application.exceptions;

import lombok.Getter;

@SuppressWarnings("serial")
public class MicroserviceCommunicationError extends RuntimeException {
	
	@Getter
	private Integer status;
	
	public MicroserviceCommunicationError(String msg, Integer status) {
		super(msg);
		this.status = status;
	}

}
