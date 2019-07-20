package br.com.lhamello.springbootpetshop.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.lhamello.springbootpetshop.core.exception.CpfInvalidoException;
import br.com.lhamello.springbootpetshop.core.exception.NomeInvalidoException;
import br.com.lhamello.springbootpetshop.model.Cliente;
import br.com.lhamello.springbootpetshop.repository.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository repository;
	
	public ClienteService(final ClienteRepository repository) {
		this.repository = repository;
	}
	
	public void incluir(final Cliente cliente) {
		this.validarNome(cliente);
		this.validarCPF(cliente);
		repository.save(cliente);
	}
	
	public List<Cliente> listar() {
		return repository.findAll();
	}
	
	public void remover(final Long id) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		repository.delete(cliente);
	}
	
	private void validarNome(final Cliente cliente) {
		final String nome = cliente.getNome();
		
		if (nome == null || nome.split(" ").length < 2) {
			throw new NomeInvalidoException("O nome do cliente deve conter duas partes.");
		}
		
		final String[] partesNome = nome.split(" ");
		
		Arrays.asList(partesNome).forEach(p -> {
			if (p.length() < 2) {
				throw new NomeInvalidoException("Cada parte do nome deve conter pelo menos 2 letras.");
			}
		}); 
	}
	
	private void validarCPF(final Cliente cliente) {
		final String cpf = cliente.getCpf();
		
		if (cpf == null || cpf.replaceAll("[^\\d]", "" ).length() != 11) {
			throw new CpfInvalidoException("CPF deve conter 11 dígitos.");
		}
	}
}
