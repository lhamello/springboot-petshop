package br.com.lhamello.springbootpetshop.dto;

import java.time.LocalDate;

import br.com.lhamello.springbootpetshop.enumeration.Especie;

public class AnimalDTO {
	
	private Long id;
	private String nome;
	private LocalDate dataNascimento;
	private Especie especie;

}
