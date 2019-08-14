package br.com.lhamello.springbootpetshop.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lhamello.springbootpetshop.model.Unidade;
import br.com.lhamello.springbootpetshop.service.UnidadeService;

@RestController
@RequestMapping(value = "/unidades", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UnidadeEndPoint {

	private final UnidadeService unidadeService;

	public UnidadeEndPoint(final UnidadeService unidadeService) {
		this.unidadeService = unidadeService;
	}

	@GetMapping
	public ResponseEntity<List<Unidade>> findAll() {
		return ResponseEntity.ok(unidadeService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Unidade> findById(@PathVariable Long id) {
		final Optional<Unidade> optional = unidadeService.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping
	public ResponseEntity create(@RequestBody Unidade unidade) {
		final Unidade unidadeCriada = unidadeService.create(unidade);
		final URI uri = URI.create(String.format("/unidades/%d", unidadeCriada.getId()));
		return ResponseEntity.created(uri).build();
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	public ResponseEntity update(@RequestBody Unidade unidade, @PathVariable Long id) {
		unidadeService.update(unidade);
		return ResponseEntity.noContent().build();
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        unidadeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
