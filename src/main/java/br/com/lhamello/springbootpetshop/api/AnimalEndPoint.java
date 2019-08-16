package br.com.lhamello.springbootpetshop.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lhamello.springbootpetshop.dto.AnimalDTO;
import br.com.lhamello.springbootpetshop.service.AnimalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/animais")
@Api("Animal Controller")
public class AnimalEndPoint {

    private final AnimalService animalService;
    private final ModelMapper mapper;

    public AnimalEndPoint(AnimalService animalService, ModelMapper mapper) {
        this.animalService = animalService;
        this.mapper = mapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Lista os animais ativos do sistema")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista de animais retornada com sucesso"),
            @ApiResponse(code = 400, message = "Parâmetros informados incorretamente")
    })
    public ResponseEntity<List<AnimalDTO>> list(
            @ApiParam("Id do Cliente para filtro")
            @RequestParam Optional<Long> clienteId,
            @ApiParam("Nome do Animal")
            @RequestParam Optional<String> nome){
        return ResponseEntity.ok(
                animalService.listarByExample(clienteId, nome)
                .stream()
                .map(a -> mapper.map(a, AnimalDTO.class))
                .collect(Collectors.toList()));
    }
}