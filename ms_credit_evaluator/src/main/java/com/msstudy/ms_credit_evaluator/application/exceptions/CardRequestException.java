package com.msstudy.ms_credit_evaluator.application.exceptions;

@SuppressWarnings("serial")
public class CardRequestException extends RuntimeException {

	public CardRequestException(String msg) {
		super(msg);
	}
}
