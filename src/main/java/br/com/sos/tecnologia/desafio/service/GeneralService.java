package br.com.sos.tecnologia.desafio.service;

import java.util.List;

public interface GeneralService <Entity, ListarDTO>{

	public List<ListarDTO> listar();

	public Entity dados(Long id);

	public void salvar(Entity entity);

	public void excluir(Entity entity);

}
