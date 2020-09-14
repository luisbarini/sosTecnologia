package br.com.sos.tecnologia.desafio.restController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sos.tecnologia.desafio.dto.MarcaCadastrarDTO;
import br.com.sos.tecnologia.desafio.dto.MarcaDTO;
import br.com.sos.tecnologia.desafio.dto.PatrimonioDTO;
import br.com.sos.tecnologia.desafio.dto.RespostaDTO;
import br.com.sos.tecnologia.desafio.entity.MarcaEntity;
import br.com.sos.tecnologia.desafio.service.MarcaService;
import br.com.sos.tecnologia.desafio.service.PatrimonioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("marcas")
public class MarcaRestController {

	private MarcaService service;
	@Autowired
	private PatrimonioService patrimonioService;

	public MarcaRestController(MarcaService service) {
		this.service = service;
	}

	@GetMapping("")
	public List<MarcaDTO> listar() {
		return service.listar().stream().map(item -> {
			return new MarcaDTO(item);
		}).collect(Collectors.toList());
	}

	@GetMapping("{id}")
	public MarcaDTO dados(@PathVariable(required = true, value = "id") Long id) {
		return new MarcaDTO(service.dados(id));
	}

	@GetMapping("{id}/patrimonios")
	public List<PatrimonioDTO> listarPatrimoniosDeUmaMarca(@PathVariable(required = true, value = "id") Long id) {
		return patrimonioService.listarAtravesDaMarca(id).stream().map(item -> {
			return new PatrimonioDTO(item);
		}).collect(Collectors.toList());
	}

	@PostMapping("")
	public RespostaDTO salvar(@RequestBody(required = true) MarcaCadastrarDTO marcaDTO) {
		try {
			service.salvar(marcaDTO.converteParaEntity());
			return RespostaDTO.builder().mensagem("Nova marca cadastrada com sucesso").sucesso(true).build();
		} catch (Exception e) {
			return RespostaDTO.builder().mensagem("Erro ao tentar cadastrar a nova marca: " + e.getMessage())
					.sucesso(false).build();
		}
	}

	@PutMapping("")
	public RespostaDTO alterar(@RequestBody(required = true) MarcaDTO marcaDTO) {
		try {
			service.alterar(marcaDTO.converteParaEntity());
			return RespostaDTO.builder().mensagem("Dados da marca alterados com sucesso").sucesso(true).build();
		} catch (Exception e) {
			return RespostaDTO.builder().mensagem("Erro ao tentar alterar os dados da marca: " + e.getMessage())
					.sucesso(false).build();
		}
	}

	@DeleteMapping("{id}")
	public RespostaDTO excluir(@PathVariable(value = "id", required = true) Long id) {
		try {
			MarcaEntity entity = service.dados(id);
			if (entity == null)
				return RespostaDTO.builder().mensagem("Erro, você está tentando excluir uma marca que não existe mais")
						.sucesso(false).build();
			service.excluir(entity);
			return RespostaDTO.builder().mensagem("Marca excluída com sucesso").sucesso(true).build();
		} catch (Exception e) {
			return RespostaDTO.builder().mensagem("Erro ao tentar excluir a marca: " + e.getMessage()).sucesso(false)
					.build();
		}
	}
}
