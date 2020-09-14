$(document).ready(function() {
	listar();
});

function listar(){
	MarcaRemoteProxy.listar(function (resultado) {
		console.log(resultado);
		for(var i=0; i < resultado.length; i++){
			var linha = `<tr id="linha-${resultado[i].id}">`;
			linha += `<td>${resultado[i].id}</td>`;
			linha += `<td>${resultado[i].descricao}</td>`;
			linha += `<td><a class="btn btn-sm btn-primary" href="javascript:editar(${resultado[i].id})"><span class="fa fa-edit"></span></a> `;
			linha += `<a class="btn btn-sm btn-danger" href="javascript:perguntaSeDesejaExcluir(${resultado[i].id})"><span class="fa fa-trash"></span></a></td>`		
			linha += `</tr>`;
			$('#tabela_consulta').append(linha);
		}
    });
}

function perguntaSeDesejaExcluir(id){
	alertaPerigo('Deseja realmente excluir esta marca?', "Deseja realmente excluir essa marca?", `excluir(${id})`);
}

function excluir(id){
	// Retira a modal da tela (alert)
	$('#modal-danger').modal('hide');
	
	MarcaRemoteProxy.excluir(id, function (resultado) {
		if(resultado.sucesso){
			alerta('Exclusão realizada com sucesso',resultado.mensagem);
			$("#linha-"+id).remove();
		}else{
			alertaErro('Erro ao tentar excluir a marca',resultado.mensagem)
		}
	});
}

function editar(id){
	window.location.href=`${context}marca/${id}`;
}