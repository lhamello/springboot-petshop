package br.com.lhamello.springbootpetshop.model;

public class Cliente {

	private String nome;
	private String cpf;

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(final String cpf) {
		this.cpf = cpf;
	}
}
