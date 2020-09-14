package br.com.sos.tecnologia.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sos.tecnologia.desafio.entity.MarcaEntity;

public interface MarcaRepository extends JpaRepository<MarcaEntity, Long> {
	
	public List<MarcaEntity> findAllByOrderByDescricaoAsc();
	
	public int countByDescricao(String descricao);
	
	public int countByDescricaoAndIdNot(String descricao, Long id);
}
