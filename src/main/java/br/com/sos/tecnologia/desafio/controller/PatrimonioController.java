package br.com.sos.tecnologia.desafio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("patrimonio")
public class PatrimonioController {
	
	@GetMapping("listar/{mensagem}")
	public ModelAndView listarComMensagem(@PathVariable(required = false, value = "mensagem") String mensagem) {
		ModelAndView model = new ModelAndView("patrimonio/patrimonio_listar");
		model.addObject("mensagem",mensagem);
		return model;
	}
	
	@GetMapping("listar")
	public ModelAndView listar(@PathVariable(required = false, value = "mensagem") String mensagem) {
		return new ModelAndView("patrimonio/patrimonio_listar");
	}
}
