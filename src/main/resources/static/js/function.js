// Variável que receberá o objeto XMLHttpRequest
var req;
function validarDados(campo, valor, url) {
// Verificar o Browser
// Firefox, Google Chrome, Safari e outros
if(window.XMLHttpRequest) {
   req = new XMLHttpRequest();
}
// Internet Explorer
else if(window.ActiveXObject) {
   req = new ActiveXObject("Microsoft.XMLHTTP");
}
// Aqui vai o valor e o nome do campo que pediu a requisição.
///search/api/getSearchResult/{msg}
//var url = "ajax/validacao.php?campo="+campo+"&valor="+valor;
 url +=  + campo + "/" + valor
// Chamada do método open para processar a requisição
req.open("Get", url, true);
// Quando o objeto recebe o retorno, chamamos a seguinte função;
req.onreadystatechange = function() {
	
    // Exibe a mensagem "Verificando" enquanto carrega
    if(req.readyState == 1) {
        document.getElementById('campo_' + campo + '').innerHTML = '<font color="gray">Verificando...</font>';
    }
    // Verifica se o Ajax realizou todas as operações corretamente (essencial)
    if(req.readyState == 4 && req.status == 200) {
    // Resposta retornada pelo validacao.php
    var resposta = req.responseText;
   
   // habilitando e desabitando  o botão bem com o box de erro
    if(resposta == "") {
	document.getElementById("botao").disabled = false;
	document.getElementById("campo_email").style.display = "none";
     }else {
	  document.getElementById("botao").disabled = true;
	  document.getElementById("campo_email").style.display = "block";
  }
    
    // Abaixo colocamos a resposta na div do campo que fez a requisição
    document.getElementById('campo_'+ campo +'').innerHTML = resposta;
    
    
    }
}

req.send(null);
}
