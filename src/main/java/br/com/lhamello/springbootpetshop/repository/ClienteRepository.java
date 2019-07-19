package br.com.lhamello.springbootpetshop.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.lhamello.springbootpetshop.model.Cliente;

@Repository
public class ClienteRepository {

	private List<Cliente> clientes = new LinkedList<>();

	public List<Cliente> findAll() {
		return clientes;
	}

	public void save(final Cliente cliente) {
		clientes.add(cliente);
	}
}
