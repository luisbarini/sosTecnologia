package br.com.sos.tecnologia.desafio.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "marca")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class MarcaEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 20)
	private Long id;
	
	@Column(name = "descricao", nullable = false, length = 2000)
	private String descricao;
	
	public String getDescricao() {
		return this.descricao!=null?this.descricao.trim():null;
	}
}