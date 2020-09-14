package br.com.sos.tecnologia.desafio.dto;

import br.com.sos.tecnologia.desafio.entity.MarcaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class MarcaDTO {
	private Long id;
	private String descricao;

	public MarcaDTO(MarcaEntity entity) {
		if (entity != null) {
			this.id = entity.getId();
			this.descricao = entity.getDescricao();
		}
	}

	public MarcaEntity converteParaEntity() {
		return MarcaEntity.builder().id(id).descricao(descricao).build();
	}
}
