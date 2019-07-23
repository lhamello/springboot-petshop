package br.com.lhamello.springbootpetshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.lhamello.springbootpetshop.model.Animal;
import br.com.lhamello.springbootpetshop.repository.AnimalRepository;

@Service
public class AnimalService {

	private final AnimalRepository repository;
	
	public AnimalService(final AnimalRepository repository) {
		this.repository = repository;
	}
	
	public List<Animal> listar(final Long idCliente) {
		return repository.listar(idCliente);
	}
}
