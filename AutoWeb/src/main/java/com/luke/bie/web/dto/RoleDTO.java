package com.luke.bie.web.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class RoleDTO {

	@Getter
	@Setter
	public Long id;

	@Getter
	@Setter
	public String code;

	@Getter
	@Setter
	public String descr;

	@Getter
	@Setter
	public List<UsuarioDTO> usuarios;
	
	@Getter
	@Setter
	public List<CapabilityesDTO> capabilities;

}
