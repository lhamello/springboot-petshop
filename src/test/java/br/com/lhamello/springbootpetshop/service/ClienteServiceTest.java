package br.com.lhamello.springbootpetshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.lhamello.springbootpetshop.core.exception.NomeInvalidoException;
import br.com.lhamello.springbootpetshop.model.Cliente;
import br.com.lhamello.springbootpetshop.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

	@Mock
	private ClienteRepository clienteRepository;
	
	@InjectMocks
	private ClienteService clienteService;
	
	@Test
	public void deveRetornarListaVazia() {
		final List<Cliente> clientes = clienteService.listar();
		
		final int valorAtual = clientes.size();
		final int valorEsperado = 0;
		final String mensagem = "Deve retornar uma lista vazia.";
		
		assertEquals(valorEsperado, valorAtual, mensagem);
	}
	
	@Test
	public void deveRemoverComSucesso() {
		clienteService.remover(12L);
		
		verify(clienteRepository).delete(new Cliente(12L, null, null));
		verifyNoMoreInteractions(clienteRepository);
	}
	
	@Test
	public void deveLancarExcecaoParaClienteNulo() {
		final NomeInvalidoException excecao = assertThrows(NomeInvalidoException.class, () -> { clienteService.incluir(null); });
		final String valorAtual = excecao.getMessage();
		final String valorEsperado = "Nome inválido.";
		final String mensagem = "Deve lançar exceção para cliente nulo";
		
		assertEquals(valorEsperado, valorAtual, mensagem);
	}
}
