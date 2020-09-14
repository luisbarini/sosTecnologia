function salvar(){
	let marcaDTO = {};
	marcaDTO.descricao = $('#input_descricao').val();
	if($('#input_descricao') != undefined)
		marcaDTO.id = $('#input_id').val();
	MarcaRemoteProxy.salvar(marcaDTO, function (resultado) {
		if(resultado.sucesso){
			window.location.href=`${context}marca/listar/${resultado.mensagem}`;
		}else{
			alertaErro('Erro ao cadastrar a marca', resultado.mensagem);
		}
	});
	return false;
}