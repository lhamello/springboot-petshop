package br.com.lhamello.springbootpetshop.api;

import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import br.com.lhamello.springbootpetshop.dto.ClienteDTO;
import br.com.lhamello.springbootpetshop.model.Cliente;
import br.com.lhamello.springbootpetshop.service.ClienteService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/clientes")
@Api(tags = "clientes")
public class ClienteEndPoint {

	private final ClienteService clienteService;
	private final ModelMapper mapper;

	public ClienteEndPoint(ClienteService clienteService, ModelMapper modelMapper) {
		this.clienteService = clienteService;
		this.mapper = modelMapper;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		return ok(clienteService.listar()
				.stream()
				.map(cliente -> mapper.map(cliente, ClienteDTO.class))
				.collect(Collectors.toList()));
	}

	@SuppressWarnings("rawtypes")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity create(@RequestBody Cliente cliente) {
		URI location = URI.create(format("/clientes/%d", clienteService.incluir(cliente).getId()));
		return created(location).build();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
		Optional<Cliente> clienteOptional = clienteService.findById(id);

		if (clienteOptional.isPresent()) {
			ClienteDTO dto = mapper.map(clienteOptional.get(), ClienteDTO.class);
			return ok(dto);
		}

		return notFound().build();
	}

	@SuppressWarnings("rawtypes")
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity update(@RequestBody Cliente cliente, @PathVariable Long id) throws NotFound {
		clienteService.update(id, cliente);
		return noContent().build();
	}
}