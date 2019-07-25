package br.com.lhamello.springbootpetshop.model;

public class Cliente {

	private Long id;
	private String nome;
	private String cpf;
	private Boolean inadimplente;

	public Cliente() {
		super();
	}

	public Cliente(final Long id, final String nome, final String cpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(final String cpf) {
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
