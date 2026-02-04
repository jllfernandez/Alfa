function parseJson22(obj) {
	var result = obj["result"];
	return result;
}

function parseJsonByKey(obj, key) {
	var result = obj[key];
	return result;
}

function parseJson(obj) {
	return parseJsonByKey(obj, "result");
}


function existSia(sia) {
	var json = new Object();
	json.numeroSIA = sia;

	peticionPostAjax('/Bie/validaSia', json, cbExistSia);
}

function getSia(sia) {
	peticionGetAjax('/Bie/validaSia/16', cbExistSia);
}

function cbExistSia(response) {
	alert("result --->" + response);
}


function peticionPostAjax(action, json, callback) {
	// METODO POST
	fetch(action, {
		method: "POST",
		body: JSON.stringify(json),
		headers: { "Content-type": "application/json; charset=UTF-8" }
	})
		.then(response => response.json())  // convertir a json
		.then(response => callback(parseJson(response)))
		.catch(err => console.log(err));
}

function peticionGetAjax(action, callback) {
	// METODO GET
	fetch(action)
		.then(response => response.json())  // convertir a json
		.then(response => callback(parseJson(response)))
		.catch(err => console.log(err));
}

function borraDiv(target) {
    document.getElementById(target).innerHTML = "";   
    // document.getElementById(target).remove();
     //var element = document.getElementById(target);
	 //element.parentNode.removeChild(element);
}


function mostrarDiv(target) {
  var opciones = document.getElementsByClassName('opc');
  var tagt = document.getElementById(target);
  var esVisible = tagt.style.display == 'block';

  //ocultar todo
  for (var i = 0; i < opciones.length; i++) {
    opciones[i].style.display = 'none';
  }

  //toggle actual
  tagt.style.display = esVisible ? 'none' : 'block';

  return false;
}

function onOffDiv(target, esVisible) {
  //var opciones = document.getElementsByClassName('opc');
  var tagt = document.getElementById(target);
  //var esVisible = tagt.style.display == 'block';

  //ocultar todo
//  for (var i = 0; i < opciones.length; i++) {
  //  opciones[i].style.display = 'none';
//  }

  //toggle actual
  if(true == esVisible) {
	   tagt.style.display = 'block';
  } else {
	   tagt.style.display = 'none';
  }
  
  return true;
}


// OLD WAY
function existSiaOld(sia) {
	var result = "";

	/*
	// METODO GET
	var url = '/validaSia/'+sia;
	fetch(url)
		.then(result => result.json())  // convertir a json
		.then(result =>parseJson(result))    	
	    
	   */

	// METODO POST
	var json = new Object();
	json.numeroSIA = sia;
	//var json = '{"numeroSIA", sia}';

	fetch('/validaSia', {
		method: "POST",
		body: JSON.stringify(json),
		headers: { "Content-type": "application/json; charset=UTF-8" }
	})
		.then(result => result.json())  // convertir a json
		.then(result => parseJson(result))
		.catch(err => console.log(err));

	return result;
}
