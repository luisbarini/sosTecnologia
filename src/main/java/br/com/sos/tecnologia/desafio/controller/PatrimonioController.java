package br.com.sos.tecnologia.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sos.tecnologia.desafio.entity.PatrimonioEntity;
import br.com.sos.tecnologia.desafio.service.MarcaService;
import br.com.sos.tecnologia.desafio.service.PatrimonioService;

@Controller
@RequestMapping("patrimonio")
public class PatrimonioController {

	@Autowired
	private PatrimonioService service;
	@Autowired
	private MarcaService marcaService;

	@GetMapping("listar/{mensagem}")
	public ModelAndView listarComMensagem(@PathVariable(required = false, value = "mensagem") String mensagem) {
		ModelAndView model = new ModelAndView("patrimonio/patrimonio_listar");
		model.addObject("mensagem", mensagem);
		return model;
	}

	@GetMapping("listar")
	public ModelAndView listar(@PathVariable(required = false, value = "mensagem") String mensagem) {
		return new ModelAndView("patrimonio/patrimonio_listar");
	}

	@GetMapping("")
	public ModelAndView cadastrar() {
		ModelAndView model = new ModelAndView("patrimonio/patrimonio_editar");
		model.addObject("entity", new PatrimonioEntity());
		model.addObject("marcas", marcaService.listar());
		return model;
	}

	@GetMapping("{id}")
	public ModelAndView editar(@PathVariable(value = "id", required = true) Long id) {
		ModelAndView model = new ModelAndView("patrimonio/patrimonio_editar");
		PatrimonioEntity entity = service.dados(id);
		model.addObject("entity", entity);
		model.addObject("marcaSelecionada", entity.getMarca().getId());
		model.addObject("marcas", marcaService.listar());
		return model;
	}
}
