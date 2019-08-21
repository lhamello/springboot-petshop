package br.com.lhamello.springbootpetshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lhamello.springbootpetshop.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

	List<Animal> findByClienteId(Long idCliente);

	List<Animal> findByNome(String string);

	List<Animal> findByUnidadeIdAndClienteIdOrderByClienteId(Long unidadeId, Long clienteId);
	
	List<Animal> findByClienteUnidadeId(Long unidadeId);

//	private List<Animal> animais = new LinkedList<>();
//
//	@PostConstruct
//	private void init() {
//		animais.add(new Animal(1L, "Rex", LocalDate.now(), Especie.MAMIFERO, 1L));
//		animais.add(new Animal(2L, "Nemo", LocalDate.now().minusMonths(1), Especie.PEIXE, 1L));
//		animais.add(new Animal(3L, "T-Rex", LocalDate.now().minusYears(1), Especie.REPTIL, 1L));
//	}
//	
//	public List<Animal> listar(final Long clienteId) {
//		return animais.stream()
//			.filter(a -> a.getIdCliente().equals(clienteId))
//			.collect(Collectors.toList());
//	}
//
//	public void save(final Animal animal) {
//		animal.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
//		animais.add(animal);
//	}

}
