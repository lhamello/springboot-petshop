package br.com.lhamello.springbootpetshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lhamello.springbootpetshop.model.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

}
