$(document).ready(function() {
	if(mensagem!=null) alerta('Sucesso', mensagem);
});

function alerta(titulo, mensagem) {
	$('#modal-default-title').html(titulo);
	$('#modal-default-message').html(mensagem);
	$('#modal-default').modal();
}

function alertaErro(titulo, mensagem) {
	$('#modal-erro-title').html("<i class='icon fa fa-ban'>&nbsp;</i>"+titulo);
	$('#modal-erro-mensagem').html(mensagem);
	$('#modal-erro').modal();
}

function alertaPerigo(titulo, mensagem, funcao) {
	$('#modal-danger-title').html(titulo);
	$('#modal-danger-message').html(mensagem);
	$('#modal-danger-button').attr("onClick",funcao);
	$('#modal-danger').modal();
}