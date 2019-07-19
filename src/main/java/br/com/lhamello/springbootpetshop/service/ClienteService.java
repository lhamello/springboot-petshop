package br.com.lhamello.springbootpetshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.lhamello.springbootpetshop.model.Cliente;
import br.com.lhamello.springbootpetshop.repository.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository repository;
	
	public ClienteService(final ClienteRepository repository) {
		this.repository = repository;
	}
	
	public void incluir(final Cliente cliente) {
		repository.save(cliente);
	}
	
	public List<Cliente> listar() {
		return repository.findAll();
	}
}
