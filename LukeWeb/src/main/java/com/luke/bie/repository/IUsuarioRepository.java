package com.luke.bie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luke.bie.entitys.Usuarios;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuarios, Long> {

	List<Usuarios> findAll();
}
