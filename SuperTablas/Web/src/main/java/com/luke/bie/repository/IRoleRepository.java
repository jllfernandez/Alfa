package com.luke.bie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luke.bie.entitys.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

	List<Role> findAll();
}
