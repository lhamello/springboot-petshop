package br.com.lhamello.springbootpetshop.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.lhamello.springbootpetshop.model.Cliente;
import br.com.lhamello.springbootpetshop.model.vo.Cpf;
import br.com.lhamello.springbootpetshop.repository.ClienteRepository;

@RunWith(MockitoJUnitRunner.class)
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

		assertEquals(mensagem, valorEsperado, valorAtual);
	}

	@Test
	public void deveRemoverComSucesso() {
		clienteService.remover(12L);

		verify(clienteRepository).delete(new Cliente(12L, null, null));
		verifyNoMoreInteractions(clienteRepository);
	}

	@Test
	public void deveIncluirClienteComSucesso() {
		Cliente cliente = new Cliente();
		cliente.setNome("Luiz Henrique");
		cliente.setCpf(new Cpf("12345678901"));
		clienteService.incluir(cliente);
		verify(clienteRepository).save(cliente);
	}
	
//	@Test
//	public void deveLancarNullPointerExceptionParaClienteNulo() {
//		final NullPointerException excecao = assertThrows(NullPointerException.class, () -> {
//			clienteService.incluir(null);
//		});
//		
//		final String valorAtual = excecao.getMessage();
//		final String valorEsperado = "Cliente não pode ser nulo.";
//		final String mensagem = "Deve lançar exceção para cliente nulo";
//
//		assertEquals(valorEsperado, valorAtual, mensagem);
//	}
//
//	@Test
//	public void deveLancarNomeInvalidoExceptionParaNomeNulo() {
//		final Cliente cliente = new Cliente();
//		cliente.setNome(null);
//		
//		final NomeInvalidoException excecao = assertThrows(NomeInvalidoException.class, () -> {
//			clienteService.incluir(cliente);
//		});
//		
//		final String valorAtual = excecao.getMessage();
//		final String valorEsperado = "Informe seu nome completo.";
//		final String mensagem = "Deve lançar exceção para nome do cliente nulo";
//
//		assertEquals(valorEsperado, valorAtual, mensagem);
//	}
//
//	@Test
//	public void deveLancarNomeInvalidoExceptionParaNomeComTamanho1() {
//		final Cliente cliente = new Cliente();
//		cliente.setNome("S");
//		
//		final NomeInvalidoException excecao = assertThrows(NomeInvalidoException.class, () -> {
//			clienteService.incluir(cliente);
//		});
//		
//		final String valorAtual = excecao.getMessage();
//		final String valorEsperado = "Informe seu nome completo.";
//		final String mensagem = "Deve lançar exceção para nome do cliente nulo";
//
//		assertEquals(valorEsperado, valorAtual, mensagem);
//	}
}
