package com.luke.bie.web.dto.filters;

import com.luke.bie.web.dto.UsuariosDTO;
import com.luke.core.ad.filters.Paged;

import lombok.Getter;
import lombok.Setter;

public class UsuarioFilter extends UsuariosDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Paged paged;
}
