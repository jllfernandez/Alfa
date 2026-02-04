package com.luke.bie.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luke.bie.entitys.Role;
import com.luke.bie.repository.IRoleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class RolesServiceImpl {

	@Autowired
	IRoleRepository repository;

	// @Override
	public List<Role> findAll() {
		log.info("In ");
		return repository.findAll();
	}

	/*
	 * @Override public Map<String, Object> searchByCriteria(RoleFilter filter) {
	 * return svc.searchByCriteria(filter); }
	 */
}
