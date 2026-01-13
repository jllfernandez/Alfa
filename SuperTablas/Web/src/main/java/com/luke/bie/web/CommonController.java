package com.luke.bie.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.luke.bie.web.dto.UsuarioDTO;

public class CommonController {

	/**
	 * getUsuario.
	 * 
	 * @param session HttpSession
	 * @return String
	 */
	protected String getUsuario(HttpSession session) {

		UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");

		return usuario.getLogin();
	}

	/**
	 * getUsuario.
	 * 
	 * @param session HttpSession
	 * @return UsuarioDto
	 */
	protected UsuarioDTO getUsuario() {
		UsuarioDTO usuario = (UsuarioDTO) getSession().getAttribute("usuario");

		return usuario;
	}

	/**
	 * setUsuario.
	 * 
	 * @param UsuarioDTO usuario
	 */
	protected void setUsuario(UsuarioDTO usuario) {
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
