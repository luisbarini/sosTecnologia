package br.com.sos.tecnologia.desafio.remoteProxy;

import java.util.List;
import java.util.stream.Collectors;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Service;

import br.com.sos.tecnologia.desafio.dto.MarcaDTO;
import br.com.sos.tecnologia.desafio.dto.PatrimonioDTO;
import br.com.sos.tecnologia.desafio.dto.RespostaDTO;
import br.com.sos.tecnologia.desafio.entity.PatrimonioEntity;
import br.com.sos.tecnologia.desafio.service.PatrimonioService;

@Service
@RemoteProxy
public class PatrimonioRemoteProxy {
	private final PatrimonioService service;

	public PatrimonioRemoteProxy(PatrimonioService service) {
		this.service = service;
	}

	@RemoteMethod
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

	@RemoteMethod
	public RespostaDTO excluir(Long id) {
		try {
			service.excluir(PatrimonioEntity.builder().id(id).build());
			return RespostaDTO.builder().mensagem("Patrimônio excluído com sucesso").sucesso(true).build();
		} catch (Exception e) {
			return RespostaDTO.builder().mensagem("Erro ao tentar excluir esse patrimônio: " + e.getMessage())
					.sucesso(false).build();
		}
	}

	@RemoteMethod
	public RespostaDTO salvar(PatrimonioDTO patrimonioDTO) {
		PatrimonioEntity entity = patrimonioDTO.converteParaEntity();
		service.salvar(entity);
		try {
			if (patrimonioDTO.getId() == null)
				return RespostaDTO.builder().mensagem("O novo patrimônio recebeu o tombo: " + entity.getTombo())
						.sucesso(true).build();
			else
				return RespostaDTO.builder().mensagem("Dados do patrimônio alterados com sucesso").sucesso(true)
						.build();
		} catch (Exception e) {
			return RespostaDTO.builder().mensagem("Erro ao tentar salvar o patrimônio: " + e.getMessage())
					.sucesso(false).build();
		}
	}
}
