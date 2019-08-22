package br.com.lhamello.springbootpetshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lhamello.springbootpetshop.model.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

	@Query("select distinct a.unidade from Animal a where a.nome = :nome")
	List<Unidade> findByAnimaisNome(@Param("nome") String nome);
}
