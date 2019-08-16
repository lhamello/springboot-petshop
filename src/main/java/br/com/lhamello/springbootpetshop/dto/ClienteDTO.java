package br.com.lhamello.springbootpetshop.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Mod11Check;

public class ClienteDTO {

	private Long id;
	@NotBlank(message = "O nome deve ser informado!")
	@Size(min = 2, max = 100, message = "Nome deve conter de {min} a {max} caracteres")
	private String nome;

	@Pattern.List(value = { @Pattern(regexp = "([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}-[0-9]{2})|([0-9]{11})"),
			@Pattern(regexp = "^(?:(?!000\\.?000\\.?000-?00).)*$"),
			@Pattern(regexp = "^(?:(?!111\\.?111\\.?111-?11).)*$"),
			@Pattern(regexp = "^(?:(?!222\\.?222\\.?222-?22).)*$"),
			@Pattern(regexp = "^(?:(?!333\\.?333\\.?333-?33).)*$"),
			@Pattern(regexp = "^(?:(?!444\\.?444\\.?444-?44).)*$"),
			@Pattern(regexp = "^(?:(?!555\\.?555\\.?555-?55).)*$"),
			@Pattern(regexp = "^(?:(?!666\\.?666\\.?666-?66).)*$"),
			@Pattern(regexp = "^(?:(?!777\\.?777\\.?777-?77).)*$"),
			@Pattern(regexp = "^(?:(?!888\\.?888\\.?888-?88).)*$"),
			@Pattern(regexp = "^(?:(?!999\\.?999\\.?999-?99).)*$") })
	@Mod11Check.List(value = {
			@Mod11Check(checkDigitIndex = 12, endIndex = 10, treatCheck10As = 48, ignoreNonDigitCharacters = true),
			@Mod11Check(checkDigitIndex = 13, endIndex = 12, treatCheck10As = 48, ignoreNonDigitCharacters = true) })
	private String cpf;
	private List<AnimalDTO> animais;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<AnimalDTO> getAnimais() {
		return animais;
	}

	public void setAnimais(List<AnimalDTO> animais) {
		this.animais = animais;
	}
}
