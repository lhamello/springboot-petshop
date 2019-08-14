package br.com.lhamello.springbootpetshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.lhamello.springbootpetshop.core.exception.DataNascimentoException;
import br.com.lhamello.springbootpetshop.core.exception.NomeInvalidoException;
import br.com.lhamello.springbootpetshop.model.Animal;
import br.com.lhamello.springbootpetshop.model.Cliente;
import br.com.lhamello.springbootpetshop.model.vo.Data;
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
		clienteService.validarClienteAdimplente(animal.getCliente().getId());
		repository.save(animal);
	}

	private void validarDataNascimento(final Animal animal) {
		final Data dataNascimento = animal.getDataNascimento();

		if (!dataNascimento.isValida()) {
			throw new DataNascimentoException("A data de nascimento deve ser menor ou igual a data de hoje.");
		}
	}

	private void validarNome(final Animal animal) {
		final String nome = animal.getNome();

		if (nome == null || nome.length() < 3) {
			throw new NomeInvalidoException("Informe o nome completo.");
		}
	}

	public List<Animal> listarByExample(Optional<Long> clienteId, Optional<String> nome) {
        Animal animal = new Animal();
        
        if (clienteId.isPresent()) {
            animal.setCliente(new Cliente(clienteId.get()));;
        }
        
        if (nome.isPresent()) {
            animal.setNome(nome.get());
        }
        
        return repository.findAll(Example.of(animal), Sort.by("nome"));
    }
}
