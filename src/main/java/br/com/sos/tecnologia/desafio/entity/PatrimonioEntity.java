package br.com.sos.tecnologia.desafio.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "patrimonio")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class PatrimonioEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 20)
	private Long id;
	
	@Column(name = "tombo", nullable = false, length = 10)
	private Integer tombo;
	
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	@Column(name = "descricao", nullable = false, length = 2000)
	private String descricao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "marca_id", nullable = false)
	private MarcaEntity marca;
}
