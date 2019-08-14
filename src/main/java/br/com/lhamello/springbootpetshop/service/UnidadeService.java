package br.com.lhamello.springbootpetshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.lhamello.springbootpetshop.model.Unidade;
import br.com.lhamello.springbootpetshop.repository.UnidadeRepository;

@Service
public class UnidadeService {

	private final UnidadeRepository unidadeRepository;

	public UnidadeService(final UnidadeRepository unidadeRepository) {
		this.unidadeRepository = unidadeRepository;
	}

	public List<Unidade> findAll() {
		return unidadeRepository.findAll();
	}

	public Optional<Unidade> findById(final Long id) {
		return unidadeRepository.findById(id);
	}

	public Unidade create(final Unidade unidade) {
		return unidadeRepository.save(unidade);
	}

	public void update(final Unidade unidade) {
		unidadeRepository.save(unidade);
	}

	public void deleteById(Long id) {
		unidadeRepository.deleteById(id);
	}
}
