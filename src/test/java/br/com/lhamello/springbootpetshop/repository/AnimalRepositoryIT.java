package br.com.lhamello.springbootpetshop.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.lhamello.springbootpetshop.enumeration.Especie;
import br.com.lhamello.springbootpetshop.model.Animal;

@DataJpaTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test-jpa")
public class AnimalRepositoryIT {

	@Autowired
	private AnimalRepository animalRepository;

	@Test
	public void deveRetornarListaVazia() {
		final List<Animal> lista = animalRepository.findByClienteId(1L);
		Assert.assertEquals("Deve retornar lista Vazia", 0, lista.size());
	}

	@Test
	@Sql(value = "classpath:insere_rex.sql")
	public void deveRetornarUmAnimal() {
		final List<Animal> lista = animalRepository.findByClienteId(133L);
		Assert.assertEquals("Deve retornar um animal", 1, lista.size());

		final Animal rex = lista.get(0);
		Assert.assertEquals("O nome deve ser Rex", "Rex", rex.getNome());
		Assert.assertEquals("O cliente deve ser o 133", Long.valueOf(133), rex.getCliente().getId());
		Assert.assertEquals("O animal deve ser mamífero", Especie.MAMIFERO, rex.getEspecie());
	}
	
	@Test
	@Sql(value = "classpath:insere_rex.sql")
	public void deveRetornarUmAnimalPorNome() {
		final List<Animal> lista = animalRepository.findByNome("Rex");
		final Animal rex = lista.get(0);
		Assert.assertEquals("O nome deve ser Rex", "Rex", rex.getNome());
		Assert.assertEquals("O cliente deve ser o 133", Long.valueOf(133), rex.getCliente().getId());
		Assert.assertEquals("O animal deve ser mamífero", Especie.MAMIFERO, rex.getEspecie());
	}
}
