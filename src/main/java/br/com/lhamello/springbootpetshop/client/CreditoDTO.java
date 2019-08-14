package br.com.lhamello.springbootpetshop.client;

public class CreditoDTO {

	private String situacao;
	private int pontuacao;

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
}
