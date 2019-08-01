package br.com.lhamello.springbootpetshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.lhamello.springbootpetshop.enumeration.Especie;
import br.com.lhamello.springbootpetshop.model.Animal;
import br.com.lhamello.springbootpetshop.repository.AnimalRepository;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

	@Mock
	private AnimalRepository animalRepository;

	@Mock
	private ClienteService clienteService;

	@InjectMocks
	private AnimalService animalService;
	
	@Test
	public void deveRetornarListaVazia() {
		final List<Animal> animais = animalService.listar(0L);

		final int valorAtual = animais.size();
		final int valorEsperado = 0;
		final String mensagem = "Deve retornar uma lista vazia.";

		assertEquals(valorEsperado, valorAtual, mensagem);
	}
	
	public void deveRetornarListaComAnimais() {
		// Arrange
		List<Animal> listaCliente01 = Arrays.asList(
				new Animal(1L, "Rex", LocalDate.now().minusYears(3), Especie.MAMIFERO, 1L),
				new Animal(2L, "Totó", LocalDate.now().minusYears(2), Especie.MAMIFERO, 1L)
		);
		List<Animal> listaCliente02 = Arrays.asList(
				new Animal(3L, "Nemo", LocalDate.now().minusYears(1), Especie.PEIXE, 2L)
		);
		
		// Act
		Mockito.when(animalRepository.findByClienteId(1L)).thenReturn(listaCliente01);
		Mockito.when(animalRepository.findByClienteId(2L)).thenReturn(listaCliente02);
		
		// Asserts
		assertEquals(listaCliente01, animalService.listar(1L));
		assertEquals(listaCliente02, animalService.listar(2L));
	}
}
