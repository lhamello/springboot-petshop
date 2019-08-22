package br.com.lhamello.springbootpetshop.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.lhamello.springbootpetshop.model.Unidade;

@DataJpaTest
@RunWith(SpringRunner.class)
@Sql("classpath:insere_animal_por_unidade.sql")
public class UnidadeRepositoryTest {

	@Autowired
	private UnidadeRepository unidadeRepository;

	@Test
	public void deveConsultarAnimaisPeloNome() {
		List<Unidade> unidades = unidadeRepository.findByAnimaisNome("Pluto");
		assertEquals(2, unidades.size());

		Unidade unidade1 = unidades.get(0);
		assertEquals(Long.valueOf(1), unidade1.getId());
		assertEquals("Cristóvão", unidade1.getNome());
		assertEquals("Rua Cristóvão Colombo", unidade1.getEndereco());

		Unidade unidade2 = unidades.get(1);
		assertEquals(Long.valueOf(2), unidade2.getId());
		assertEquals("Centro", unidade2.getNome());
		assertEquals("Rua Alberto Bins", unidade2.getEndereco());
	}
	
	@Test
	public void deveConsultarAnimaisPeloNomeRex() {
		List<Unidade> unidades = unidadeRepository.findByAnimaisNome("Rex");
		assertEquals(1, unidades.size());

		Unidade unidade1 = unidades.get(0);
		assertEquals(Long.valueOf(2), unidade1.getId());
		assertEquals("Centro", unidade1.getNome());
		assertEquals("Rua Alberto Bins", unidade1.getEndereco());
	}
}
