package br.com.sos.tecnologia.desafio.remoteProxy;

import java.util.List;
import java.util.stream.Collectors;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Service;

import br.com.sos.tecnologia.desafio.dto.MarcaDTO;
import br.com.sos.tecnologia.desafio.dto.RespostaDTO;
import br.com.sos.tecnologia.desafio.entity.MarcaEntity;
import br.com.sos.tecnologia.desafio.service.MarcaService;

@Service
@RemoteProxy
public class MarcaRemoteProxy {

	private final MarcaService service;

	public MarcaRemoteProxy(MarcaService service) {
		this.service = service;
	}

	@RemoteMethod
	public List<MarcaDTO> listar() {
		return service.listar().stream().map(item -> {
			return new MarcaDTO(item);
		}).collect(Collectors.toList());
	}

	@RemoteMethod
	public RespostaDTO excluir(Long id) {
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

	@RemoteMethod
	public RespostaDTO salvar(MarcaDTO marcaDTO) {
		try {
			if (marcaDTO.getId() == null) {
				service.salvar(marcaDTO.converteParaEntity());
				return RespostaDTO.builder().mensagem("Nova marca cadastrada com sucesso").sucesso(true).build();
			} else {
				service.alterar(marcaDTO.converteParaEntity());
				return RespostaDTO.builder().mensagem("Registro alterado com sucesso").sucesso(true).build();
			}
		} catch (Exception e) {
			return RespostaDTO.builder().mensagem("Erro ao tentar cadastrar a nova marca: " + e.getMessage())
					.sucesso(false).build();
		}
	}
}
