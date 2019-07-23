package br.com.lhamello.springbootpetshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
