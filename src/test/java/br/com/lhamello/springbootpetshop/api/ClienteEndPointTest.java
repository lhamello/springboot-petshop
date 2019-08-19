package br.com.lhamello.springbootpetshop.api;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.lhamello.springbootpetshop.config.ModelMapperConfig;
import br.com.lhamello.springbootpetshop.model.Cliente;
import br.com.lhamello.springbootpetshop.service.ClienteService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ClienteEndPoint.class)
@Import(ModelMapperConfig.class)
public class ClienteEndPointTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClienteService clienteService;
	
	@Test
	public void deveRetornarListaClienteComSucesso() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
			.andExpect(MockMvcResultMatchers.status().is(200))
			.andExpect(MockMvcResultMatchers.content().string("[]"))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void deveRetornarListaComClienteFulano() throws Exception {
		Mockito.when(clienteService.listar()).thenReturn(Arrays.asList(new Cliente(77L, "Fulano Silva", "000.111.222-33")));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.header().string("Content-Type", "applicaton/json;charset=UTF-8"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(equalTo(77)))
			.andDo(MockMvcResultHandlers.print());
	}
}
