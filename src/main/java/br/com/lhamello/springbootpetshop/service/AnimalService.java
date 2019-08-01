package br.com.lhamello.springbootpetshop.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.lhamello.springbootpetshop.core.exception.DataNascimentoException;
import br.com.lhamello.springbootpetshop.core.exception.NomeInvalidoException;
import br.com.lhamello.springbootpetshop.model.Animal;
import br.com.lhamello.springbootpetshop.repository.AnimalRepository;

@Service
public class AnimalService {

	private final AnimalRepository repository;
	private final ClienteService clienteService;

	public AnimalService(final AnimalRepository repository, final ClienteService clienteService) {
		this.repository = repository;
		this.clienteService = clienteService;
	}

	public List<Animal> listar(final Long idCliente) {
		return repository.findByClienteId(idCliente);
	}

	public void adicionar(final Animal animal) {
		this.validarDataNascimento(animal);
		this.validarNome(animal);
		clienteService.validarClienteAdimplente(animal.getClienteId());
		repository.save(animal);
	}

	private void validarDataNascimento(final Animal animal) {
		final LocalDate dataNascimento = animal.getDataNascimento();

		if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now())) {
			throw new DataNascimentoException("A data de nascimento deve ser menor ou igual a data de hoje.");
		}
	}

	private void validarNome(final Animal animal) {
		final String nome = animal.getNome();

		if (nome == null || nome.length() < 3) {
			throw new NomeInvalidoException("Informe o nome completo.");
		}
	}
}
