/**
 * 
 */

function haz1() {
	alert ("---> 1 ");
}

function haz2() {
	alert ("---> 2");
}

function haz3() {
	alert ("---> 3");
}

function haz4() {
	alert ("---> 4");
}

function haz5() {
	alert ("---> 5");
}

function haz(id, type) {
	alert("Pulsado un item de id " + id + " y de tipo " + type);
}

function getAction(id, type) {
	//alert("Pulsado un item de id " + id + " y de tipo " + type);
	var action = "javascript:haz("+id+", "+type+");";
/*
	if(type=="1") {
	}
	if(type=="2") {
		action = "javascript:haz(id, type);";
	}
	if(type=="3") {
		action = "javascript:haz(id, type);";
	}
	if(type=="4") {
		action = "javascript:haz(id, type);";
	}
	if(type=="5") {
		action = "javascript:haz(id, type);";
	}
	*/
	return action;
}
