package com.luke.bie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luke.bie.entitys.Roles;

@Repository
public interface IRoleRepository extends JpaRepository<Roles, Long> {

	List<Roles> findAll();
}
