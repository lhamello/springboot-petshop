package br.com.lhamello.springbootpetshop.repository;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import br.com.lhamello.springbootpetshop.enumeration.Especie;
import br.com.lhamello.springbootpetshop.model.Animal;

@Repository
public class AnimalRepository {

	private List<Animal> animais = new LinkedList<>();

	@PostConstruct
	private void init() {
		animais.add(new Animal(1L, "Rex", LocalDate.now(), Especie.MAMIFERO, 1L));
		animais.add(new Animal(2L, "Nemo", LocalDate.now().minusMonths(1), Especie.PEIXE, 1L));
		animais.add(new Animal(3L, "T-Rex", LocalDate.now().minusYears(1), Especie.REPTIL, 1L));
	}
	
	public List<Animal> listar(final Long clienteId) {
		return animais.stream()
			.filter(a -> a.getIdCliente().equals(clienteId))
			.collect(Collectors.toList());
	}

	public void save(final Animal animal) {
		animal.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
		animais.add(animal);
	}
}
