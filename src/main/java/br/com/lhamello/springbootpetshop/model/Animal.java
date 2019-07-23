package br.com.lhamello.springbootpetshop.model;

import java.time.LocalDate;

import br.com.lhamello.springbootpetshop.enumeration.Especie;

/**
 * @author aluno02sala04
 *
 */
public class Animal {

	private Long id;
	private String nome;
	private LocalDate dataNascimento;
	private Especie especie;
	private Long clienteId;
	
	public Animal() {
		super();
	}
	
	public Animal(final Long id, final String nome, final LocalDate dataNascimento, final Especie especie, final Long clienteId) {
		this();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.especie = especie;
		this.clienteId = clienteId;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(final LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(final Especie especie) {
		this.especie = especie;
	}

	public Long getIdCliente() {
		return clienteId;
	}

	public void setIdCliente(final Long idCliente) {
		this.clienteId = idCliente;
	}
	
	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(final Long clienteId) {
		this.clienteId = clienteId;
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
		Animal other = (Animal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
