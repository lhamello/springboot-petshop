package br.com.lhamello.springbootpetshop.dto.builder;

import java.util.Arrays;

import br.com.lhamello.springbootpetshop.dto.AnimalDTO;
import br.com.lhamello.springbootpetshop.dto.ClienteDTO;

public class ClienteDTOBuilder {

	private ClienteDTO dto = new ClienteDTO();

	public ClienteDTOBuilder setId(Long id) {
		dto.setId(id);
		return this;
	}

	public ClienteDTOBuilder setNome(String nome) {
		dto.setNome(nome);
		return this;
	}

	public ClienteDTOBuilder adicionaAnimal(AnimalDTO animal) {
		if (dto.getAnimais() == null) {
			dto.setAnimais(Arrays.asList(animal));
		}

		dto.getAnimais().add(animal);
		return this;
	}

	public ClienteDTO build() {
		return dto;
	}
}