/**************************************************************************
	Copyright (c) 2001-2003 Geir Landr� (drop@destroydrop.com)
	JavaScript Tree - www.destroydrop.com/hjavascripts/tree/
	Version 0.96	

	This script can be used freely as long as all copyright messages are
	intact.
**************************************************************************/

// Arrays for nodes and icons
var nodes = new Array();;
var openNodes = new Array();
var icons = new Array(6);

// Loads all icons that are used in the tree
function preloadIcons() {
	icons[0] = new Image();
	icons[0].src = getBase() + "/img/plus.gif";

	icons[1] = new Image();
	icons[1].src = getBase() + "/img/plusbottom.gif";

	icons[2] = new Image();
	icons[2].src = getBase() + "/img/minus.gif";

	icons[3] = new Image();
	icons[3].src = getBase() + "/img/minusbottom.gif";

	icons[4] = new Image();
	icons[4].src = getBase() + "/img/folder.gif";

	icons[5] = new Image();
	icons[5].src = getBase() + "/img/folderopen.gif";
}

function getBase() {
	return "../";
}

function mount(img) {
	//var a1 = location.href.split('Bie')[0];
	return "src=" + getBase() + img;
}


function createElementTree(element, arrName, startNode, openNode) {
	var miDiv = document.getElementById(element);
	nodes = arrName;
	var texto = "";
	
	if (nodes.length > 0) {
		preloadIcons();

		if (startNode == null) startNode = 0;
		if (openNode != 0 || openNode != null) setOpenNodes(openNode);


		if (startNode != 0) {
			var nodeValues = nodes[getArrayId(startNode)].split("|");
			var folderopen = mount("/img/folderopen.gif");
			texto = texto +"<a href=\"" + nodeValues[3] + "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\"><img src=\"img" + folderopen + " align=\"absbottom\" alt=\"\" />" + nodeValues[2] + "</a><br />";
		} else {
			texto = texto +"<img " +  mount("/img/base.gif") + " align=\"absbottom\" alt=\"\" />Websites<br />";
		}

		var recursedNodes = new Array();
		addNode(startNode, recursedNodes);

	}

	texto = texto +"<input type='checkbox' name='aaaa' onClick='javascript:c()';>";
	miDiv.innerHTML=texto;

}

// Create the tree
function createTree(arrName, startNode, openNode) {
	nodes = arrName;
	//document.write("<form name='formulario'>");

	if (nodes.length > 0) {
		preloadIcons();

		if (startNode == null) startNode = 0;
		if (openNode != 0 || openNode != null) setOpenNodes(openNode);


		if (startNode != 0) {
			var nodeValues = nodes[getArrayId(startNode)].split("|");
			var folderopen = mount("/img/folderopen.gif");
			document.write("<a href=\"" + nodeValues[3] + "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\"><img src=\"img" + folderopen + " align=\"absbottom\" alt=\"\" />" + nodeValues[2] + "</a><br />");
		} else {
			document.write("<img " +  mount("/img/base.gif") + " align=\"absbottom\" alt=\"\" />Websites<br />");
		}

		var recursedNodes = new Array();
		addNode(startNode, recursedNodes);

	}


	document.write("<input type='checkbox' name='aaaa' onClick='javascript:c()';>");
	//document.write("<input type='button' name='abbb' value='Dato' onClick='javascript:c()';>");

	//document.write("</form>");
}
function c() {
	alert("-->" + "Ejemplo");
}
function d(s) {
	alert("El elemento -->" + s + " esta a --->" + document.getElementById('registro' + s).checked);
}

