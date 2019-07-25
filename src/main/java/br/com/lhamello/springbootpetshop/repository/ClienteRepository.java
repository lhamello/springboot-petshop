package br.com.lhamello.springbootpetshop.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import br.com.lhamello.springbootpetshop.model.Cliente;

@Repository
public class ClienteRepository {

	private List<Cliente> clientes = new LinkedList<>();

	@PostConstruct
	private void init() {
		clientes.add(new Cliente(1L, "Fulano", "000.000.000-01"));
		clientes.add(new Cliente(2L, "Beltrano", "000.000.000-02"));
		clientes.add(new Cliente(3L, "Ciclano", "000.000.000-03"));
		clientes.add(new Cliente(4L, "Fulano da Silva", "000.000.000-04"));
	}

	public List<Cliente> findAll() {
		return clientes;
	}

	public void save(final Cliente cliente) {
		cliente.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
		clientes.add(cliente);
	}

	public void delete(final Cliente cliente) {
		clientes.remove(cliente);
	}

	public Cliente find(final Long id) {
		return clientes
				.stream()
				.filter(c -> c.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
}
