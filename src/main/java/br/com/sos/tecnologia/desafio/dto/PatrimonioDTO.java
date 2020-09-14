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
public class PatrimonioDTO {
	private Long id;
	private Integer tombo;
	private String nome;
	private String descricao;
	private MarcaDTO marca;

	public PatrimonioDTO(PatrimonioEntity entity) {
		if (entity != null) {
			this.id = entity.getId();
			this.tombo = entity.getTombo();
			this.nome = entity.getNome();
			this.descricao = entity.getDescricao();
			this.marca = entity.getMarca() != null
					? MarcaDTO.builder().id(entity.getMarca().getId()).descricao(entity.getMarca().getDescricao())
							.build()
					: null;
		}
	}

	public PatrimonioEntity converteParaEntity() {
		return PatrimonioEntity.builder().id(id).tombo(tombo).nome(nome).descricao(descricao).marca(
				marca != null ? MarcaEntity.builder().id(marca.getId()).descricao(marca.getDescricao()).build() : null)
				.build();
	}
}
