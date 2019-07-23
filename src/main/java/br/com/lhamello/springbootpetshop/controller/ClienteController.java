package br.com.lhamello.springbootpetshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import br.com.lhamello.springbootpetshop.core.exception.ServiceException;
import br.com.lhamello.springbootpetshop.model.Cliente;
import br.com.lhamello.springbootpetshop.service.ClienteService;

@Controller
public class ClienteController {
	
	private final ClienteService service;
	
	public ClienteController(final ClienteService clienteService) {
		this.service = clienteService;
	}
	
	@GetMapping("/")
	public String index(final Model model) {
		model.addAttribute("sistema", "PetShop");
		model.addAttribute("clientes", service.listar());
		return "index";
	}
	
	@GetMapping("/cliente-adicionar")
	public String redirecionarPaginaAdicionar(final Model model) {
		return "cliente-adicionar";
	}
	
	@PostMapping("/cliente-form")
	public String adicionarCliente(Cliente cliente, final Model model) {
		try {
			service.incluir(cliente);
		} catch (ServiceException excecao) {
			model.addAttribute("erro", "ERRO = " + excecao.getMessage());
		}
		
		return "cliente-adicionar";
	}
	
	@GetMapping("/cliente-excluir")
	public RedirectView removerCliente(@RequestParam final Long id) {
		service.remover(id);
		return new RedirectView("/");
	}
}
