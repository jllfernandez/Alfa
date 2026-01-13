package com.luke.bie.web.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class UsuarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	public Long id;

	@Getter
	@Setter
	public String login;

	@Getter
	@Setter
	public String pass;

	@Getter
	@Setter
	public RoleDTO role;

	@Getter
	@Setter
	public Timestamp date;

}
