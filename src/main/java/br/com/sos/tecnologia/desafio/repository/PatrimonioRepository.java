package br.com.sos.tecnologia.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sos.tecnologia.desafio.dto.consulta.PatrimonioInterfaceDTO;
import br.com.sos.tecnologia.desafio.entity.PatrimonioEntity;

public interface PatrimonioRepository extends JpaRepository<PatrimonioEntity, Long>{

	@Query(value = "select p.id, p.nome, p.tombo, p.descricao, m.descricao marcaDescricao, m.id marcaId "
				+ "from patrimonio p inner join marca m on m.id = p.marca_id order by p.tombo", nativeQuery = true)
	public List<PatrimonioInterfaceDTO> listar();
	
	@Query(value = "select ifnull(max(tombo)+1,1) from patrimonio", nativeQuery = true)
	public Integer novoTombo();
	
	public List<PatrimonioEntity> findAllByMarcaIdOrderByDescricao(Long marcaId);
	
	public int countByMarcaId(Long marcaId);
}
