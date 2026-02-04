package com.luke.bie.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.luke.bie.web.dto.UsuariosDTO;

public class CommonController {

	/**
	 * getUsuario.
	 * 
	 * @param session HttpSession
	 * @return String
	 */
	protected String getUsuario(HttpSession session) {

		UsuariosDTO usuario = (UsuariosDTO) session.getAttribute("usuario");

		return usuario.getLogin();
	}

	/**
	 * getUsuario.
	 * 
	 * @param session HttpSession
	 * @return UsuarioDto
	 */
	protected UsuariosDTO getUsuario() {
		UsuariosDTO usuario = (UsuariosDTO) getSession().getAttribute("usuario");

		return usuario;
	}

	/**
	 * setUsuario.
	 * 
	 * @param UsuariosDTO usuario
	 */
	protected void setUsuario(UsuariosDTO usuario) {
		getSession().setAttribute("usuario", usuario);
	}

	/**
	 * getSession.
	 * 
	 * @return HttpSession
	 */
	protected HttpSession getSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}

}
