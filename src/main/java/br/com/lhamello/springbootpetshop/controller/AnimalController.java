package br.com.lhamello.springbootpetshop.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.lhamello.springbootpetshop.enumeration.Especie;
import br.com.lhamello.springbootpetshop.model.Animal;
import br.com.lhamello.springbootpetshop.service.AnimalService;

@Controller
public class AnimalController {

	private AnimalService service;

	public AnimalController(AnimalService service) {
		this.service = service;
	}
	
	@GetMapping("/animais-listar")
	public String listar(final Model model, @RequestParam final Long clienteId) {
		model.addAttribute("sistema", "PetShop");
		model.addAttribute("animais", service.listar(clienteId));
		return "animais-listar";
	}
	
	@GetMapping("/animal-adicionar")
	public String adicionar(final Model model) {
		model.addAttribute("especies", this.getEspecies());
		return "/animal-adicionar";
	}
	
	private List<Especie> getEspecies() {
		return Arrays.asList(Especie.values());
	}

	@PostMapping("/animal-form")
	public String salvar(final Model model, final Animal animal) {
		service.adicionar(animal);
		return "/animal-adicionar";
	}
}
