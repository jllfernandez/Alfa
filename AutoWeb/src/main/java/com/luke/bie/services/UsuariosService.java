package com.luke.bie.services;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luke.bie.entitys.Usuario;
import com.luke.bie.repository.IUsuarioRepository;
import com.luke.bie.services.ad.UsuarioADService;
import com.luke.bie.web.dto.filters.UsuarioFilter;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UsuariosService {

	@Autowired
	IUsuarioRepository repository;

	@Autowired
	UsuarioADService svc;

	public List<Usuario> findAll() {
		log.info("In ");
		return repository.findAll();
	}

	public Usuario create(Usuario entity) {
		log.info("Create ");
		return repository.save(entity);
	}

	public Map<String, Object> searchByCriteria(UsuarioFilter filter) {
		return svc.searchByCriteria(filter);
	}
}
