package com.luke.bie.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.luke.bie.web.dto.Record;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TreeService {

	public ArrayList<Record> getTree() {
		ArrayList<Record> arr = new ArrayList<Record>();
		Record r = new Record();
		r.setNodeId("1");
		r.setParentNodeId("0");
		r.setNodeName("Page 1");
		r.setNodeUrl("#");

		arr.add(r);

		r = new Record();
		r.setNodeId("2");
		r.setParentNodeId("1");
		r.setNodeName("Page 1.1");
		r.setNodeUrl("#");

		arr.add(r);

		r = new Record();
		r.setNodeId("3");
		r.setParentNodeId("1");
		r.setNodeName("Page 1.2");
		r.setNodeUrl("#");

		arr.add(r);

		r = new Record();
		r.setNodeId("4");
		r.setParentNodeId("3");
		r.setNodeName("Page 1.2.1");
		r.setNodeUrl("#");

		arr.add(r);

		return arr;

	}

	public ArrayList<Record> getTree2() {
		ArrayList<Record> arr = new ArrayList<Record>();
		Record r = new Record();
		r.setNodeId("1");
		r.setParentNodeId("0");
		r.setNodeName("Page 1");
		r.setNodeType(1);
		r.setNodeUrl("#");

		arr.add(r);

		r = new Record();
		r.setNodeId("2");
		r.setParentNodeId("1");
		r.setNodeName("Page 1.1");
		r.setNodeType(2);

		arr.add(r);

		r = new Record();
		r.setNodeId("3");
		r.setParentNodeId("1");
		r.setNodeName("Page 1.2");
		r.setNodeType(2);

		arr.add(r);

		r = new Record();
		r.setNodeId("4");
		r.setParentNodeId("3");
		r.setNodeName("Page 1.2.1");
		r.setNodeType(3);

		arr.add(r);
		
		r = new Record();
		r.setNodeId("5");
		r.setParentNodeId("3");
		r.setNodeName("Page 1.5");
		r.setNodeType(5);

		arr.add(r);
		
		r = new Record();
		r.setNodeId("6");
		r.setParentNodeId("5");
		r.setNodeName("Page 15");
		r.setNodeType(3);

		arr.add(r);


		return arr;

	}

	public ArrayList<Record> getTree3() {
		ArrayList<Record> arr = new ArrayList<Record>();
		Record r = new Record();
		r.setNodeId("1");
		r.setParentNodeId("0");
		r.setNodeName("Raiz 1");
		r.setNodeType(1);
		r.setNodeUrl("#");

		arr.add(r);

		r = new Record();
		r.setNodeId("2");
		r.setParentNodeId("1");
		r.setNodeName("Raiz 1.1");
		r.setNodeType(2);

		arr.add(r);

		r = new Record();
		r.setNodeId("3");
		r.setParentNodeId("1");
		r.setNodeName("Raiz 1.2");
		r.setNodeType(2);

		arr.add(r);

		r = new Record();
		r.setNodeId("4");
		r.setParentNodeId("3");
		r.setNodeName("Raiz 1.2.1");
		r.setNodeType(3);

		arr.add(r);
		
		r = new Record();
		r.setNodeId("5");
		r.setParentNodeId("3");
		r.setNodeName("Raiz 1.5");
		r.setNodeType(5);

		arr.add(r);
		
		r = new Record();
		r.setNodeId("6");
		r.setParentNodeId("5");
		r.setNodeName("Raiz 15");
		r.setNodeType(3);

		arr.add(r);


		return arr;

	}

	public ArrayList<String[]> getTreeString() {
		ArrayList<String[]> arr = new ArrayList<String[]>();
		String[] r = new String[] {"1","0","Page 1"};

		arr.add(r);

		r = new String[] {"2","1","Page 1.1"};

		arr.add(r);

		r = new String[] {"3","1","Page 1.1.2"};

		arr.add(r);

		r = new String[] {"4","3","Page 1.2.1"};

		arr.add(r);

		return arr;

	}
}
