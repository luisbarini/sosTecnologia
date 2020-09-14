package br.com.sos.tecnologia.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sos.tecnologia.desafio.entity.MarcaEntity;
import br.com.sos.tecnologia.desafio.service.MarcaService;

@Controller
@RequestMapping("marca")
public class MarcaController {

	@Autowired
	private MarcaService service;
	
	@GetMapping("listar/{mensagem}")
	public ModelAndView listarComMensagem(@PathVariable(required = false, value = "mensagem") String mensagem) {
		ModelAndView model = new ModelAndView("marca/marca_listar");
		model.addObject("mensagem",mensagem);
		return model;
	}
	
	@GetMapping("listar")
	public ModelAndView listar(@PathVariable(required = false, value = "mensagem") String mensagem) {
		return new ModelAndView("marca/marca_listar");
	}

	@GetMapping("")
	public ModelAndView cadastrar() {
		ModelAndView model = new ModelAndView("marca/marca_editar");
		model.addObject("entity", new MarcaEntity());
		return model;
	}
	
	@GetMapping("{id}")
	public ModelAndView editar(@PathVariable(value = "id", required = true) Long id) {
		ModelAndView model = new ModelAndView("marca/marca_editar");
		model.addObject("entity", service.dados(id));
		return model;
	}
}