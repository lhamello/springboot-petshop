package br.com.lhamello.springbootpetshop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.lhamello.springbootpetshop.dto.ClienteDTO;
import br.com.lhamello.springbootpetshop.model.Cliente;
import br.com.lhamello.springbootpetshop.model.vo.Cpf;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper getModelMapperBean() {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper
			.createTypeMap(Cliente.class, ClienteDTO.class)
			.addMapping(cliente -> cliente.getCpf().getValor(), ClienteDTO::setCpf);
		
		modelMapper
			.createTypeMap(ClienteDTO.class, Cliente.class)
			.addMapping(dto -> new Cpf(dto.getCpf()), Cliente::setCpf);
		
		return new ModelMapper();
	}
}
