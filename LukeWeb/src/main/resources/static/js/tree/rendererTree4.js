/**
 * 
 */


function getRoottree4() {
	var item = "/img/tree4/imgfolder.gif";
	return item;
}

function getOpenNodetree4(type) {
	var item = "/img//folderopen.gif";
	if(type=="2") {
		//item ="/img/musicfolder.gif";
		item ="/img/search.png";
	}
	if(type=="1") {
		item ="/img/globe.gif";
	}
	return item;
}


function actiontree4(id, type) {
	//alert("Pulsado un item de id " + id + " y de tipo " + type + " del Arbol " + code);
	mountArbol23();
}

