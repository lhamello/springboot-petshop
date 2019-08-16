package br.com.lhamello.springbootpetshop.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import br.com.lhamello.springbootpetshop.enumeration.Especie;

public class AnimalDTO {

	private Long id;
	@NotBlank(message = "Informe o nome do animal!")
	@Size(min = 2, max = 100, message = "O nome do animal deve conter de {min} a {max} caracteres.")
	private String nome;
	@PastOrPresent
	private LocalDate dataNascimento;
	@NotNull
	private Especie especie;

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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}
}
