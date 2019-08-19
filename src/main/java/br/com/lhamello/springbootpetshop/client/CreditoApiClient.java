package br.com.lhamello.springbootpetshop.client;

public interface CreditoApiClient {
	
	CreditoDTO verificarSituacao(String cpf);
}