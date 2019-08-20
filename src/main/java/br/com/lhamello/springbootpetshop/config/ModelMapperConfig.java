package br.com.lhamello.springbootpetshop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.lhamello.springbootpetshop.dto.ClienteDTO;
import br.com.lhamello.springbootpetshop.model.Cliente;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper getBean() {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.createTypeMap(Cliente.class, ClienteDTO.class)
		           .addMapping(cliente -> cliente.getCpf().getValor(), ClienteDTO::setCpf);
		
//        .addMapping(Cliente::getCpf, (clienteDto, o) -> clienteDto.setCpf(((Cpf)o).getValor()));

		modelMapper.createTypeMap(ClienteDTO.class, Cliente.class)
		           .addMapping(ClienteDTO::getCpf, (cliente, o) -> cliente.getCpf().setValor((String) o));

		return modelMapper;
	}
}
