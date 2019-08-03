package br.com.lhamello.springbootpetshop.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.lhamello.springbootpetshop.model.vo.Cpf;

@Entity
@Table(name = "TB_CLIENTE")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NOME_CLIENTE")
	private String nome;
	@Embedded
	private Cpf cpf;
	@Column(name = "INADIMPLENTE")
	private Boolean inadimplente;

	public Cliente() {
		super();
		this.inadimplente = false;
		this.cpf = new Cpf(null);
	}

	public Cliente(final Long id, final String nome, final String numeroCpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = new Cpf(numeroCpf);
		this.inadimplente = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public Cpf getCpf() {
		return cpf;
	}

	public void setCpf(Cpf cpf) {
		this.cpf = cpf;
	}

	public Boolean getInadimplente() {
		return inadimplente;
	}

	public void setInadimplente(Boolean inadimplente) {
		this.inadimplente = inadimplente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
