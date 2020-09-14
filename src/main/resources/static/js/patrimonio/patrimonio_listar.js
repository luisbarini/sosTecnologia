$(document).ready(function() {
	listar();
});

function listar(){
	PatrimonioRemoteProxy.listar(function (resultado) {
		console.log(resultado);
		for(var i=0; i < resultado.length; i++){
			var linha = `<tr id="linha-${resultado[i].id}">`;
			linha += `<td>${resultado[i].tombo}</td>`;
			linha += `<td>${resultado[i].nome}</td>`;
			linha += `<td>${resultado[i].descricao}</td>`;
			linha += `<td>${resultado[i].marca.descricao}</td>`;
			linha += `<td><a class="btn btn-sm btn-primary" href="javascript:editar(${resultado[i].id})"><span class="fa fa-edit"></span></a> `;
			linha += `<a class="btn btn-sm btn-danger" href="javascript:perguntaSeDesejaExcluir(${resultado[i].id})"><span class="fa fa-trash"></span></a></td>`		
			linha += `</tr>`;
			$('#tabela_consulta').append(linha);
		}
    });
}

function perguntaSeDesejaExcluir(id){
	alertaPerigo('Realizar exclusão', "Deseja realmente excluir esse patrimônio?", `excluir(${id})`);
}

function excluir(id){
	// Retira a modal da tela (alert)
	$('#modal-danger').modal('hide');
	
	PatrimonioRemoteProxy.excluir(id, function (resultado) {
		if(resultado.sucesso){
			alerta('Exclusão realizada com sucesso',resultado.mensagem);
			$("#linha-"+id).remove();
		}else{
			alertaErro('Erro ao tentar excluir a marca',resultado.mensagem)
		}
	});
}