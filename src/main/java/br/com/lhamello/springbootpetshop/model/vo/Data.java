package br.com.lhamello.springbootpetshop.model.vo;

import java.time.LocalDate;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

public class Data {

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "data_nascimento")
	private LocalDate valor;

	public Data() {
		super();
	}

	public Data(final LocalDate dataNascimento) {
		this();
		this.valor = dataNascimento;
	}

	public boolean isValida() {
		return valor != null && !valor.isAfter(LocalDate.now());
	}

	public LocalDate getValor() {
		return valor;
	}

	public void setValor(LocalDate valor) {
		this.valor = valor;
	}
}
