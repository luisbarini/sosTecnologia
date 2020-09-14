package br.com.sos.tecnologia.desafio.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class RespostaDTO {
	public String mensagem;
	public boolean sucesso;
}