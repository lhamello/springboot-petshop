package br.com.lhamello.springbootpetshop.e2e;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteEndPointE2E {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Sql("classpath:e2e/insere_fulano.sql")
	public void deveObterClienteFulanoComSucesso() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/clientes/133"))
		       .andExpect(jsonPath("$.id").value(equalTo(133)));
	}
}
