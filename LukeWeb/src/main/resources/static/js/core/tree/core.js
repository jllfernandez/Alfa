/**
 * 
 */
const comillas = "\"";

var innerHtml = "";

var openNodes = new Array();

function getBase() {
	return "../";
}

function write(text) {
	innerHtml = innerHtml + text;
}

function rewriteComponent(target) {
	borraDiv(target);
	document.getElementById(target).innerHTML = innerHtml;
}

function mount(img) {
	return "src=" + getBase() + img;
}

// Create the tree
function createTree(target, arrName, startNode, openNode) {
	nodes = arrName;
	innerHtml = "";
	
	//if (nodes.length > 0) {
	
		if (startNode == null) startNode = 0;
		if (openNode != 0 || openNode != null) setOpenNodes(openNode);


		if (startNode != 0) {
			var nodeValues = nodes[getArrayId(startNode)].split("|");
			var folderopen = mount(getOpenNode(target, nodeValues[3]));

			write("<a href="+comillas+ getAction( target, nodeValues[0], nodeValues[3]) + comillas+ " onmouseover=" + comillas+ "window.status='" + nodeValues[2] + "';" + comillas+ " onmouseout=" + comillas+ "window.status=' ';"+comillas+"><img src=" + comillas+ "img" + folderopen + " align=" + comillas+ "absbottom" + comillas+ " alt=" + comillas+ comillas+ " />" + nodeValues[2] + "</a><br />");
		} else {
			write("<img " +  mount(getRoot(target)) + " align=" + comillas+ "absbottom" + comillas+ " alt="+comillas + comillas+ "/>Websites<br />");
		}

		var recursedNodes = new Array();
	 	
		addNode(target, startNode, recursedNodes);
		
	//}

	rewriteComponent(target);
}
function c() {
	alert("-->" + "Ejemplo");
}
function d(s) {
	alert("El elemento -->" + s + " esta a --->" + document.getElementById("registro" + s).checked);
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
function addNode(target, parentNode, recursedNodes) {

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
					line = mount(getLine(target));
				else 
					line = mount(getEmpty(target));

				write("<img "+ line + " align=" + comillas+ "absbottom" + comillas+ " alt=" + comillas+ comillas+ " />");
			}

			// put in array line & empty icons
			if (ls) recursedNodes.push(0);
			else recursedNodes.push(1);

			// Write out join icons
			if (hcn) {
				var bott = null;
				if (ls) {
					if (ino) {
						bott = mount(getCloseSonsBottom(target));
					}
					else {
						bott = mount(getMoreSonsBottom(target));
					}
					
					write("<a href="+comillas+"javascript: oc('" + target+"', '"+ target +"_" + nodeValues[0]  + "', 1, "+ nodeValues[3] +");"+comillas+"><img id="+comillas+"join" +  target + "_" + nodeValues[0] + comillas );
					write("<img  " + bott + " align=" + comillas+ "absbottom" + comillas+ " alt=" + comillas+ "Open/Close node" + comillas+ " /></a>");
				
				} else {
					var bottn = null;
					
					if (ino) {
							bottn = mount(getCloseSons(target));
						}
					else {
							bottn = mount(getMoreSon(target));
						}
					write("<a href=" + comillas+ "javascript: oc('" + target+"', '"+ target +"_" + nodeValues[0]  + "', 0, "+ nodeValues[3] +");" + comillas+ "><img id=" + comillas+ "join" + target + "_"+ nodeValues[0] +  "<img  " + bottn + " align=" + comillas+ "absbottom" + comillas+ " alt=" + comillas+ "Open/Close node" + comillas+ " /></a>"); 				
				}
			} else {
				var imagen1 = mount(getJoinBottom(target));
				var imagen2 = mount(getJoin(target));
			
				if (ls) 
					write("<img " + imagen1 + " align=" + comillas+ "absbottom" + comillas+ " alt=" + comillas+ comillas+ " />");
				else 
					write("<img  " + imagen2 + " align="+comillas+"absbottom" + comillas+ " alt=" + comillas+ comillas+" />");
			}

			write("<a href="+comillas + getAction(target, nodeValues[0], nodeValues[3]) +  comillas+ " onmouseover="+comillas+"window.status='" + nodeValues[2] + "';" + comillas+ " onmouseout=" + comillas+ "window.status=' ';"+comillas+">");
			
			if (hcn) {
				var folder = null;
				if (ino) {
					folder =  mount(getOpenNode(target, nodeValues[3]));
				} else {
					folder =  mount(getCloseNode(target, nodeValues[3]));
				}
				write("<img id=" + comillas+ "icon" +  target + "_" + nodeValues[0] + comillas + folder +" align=" + comillas+ " absbottom" + comillas+ " alt=" + comillas+ "Folder" + comillas+ " />"); //src=\"static/img/folder"
			} else {
				write("<input type='checkbox' id="+comillas+"registro" +  target + "_" + nodeValues[0] + comillas+" onClick="+comillas+"javascript:d('"  + target + "_" + nodeValues[0] + "');"+comillas+">" +  "&nbsp;<img id=" + comillas+ "icon"  + target + "_" + nodeValues[0] + comillas + mount(getItemNode(target, nodeValues[3])) + " align=" + comillas+ "absbottom" + comillas+ " alt=" + comillas+ "Page" + comillas+ " />");
			}

			//write(target, "<input type='Checkbox' name='aa'  ");	
			
			write(nodeValues[2]);

			// End link
			write("</a><br />");

			// If node has children write out divs and go deeper
			if (hcn) {
				if (!ino) {
					 	write("<div id=" + comillas+ "div" + target + "_" + nodeValues[0] + comillas + " style="+comillas+"display: none;"+comillas + ">");
				} else {
						write("<div id=" + comillas+ "div" + target + "_" + nodeValues[0] + comillas + ">");
				}

				addNode(target, nodeValues[0], recursedNodes);
			
				write("</div>");
				
			}

			// remove last line or empty icon 
			recursedNodes.pop();
		}
		
	}
	
}

