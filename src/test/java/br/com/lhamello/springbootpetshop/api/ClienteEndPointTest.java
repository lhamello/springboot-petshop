package br.com.lhamello.springbootpetshop.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lhamello.springbootpetshop.config.ModelMapperConfig;
import br.com.lhamello.springbootpetshop.dto.ClienteDTO;
import br.com.lhamello.springbootpetshop.dto.factory.ClienteDtoFactory;
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
			.andExpect(MockMvcResultMatchers.header().string("Content-Type", "application/json;charset=UTF-8"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(equalTo(77)))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void deveCriarClienteComSucesso() throws Exception {
		Cliente clienteSalvar = new Cliente(56L, "Fulano Silva da Silva", "999-888-777-66");
		
		Mockito.when(clienteService.incluir(clienteSalvar))
		       .thenReturn(clienteSalvar);
		
		ClienteDTO clienteDTO = ClienteDtoFactory.from(clienteSalvar);
		
		byte[] objectToJson = new ObjectMapper().writeValueAsBytes(clienteDTO);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
			   .content(objectToJson)
			   .header("Content-Type", MediaType.APPLICATION_JSON_VALUE))
			   .andExpect(MockMvcResultMatchers.status().isCreated())
			   .andExpect(header().string("Location", "/clientes/56"))
			   .andExpect(content().string(""));
	}
}
