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
public class MarcaCadastrarDTO {
	private String descricao;

	public MarcaCadastrarDTO(MarcaEntity entity) {
		if (entity != null) {
			this.descricao = entity.getDescricao();
		}
	}

	public MarcaEntity converteParaEntity() {
		return MarcaEntity.builder().descricao(descricao).build();
	}
}