function getAction(target, id, type) {
	var action = "javascript:action"+target+"("+id+", "+type+");";
	return action;
}

// Opens or closes a node
function oc(target, node, bottom, type) {
	var theDiv = document.getElementById("div" + node);
	var theJoin = document.getElementById("join" + node);
	var theIcon = document.getElementById("icon" + node);

	if (theDiv.style.display == 'none') {
		if (bottom == 1) theJoin.src = getCloseSonsBottom(target);//icons[3].src;
		else theJoin.src = getCloseSons(target);//icons[2].src;

		theIcon.src = getOpenNode(target, type);//icons[5].src;
		theDiv.style.display = '';
	} else {
		if (bottom == 1) theJoin.src = getMoreSonsBottom(target);//icons[1].src;
		else theJoin.src = getMoreSon(target);//icons[0].src;
		theIcon.src = getCloseNode(target, type);//icons[4].src;
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

function getRoot(target) {
	//var item = "/img/" + target+"/imgfolder.gif";
	let item = eval("getRoot" + target+"()");
	//base.gif
	return item;
}

function getOpenNode(target, type) {
	//var item = "/img/folderopen.gif";
	
	let item = eval("getOpenNode" + target+"('"+ type + "')");
	
	return item;
}

function getCloseNode(target, type) {
	var item = "/img/folder.gif";
	if(type=="2") {
		item ="/img/search.png";
	}
	
	return item;
}

function getMoreSon(target) {
	return "/img/plus.gif";
}

function getCloseSons(target) {
	return "/img/minus.gif";
}

function getMoreSonsBottom(target) {
	return "/img/plusbottom.gif";
}

function getCloseSonsBottom(target) {
	return "/img/minusbottom.gif";
}

function getLine(target) {
	return "/img/line.gif";
}

function getEmpty(target) {
	return "/img/empty.gif";
}

function getJoinBottom(target) {
	return "/img/joinbottom.gif";
}

function getJoin(target) {
	return "/img/join.gif";
}

function getItemNode(target, type) {
	var item = "/img/page.gif";
	if(type=="3") {
		item ="/img/cd.gif";
	}
	if(type=="5") {
		item ="/img/question.gif";
	}
	return item;
}

