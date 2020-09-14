package br.com.sos.tecnologia.desafio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sos.tecnologia.desafio.dto.consulta.PatrimonioInterfaceDTO;
import br.com.sos.tecnologia.desafio.entity.PatrimonioEntity;
import br.com.sos.tecnologia.desafio.repository.PatrimonioRepository;

@Service
public class PatrimonioService {

	private final PatrimonioRepository repository;

	public PatrimonioService(PatrimonioRepository repository) {
		super();
		this.repository = repository;
	}

	public List<PatrimonioInterfaceDTO> listar() {
		return repository.listar();
	}

	public PatrimonioEntity dados(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void salvar(PatrimonioEntity entity) {
		// Procura o último tombo e soma mais um
		if (entity.getTombo() == null)
			entity.setTombo(repository.novoTombo());
		// Salva o patrimônio
		repository.save(entity);
	}

	public void excluir(PatrimonioEntity entity) {
		repository.delete(entity);
	}

	public List<PatrimonioEntity> listarAtravesDaMarca(Long marcaId) {
		return repository.findAllByMarcaIdOrderByDescricao(marcaId);
	}
	
	public int totalDePatrimoniosDeUmaMarca(Long marcaId) {
		return repository.countByMarcaId(marcaId);
	}
	
}
