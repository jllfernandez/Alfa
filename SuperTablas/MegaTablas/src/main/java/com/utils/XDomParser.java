package com.utils;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XDomParser {

	private static XDomParser instance = null;
	private static Document document = null;

	/**
	 * Singleton access to Lanzadera.
	 * 
	 * @return singleton instance of Lanzadera.
	 */
	public static synchronized XDomParser getInstance() {
		if (null == instance) {
			instance = new XDomParser();
			System.out.println("Creo objeto nuevo");
		} else {
			// System.out.println("Ya existe instancia");
		}
		return instance;
	}

	private XDomParser() {
	}

	/**
	 * @return Document
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * @return Document
	 */
	public void setDocument(Document doc) {
		document = doc;
	}

	/**
	 * @param sFileName
	 *            String
	 */
	public void buildXmlDocument(String sFileName) {
		// Objetos para generar parseo DOM
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			// To get a validating parser
			factory.setValidating(false);
			// To get one that understands namespaces
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			setDocument(builder.parse(new File(sFileName)));
			System.out.println("He leido el XML ---> " + sFileName);
		} catch (Exception e) {
			System.out.println("Error al leer el XML -->" + sFileName);
		}
	}

	/**
	 * @param sFileName
	 *            String
	 */
	public void buildXmlDocument(InputStream is) {
		// Objetos para generar parseo DOM
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			// To get a validating parser
			factory.setValidating(false);
			// To get one that understands namespaces
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			setDocument(builder.parse(is));
			System.out.println("He leido el XML ---> ");
		} catch (Exception e) {
			System.out.println("Error al leer el XML -->");
		}
	}

	/**
	 * @param root
	 *            String
	 * @param nombrenuevo
	 *            String
	 * @param value
	 *            String
	 */
	public void addNodo(String pather, String nombrenuevo, String value) {
		Element nuevo = (Element) getDocument().createElement(nombrenuevo);
		getNodeByName(pather).appendChild(nuevo);

		Node nodo = getDocument().createTextNode(value);
		nuevo.appendChild(nodo);
	}

	/**
	 * @param root
	 *            String
	 * @param nombrenuevo
	 *            String
	 * @param value
	 *            String
	 */
	public void removeNodo(String pather, String nodeToRemove) {
		getNodeByName(pather).removeChild(getNodeByName(nodeToRemove));
	}

	/**
	 * @param nodoName
	 *            String
	 * @return String
	 */
	public String getValorNodo(String nodoName) {
		return getNodeHijo(getNodeByName(nodoName), 0).getNodeValue();
	}

	/**
	 * @param att
	 *            String
	 * @param nodo
	 *            String
	 * @return String
	 */
	public String getAtributoFromNodo(String att, String nodo) {
		return getValor(getAttribute(getNodeByName(nodo), att));
	}

	/**
	 * @param pos
	 *            int
	 * @param nodo
	 *            String
	 * @return String
	 */
	public String getAtributoFromNodo(int pos, String nodo) {
		return getValor(getAttribute(getNodeByName(nodo), pos));
	}

	/**
	 * @param att
	 *            String
	 * @param nodo
	 *            String
	 * @param order
	 *            int
	 * @return String
	 */
	public String getAtributoFromNodoAt(String att, Node nodo, int order) {
		return getValor(getAttribute(nodo, att));
	}

	/**
	 * @param att
	 *            String
	 * @param nodo
	 *            String
	 * @return String
	 */
	public String getAtributoFromNodo(String att, Node nodo) {
		return getValor(getAttribute(nodo, att));
	}

	/**
	 * @param att
	 *            String
	 * @param nodo
	 *            String
	 * @param order
	 *            int
	 * @return String
	 */
	public String getAtributoFromNodoAt(String att, String nodo, int order) {
		return getValor(getAttribute(getNodeFromList(nodo, order), att));
	}

	/**
	 * @param pos
	 *            int
	 * @param nodo
	 *            String
	 * @param order
	 *            int
	 * @return String
	 */
	public String getAtributoFromNodoAt(int pos, String nodo, int order) {
		return getValor(getAttribute(getNodeFromList(nodo, order), pos));
	}

	/**
	 * @return Node
	 */
	public Node getDocumento() {
		return getNodeHijo(getDocument(), 0);
	}

	/**
	 * @param sKey
	 *            String
	 * @return int
	 */
	public int getSize(String sKey) {
		NodeList list = getDocument().getElementsByTagName(sKey);
		return list.getLength();
	}

	/**
	 * @param sKey
	 *            String
	 * @param nodo
	 *            Node
	 * @return int
	 */
	public int getSize(String sKey, Node nodo) {
		NodeList list = ((Element) nodo).getElementsByTagName(sKey);
		return list.getLength();
	}

	/**
	 * @param node
	 *            Node
	 * @return NodeList
	 */
	public NodeList getNodeHijos(Node node) {
		return node.getChildNodes();
	}

	/**
	 * @param node
	 *            Node
	 * @param pos
	 *            int
	 * @return Node
	 */
	public Node getNodeHijo(Node node, int pos) {
		return getNodeHijos(node).item(pos);
	}

	/**
	 * @param node
	 *            Node
	 * @return String
	 */
	public String getName(Node node) {
		return node.getNodeName();
	}

	/**
	 * @param node
	 *            Node
	 * @return String
	 */
	public String getValor(Node node) {
		// return node.getNodeValue();
		String result = "";
		if (null != node) {
			result = node.getTextContent();
		}
		return result;
	}

	/**
	 * @param node
	 *            Node
	 * @return int
	 */
	public int getNodesLength(Node node) {
		NodeList children = node.getChildNodes();
		if (children != null)
			return children.getLength();
		return 0;
	}

	/**
	 * @param node
	 *            Node
	 * @return NamedNodeMap
	 */
	public NamedNodeMap getAttributes(Node node) {
		NamedNodeMap attrs = node.getAttributes();
		return attrs;
	}

	/**
	 * @param nodeKey
	 *            String
	 * @param attKey
	 *            String
	 * @param valor
	 *            String
	 */
	public void setAttibute(String nodeKey, String attKey, String valor) {
		Element element = getDocument().getElementById(nodeKey);
		element.setAttribute(attKey, valor);
	}

	/**
	 * @param node
	 *            Node
	 * @param pos
	 *            int
	 * @return Node
	 */
	public Node getAttribute(Node node, int pos) {
		return getAttributes(node).item(pos);
	}

	/**
	 * @param node
	 *            Node
	 * @param key
	 *            String
	 * @return Node
	 */
	public Node getAttribute(Node node, String key) {
		return getAttributes(node).getNamedItem(key);
	}

	/**
	 * @param sKey
	 *            String
	 * @param pos
	 *            int
	 * @return Node
	 */
	public Node getNodeFromList(String sKey, int pos) {
		NodeList list = getDocument().getElementsByTagName(sKey);
		Node element = list.item(pos);
		return element;
	}

	/**
	 * @param sKey
	 *            String
	 * @param pos
	 *            int
	 * @return Node
	 */
	public Node getNodeFromList(String sKey, Node node, int pos) {
		NodeList list = ((Element) node).getElementsByTagName(sKey);
		Node element = list.item(pos);
		return element;
	}

	/**
	 * @param sKey
	 *            String
	 * @return Node
	 */
	public Node getNodeByName(String sKey) {
		NodeList list = getDocument().getElementsByTagName(sKey);
		for (int i = 0; i < list.getLength(); i++) {
			Node element = list.item(i);
			if (getName(element).equals(sKey))
				return element;
		}
		return null;
	}

	/*
	 * This method writes a DOM document to a file
	 * 
	 * @param filename String
	 */
	public void writeXmlToFile(String filename) {
		writeXmlToFile(getDocument(), filename);
	}

	/*
	 * This method writes a DOM document to a file
	 * 
	 * @param document
	 * 
	 * @param filename
	 */
	public void writeXmlToFile(Document document, String filename) {
		try {

			// Prepare the DOM document for writing
			Source source = new DOMSource(document);

			// Prepare the output file
			File file = new File(filename);
			Result result = new StreamResult(file);

			// Write the DOM document to the file
			// Get Transformer
			Transformer xformer = TransformerFactory.newInstance()
					.newTransformer();
			// Write to a file
			xformer.transform(source, result);

		} catch (Exception eg) {
			System.out.println("Exception: " + eg);
		}

	}

}
