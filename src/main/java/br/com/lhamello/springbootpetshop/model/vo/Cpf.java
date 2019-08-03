package br.com.lhamello.springbootpetshop.model.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Cpf {

	@Column(name = "CPF_CLIENTE")
	private String valor;

	private Cpf() {
		super();
	}
	
	public Cpf(final String valor) {
		this();
		this.valor = valor;
	}

	public boolean isValido() {
		return valor != null && valor.replaceAll("\\D", "").length() == 11;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
