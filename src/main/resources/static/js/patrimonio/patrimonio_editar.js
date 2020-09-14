function salvar(){
	let dto = {};
	dto.marca = {};
	if($('#input_id') != undefined)
		dto.id = $(`#input_id`).val();
	if($('#input_tombo') != undefined)
		dto.tombo = $(`#input_tombo`).val();	
	dto.nome = $('#input_nome').val();
	dto.descricao = $('#input_descricao').val();
	dto.marca.id = $('#select_marca').val();
	
	if($('#input_id') != undefined)
		dto.id = $('#input_id').val();
		
	PatrimonioRemoteProxy.salvar(dto, function (resultado) {
		if(resultado.sucesso){
			window.location.href=`${context}patrimonio/listar/${resultado.mensagem}`;
		}else{
			alertaErro('Erro ao cadastrar o patrimônio', resultado.mensagem);
		}
	});
	return false;
}