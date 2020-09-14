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

import br.com.sos.tecnologia.desafio.dto.MarcaDTO;
import br.com.sos.tecnologia.desafio.dto.PatrimonioAlterarDTO;
import br.com.sos.tecnologia.desafio.dto.PatrimonioDTO;
import br.com.sos.tecnologia.desafio.dto.PatrimonioCadastrarDTO;
import br.com.sos.tecnologia.desafio.dto.RespostaDTO;
import br.com.sos.tecnologia.desafio.entity.PatrimonioEntity;
import br.com.sos.tecnologia.desafio.service.MarcaService;
import br.com.sos.tecnologia.desafio.service.PatrimonioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("patrimonios")
public class PatrimonioRestController {

	private PatrimonioService service;
	@Autowired
	private MarcaService marcaService;

	public PatrimonioRestController(PatrimonioService service) {
		this.service = service;
	}

	@GetMapping("")
	public List<PatrimonioDTO> listar() {
		return service
				.listar().stream().map(
						item -> PatrimonioDTO.builder().id(item.getId()).descricao(item.getDescricao())
								.tombo(item.getTombo()).nome(item.getNome())
								.marca(item.getMarcaId() != null ? MarcaDTO.builder()
										.descricao(item.getMarcaDescricao()).id(item.getMarcaId()).build() : null)
								.build())
				.collect(Collectors.toList());
	}

	@GetMapping("{id}")
	public PatrimonioDTO dados(@PathVariable(required = true, value = "id") Long id) {
		return new PatrimonioDTO(service.dados(id));
	}

	@PostMapping("")
	public RespostaDTO salvar(@RequestBody(required = true) PatrimonioCadastrarDTO patrimonioDTO) {
		PatrimonioEntity entity = patrimonioDTO.converteParaEntity();
		try {
			service.salvar(entity);
			return RespostaDTO.builder().mensagem("O novo patrimônio recebeu o tombo: " + entity.getTombo())
					.sucesso(true).build();
		} catch (Exception e) {
			return RespostaDTO.builder().mensagem("Erro ao tentar salvar o patrimônio: " + e.getMessage())
					.sucesso(false).build();
		}
	}

	@PutMapping("")
	public RespostaDTO alterar(@RequestBody(required = true) PatrimonioAlterarDTO patrimonioDTO) {
		try {
			PatrimonioEntity entity = service.dados(patrimonioDTO.getId());
			if (entity == null)
				return RespostaDTO.builder().mensagem(
						"Erro ao tentar alterar os dados do patrimônio, pois foi selecionado um patrimônio que não existe mais no banco")
						.sucesso(false).build();

			entity.setDescricao(patrimonioDTO.getDescricao());
			entity.setNome(patrimonioDTO.getNome());
			entity.setMarca(marcaService.dados(patrimonioDTO.getMarcaId()));
			service.salvar(entity);
			return RespostaDTO.builder().mensagem("Alteração no patrimônio realizada com sucesso").sucesso(true)
					.build();
		} catch (Exception e) {
			return RespostaDTO.builder().mensagem("Erro ao tentar alterar os dados do patrimônio: " + e.getMessage())
					.sucesso(true).build();
		}
	}

	@DeleteMapping("")
	public RespostaDTO excluir(@RequestBody(required = true) Long id) {
		try {
			service.excluir(PatrimonioEntity.builder().id(id).build());
			return RespostaDTO.builder().mensagem("Exclusão realizada com sucesso").sucesso(true).build();
		} catch (Exception e) {
			return RespostaDTO.builder().mensagem("Erro ao tentar excluir esse patrimônio: " + e.getMessage())
					.sucesso(false).build();
		}
	}
}
