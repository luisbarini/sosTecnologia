package br.com.sos.tecnologia.desafio.dto;

import br.com.sos.tecnologia.desafio.entity.PatrimonioEntity;
import br.com.sos.tecnologia.desafio.entity.MarcaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class PatrimonioAlterarDTO {
	private Long id;
	private String nome;
	private String descricao;
	private Long marcaId;

	public PatrimonioAlterarDTO(PatrimonioEntity entity) {
		if (entity != null) {
			this.id = entity.getId();
			this.nome = entity.getNome();
			this.descricao = entity.getDescricao();
			this.marcaId = entity.getMarca() != null
					? entity.getId()
					: null;
		}
	}

	public PatrimonioEntity converteParaEntity() {
		return PatrimonioEntity.builder().id(id).nome(nome).descricao(descricao).marca(
				marcaId != null ? MarcaEntity.builder().id(marcaId).build() : null)
				.build();
	}
}
