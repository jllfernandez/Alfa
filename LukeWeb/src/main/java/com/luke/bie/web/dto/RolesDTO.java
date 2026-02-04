package com.luke.bie.web.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class RolesDTO {

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
	public List<UsuariosDTO> usuarios;
	
	@Getter
	@Setter
	public List<CapabilityesDTO> capabilities;

}
