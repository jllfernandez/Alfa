package com.luke.bie.web.dto;

import java.util.List;

import com.luke.bie.entitys.Role;

import lombok.Getter;
import lombok.Setter;

public class CapabilityesDTO {

	public Long id;

	@Getter
	@Setter
	public String code;

	@Getter
	@Setter
	public String descr;

	@Getter
	@Setter
	public List<Role> roles;
}
