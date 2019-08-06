package br.com.lhamello.springbootpetshop.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.lhamello.springbootpetshop.enumeration.Especie;
import br.com.lhamello.springbootpetshop.model.vo.Data;

@Entity
@Table(name = "ANIMAL")
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NOME")
	private String nome;

	@Embedded
	private Data dataNascimento;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESPECIE")
	private Especie especie;

	// @Column(name = "client_id")
//	private Long clienteId;

	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID")
	private Cliente cliente;

	@JoinColumn(name = "UNIDADE_ID")
	@ManyToOne
	private Unidade unidade;

	@OneToMany(mappedBy = "animal")
	private List<Produto> produtos;

	public Animal() {
		super();
		this.dataNascimento = new Data();
		this.cliente = new Cliente();
	}

	public Animal(final Long id, final String nome, final LocalDate dataNascimento, final Especie especie,
			final Long clienteId) {
		this();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = new Data(dataNascimento);
		this.especie = especie;
		this.cliente = new Cliente(clienteId);
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Data getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Data dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
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
