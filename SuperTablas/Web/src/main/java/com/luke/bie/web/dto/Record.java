package com.luke.bie.web.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class Record implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	String nodeId;

	@Getter
	@Setter
	String parentNodeId;

	@Getter
	@Setter
	String nodeName;

	@Getter
	@Setter
	String nodeUrl;
	
	@Getter
	@Setter
	int nodeType;

}