// Returns the position of a node in the array
function getArrayId(node) {
	for (i = 0; i < nodes.length; i++) {
		var nodeValues = nodes[i].split("|");
		if (nodeValues[0] == node) return i;
	}
}
// Puts in array nodes that will be open
function setOpenNodes(openNode) {
	for (i = 0; i < nodes.length; i++) {
		var nodeValues = nodes[i].split("|");
		if (nodeValues[0] == openNode) {
			openNodes.push(nodeValues[0]);
			setOpenNodes(nodeValues[1]);
		}
	}
}
// Checks if a node is open
function isNodeOpen(node) {
	for (i = 0; i < openNodes.length; i++)
		if (openNodes[i] == node) return true;
	return false;
}
// Checks if a node has any children
function hasChildNode(parentNode) {
	for (i = 0; i < nodes.length; i++) {
		var nodeValues = nodes[i].split("|");
		if (nodeValues[1] == parentNode) return true;
	}
	return false;
}
// Checks if a node is the last sibling
function lastSibling(node, parentNode) {
	var lastChild = 0;
	for (i = 0; i < nodes.length; i++) {
		var nodeValues = nodes[i].split("|");
		if (nodeValues[1] == parentNode)
			lastChild = nodeValues[0];
	}
	if (lastChild == node) return true;
	return false;
}
// Adds a new node to the tree
function addNode(parentNode, recursedNodes) {
	for (var i = 0; i < nodes.length; i++) {

		var nodeValues = nodes[i].split("|");
		if (nodeValues[1] == parentNode) {

			var ls = lastSibling(nodeValues[0], nodeValues[1]);
			var hcn = hasChildNode(nodeValues[0]);
			var ino = isNodeOpen(nodeValues[0]);

			var line = null;
			// Write out line & empty icons
			for (g = 0; g < recursedNodes.length; g++) {
				if (recursedNodes[g] == 1)
					line = mount("/img/line.gif");
				else 
					line = mount("/img/empty.gif");

				document.write("<img "+ line + " align=\"absbottom\" alt=\"\" />");

			}

			// put in array line & empty icons
			if (ls) recursedNodes.push(0);
			else recursedNodes.push(1);

			// Write out join icons
			if (hcn) {
				var bott = null;
				if (ls) {
					if (ino) {
						bott = mount("/img/minusbottom.gif");
					}
					else {
						bott = mount("/img/plusbottom.gif");
					}
					document.write("<a href=\"javascript: oc(" + nodeValues[0] + ", 1);\"><img id=\"join" + nodeValues[0] + "\" ");
					document.write("<img " + bott + " align=\"absbottom\" alt=\"Open/Close node\" /></a>");
				} else {
					var bottn = null;
					
					if (ino) {
							bottn = mount("/img/minus.gif");
						}
					else {
							bottn = mount("/img/plus.gif");
						}
						
					document.write("<a href=\"javascript: oc(" + nodeValues[0] + ", 0);\"><img id=\"join" + nodeValues[0] + "\" "); //src=\"static/img/"
					document.write("<img " + bottn + " align=\"absbottom\" alt=\"Open/Close node\" /></a>");
				}
			} else {
				var imagen1 = mount("/img/joinbottom.gif");
				var imagen2 = mount("/img/join.gif");
			
				if (ls) document.write("<img " + imagen1 + " align=\"absbottom\" alt=\"\" />");//src=\"static/img/joinbottom.gif\" 
				else document.write("<img  " + imagen2 + " align=\"absbottom\" alt=\"\" />"); //src=\"img/join.gif\"
			}

			document.write("<a href=\"" + nodeValues[3] + "\" onmouseover=\"window.status='" + nodeValues[2] + "';return true;\" onmouseout=\"window.status=' ';return true;\">");

			//theJoin.src = "javascript:doit2(icons[0]);";
			// Write out folder & page icons
			if (hcn) {
				var folder = null;
				if (ino) {
					//document.write("open");
					folder =  mount("/img/folderopen.gif");
				} else {
					folder =  mount("/img/folder.gif");
				}
				document.write("<img id=\"icon" + nodeValues[0] + "\" "); //src=\"static/img/folder"
				document.write(folder +" align=\"absbottom\" alt=\"Folder\" />");
			} else {
				document.write("<input type='checkbox' name='registro" + nodeValues[0] + "' ");
				document.write("onClick='javascript:d(" + nodeValues[0] + ")';>");
				//var page = mount("/img/page.gif");
				document.write("&nbsp;<img id=\"icon" + nodeValues[0] + "\" " + mount("/img/page.gif") + " align=\"absbottom\" alt=\"Page\" />");
			}

			//document.write("<input type='Checkbox' name='aa'  ");	
			// Write out node name
			document.write(nodeValues[2]);

			// End link
			document.write("</a><br />");

			// If node has children write out divs and go deeper
			if (hcn) {
				document.write("<div id=\"div" + nodeValues[0] + "\"");
				if (!ino) document.write(" style=\"display: none;\"");
				document.write(">");
				addNode(nodeValues[0], recursedNodes);
				document.write("</div>");
			}

			// remove last line or empty icon 
			recursedNodes.pop();
		}
	}
}
// Opens or closes a node
function oc(node, bottom) {
	var theDiv = document.getElementById("div" + node);
	var theJoin = document.getElementById("join" + node);
	var theIcon = document.getElementById("icon" + node);

	if (theDiv.style.display == 'none') {
		if (bottom == 1) theJoin.src = icons[3].src;
		else theJoin.src = icons[2].src;

		theIcon.src = icons[5].src;
		theDiv.style.display = '';
	} else {
		if (bottom == 1) theJoin.src = icons[1].src;
		else theJoin.src = icons[0].src;
		theIcon.src = icons[4].src;
		theDiv.style.display = 'none';
	}
}
// Push and pop not implemented in IE
if (!Array.prototype.push) {
	function array_push() {
		for (var i = 0; i < arguments.length; i++)
			this[this.length] = arguments[i];
		return this.length;
	}
	Array.prototype.push = array_push;
}
if (!Array.prototype.pop) {
	function array_pop() {
		lastElement = this[this.length - 1];
		this.length = Math.max(this.length - 1, 0);
		return lastElement;
	}
	Array.prototype.pop = array_pop;
}
