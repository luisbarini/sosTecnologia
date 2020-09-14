package br.com.sos.tecnologia.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sos.tecnologia.desafio.entity.MarcaEntity;
import br.com.sos.tecnologia.desafio.repository.MarcaRepository;

@Service
public class MarcaService {

	private final MarcaRepository repository;
	@Autowired
	private PatrimonioService patrimonioService;
	
	public MarcaService(MarcaRepository repository) {
		this.repository = repository;
	}
	
	public MarcaEntity dados(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<MarcaEntity> listar() {
		return repository.findAllByOrderByDescricaoAsc();
	}

	public void salvar(MarcaEntity entity) throws Exception {
		if(repository.countByDescricao(entity.getDescricao()) > 0)
			throw new Exception("Já existe uma Marca com esta mesma descrição");
		repository.save(entity);		
	}

	public void alterar(MarcaEntity entity) throws Exception {
		if(repository.countByDescricaoAndIdNot(entity.getDescricao(), entity.getId()) > 0)
			throw new Exception("Já existe uma Marca com esta mesma descrição");
		repository.save(entity);		
	}
	
	public void excluir(MarcaEntity entity) throws Exception {
		// Verifica se existe algum patrimônio vinculado a essa marca
		if(patrimonioService.totalDePatrimoniosDeUmaMarca(entity.getId()) > 0)
			throw new Exception("Não é possível excluir essa marca, pois existem patrimônios que a utilizam");
		repository.delete(entity);
	}	
}