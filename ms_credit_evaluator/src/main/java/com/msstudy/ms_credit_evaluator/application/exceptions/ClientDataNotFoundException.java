package com.msstudy.ms_credit_evaluator.application.exceptions;

@SuppressWarnings("serial")
public class ClientDataNotFoundException extends RuntimeException {

	public ClientDataNotFoundException(String cpf) {
		super("Dados de cliente não encontrados para o CPF: " + cpf);
	}
	
}
