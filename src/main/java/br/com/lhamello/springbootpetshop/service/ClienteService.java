package br.com.lhamello.springbootpetshop.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.lhamello.springbootpetshop.client.CreditoApiClient;
import br.com.lhamello.springbootpetshop.client.CreditoDTO;
import br.com.lhamello.springbootpetshop.core.exception.CpfInvalidoException;
import br.com.lhamello.springbootpetshop.core.exception.NomeInvalidoException;
import br.com.lhamello.springbootpetshop.core.exception.ServiceException;
import br.com.lhamello.springbootpetshop.model.Cliente;
import br.com.lhamello.springbootpetshop.model.vo.Cpf;
import br.com.lhamello.springbootpetshop.repository.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository repository;
	private final CreditoApiClient creditoApiClient;

	public ClienteService(final ClienteRepository repository, final CreditoApiClient creditoApiClient) {
		this.repository = repository;
		this.creditoApiClient = creditoApiClient;
	}

	public Cliente consultar(final Long id) {
		return repository.getOne(id);
	}

	public Optional<Cliente> findById(final Long id) {
		return repository.findById(id);
	}

	public Cliente incluir(final Cliente cliente) {
		Objects.requireNonNull(cliente, "Cliente não pode ser nulo.");
		this.validarNome(cliente);
		this.validarCPF(cliente);
		this.validarSituacaoCredito(cliente);
		return repository.save(cliente);
	}

	private void validarSituacaoCredito(Cliente cliente) {
		CreditoDTO dto = creditoApiClient.verificarSituacao(cliente.getCpf().getValor());
		
		if ("NEGATIVADO".equals(dto.getSituacao())) {
			throw new ServiceException("Cliente negativado! Não pode ser cadastrado!");
		}
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
			throw new NomeInvalidoException("Informe seu nome completo.");
		}

		final String[] partesNome = nome.split(" ");

		Arrays.asList(partesNome).forEach(p -> {
			if (p.length() < 2) {
				throw new NomeInvalidoException("Informe seu nome sem abreviações.");
			}
		});
	}

	private void validarCPF(final Cliente cliente) {
		final Cpf cpf = cliente.getCpf();

		if (cpf == null || !cpf.isValido()) {
			throw new CpfInvalidoException("CPF deve conter 11 dígitos.");
		}
	}

	public void validarClienteAdimplente(final Long clienteId) {
		final Cliente cliente = repository.getOne(clienteId);

		if (cliente == null || cliente.getInadimplente() == null || Boolean.TRUE.equals(cliente.getInadimplente())) {
			throw new ServiceException("Não pode ser adicionado animal para um cliente inadimplente");
		}
	}

	public void update(Long id, Cliente cliente) {
		Optional<Cliente> clienteOptional = this.findById(id);

		if (clienteOptional.isPresent()) {
			Cliente clienteSalvo = clienteOptional.get();
			clienteSalvo.setNome(cliente.getNome());
			clienteSalvo.setInadimplente(cliente.getInadimplente());
			repository.save(cliente);
		}
		// TODO throw notfound...
	}
}
