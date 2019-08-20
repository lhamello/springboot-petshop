package br.com.lhamello.springbootpetshop.dto.factory;

import br.com.lhamello.springbootpetshop.dto.ClienteDTO;
import br.com.lhamello.springbootpetshop.dto.builder.ClienteDTOBuilder;
import br.com.lhamello.springbootpetshop.model.Cliente;

public class ClienteDTOFactory {

	public static ClienteDTO from(Cliente cliente) {
		return new ClienteDTOBuilder()
				.setId(cliente.getId())
				.setNome(cliente.getNome())
				.build();
	}
}