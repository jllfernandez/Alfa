package com.luke.bie.mapstruct.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import com.luke.bie.entitys.Capability;
import com.luke.bie.entitys.Role;
import com.luke.bie.entitys.Usuario;
import com.luke.bie.web.dto.CapabilityesDTO;
import com.luke.bie.web.dto.RoleDTO;
import com.luke.bie.web.dto.UsuarioDTO;

@Component
@Mapper(componentModel = "spring")

public interface UsuariosMapper {

	@Mappings({ @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesFlat") })
	List<UsuarioDTO> usuariosFlat(List<Usuario> usuarios);

	@Named("rolesFlat")
	@Mappings({ @Mapping(target = "usuarios", ignore = true),
			@Mapping(source = "capabilities", target = "capabilities", qualifiedByName = "capabilitiesFlat") })
	RoleDTO rolesFlat(Role roles);

	@Named("capabilitiesFlat")
	@Mappings({ @Mapping(target = "roles", ignore = true) })
	default public List<CapabilityesDTO> capabilitiesFlat(List<Capability> list) {
		if (list == null) {
			return null;
		}

		List<CapabilityesDTO> list1 = new ArrayList<CapabilityesDTO>(list.size());
		for (Capability capabilityes : list) {
			list1.add(capabilityFlat(capabilityes));
		}

		return list1;
	}

	@Mappings({ @Mapping(target = "roles", ignore = true) })
	CapabilityesDTO capabilityFlat(Capability capabilityes);

	default public List<UsuarioDTO> usuariosToDto(List<Usuario> usuarios) {
		if (usuarios == null) {
			return null;
		}

		List<UsuarioDTO> list1 = new ArrayList<UsuarioDTO>(usuarios.size());
		for (Usuario usuario : usuarios) {
			list1.add(usuarioToDto(usuario));
		}

		return list1;
	}

	@Mappings(@Mapping(source = "role", target = "role", qualifiedByName = "roleSinUsuariosToDto"))
	UsuarioDTO usuarioToDto(Usuario usuario);

	@Named("roleSinUsuariosToDto")
	default public RoleDTO roleSinUsuariosToDto(Role role) {

		RoleDTO dto = roleToDtoConCaps(role);
		// dto.setCapabilities(capabilityesListToCapabilityesDTOListWithOutRoles(role.getCapabilities()));

		return dto;
	}

	@Mappings({ @Mapping(target = "usuarios", ignore = true),
			@Mapping(source = "capabilities", target = "capabilities", qualifiedByName = "capabilityesListToCapabilityesDTOListWithOutRoles") })
	RoleDTO roleToDtoConCaps(Role roles);

	@Named("capabilityesListToCapabilityesDTOListWithOutRoles")
	default public List<CapabilityesDTO> capabilityesListToCapabilityesDTOListWithOutRoles(List<Capability> list) {
		if (list == null) {
			return null;
		}

		List<CapabilityesDTO> list1 = new ArrayList<CapabilityesDTO>(list.size());
		for (Capability capabilityes : list) {
			// list1.add(capabilityesToCapabilityesDTOSinRoles(capabilityes));
			list1.add(capabilityFlat(capabilityes));
		}

		return list1;
	}

	/*
	 * default public CapabilityesDTO
	 * capabilityesToCapabilityesDTOSinRoles(Capabilityes capabilityes) { if
	 * (capabilityes == null) { return null; }
	 * 
	 * CapabilityesDTO capabilityesDTO = new CapabilityesDTO();
	 * 
	 * capabilityesDTO.setCode(capabilityes.getCode());
	 * capabilityesDTO.setDescr(capabilityes.getDescr());
	 * 
	 * capabilityesDTO.id = capabilityes.getId();
	 * 
	 * return capabilityesDTO; }
	 */

//	@Mapping(target = "id", source = "id")
//	@Mapping(target = "code", source = "code")
//	@Mapping(target = "descr", source = "descr")
	//@Mapping(target = "usuarios", ignore = true)
	RoleDTO roleToDto(Role roles);

	// @Mapping(target = "usuarios", ignore = true)
	// List<RolesDTO> rolesToDto(List<Roles> roles);

	// @Mapping(target = "roles", ignore = true)
	// @Mapping(target = "roles", source = "roles")
	Usuario dtoToUsuario(UsuarioDTO dto);

//	@Mapping(target = "id", source = "id")
//	@Mapping(target = "code", source = "code")
//	@Mapping(target = "descr", source = "descr")
//	@Mapping(target = "usuario", ignore = true)
	@Mapping(target = "capabilities", ignore = true)
	Role roleToEntity(RoleDTO dto);

	default public List<RoleDTO> rolesWithUsuariosToDto(List<Role> roles) {

		return roles.stream().map(role -> {

			RoleDTO dto = roleToDto(role);// new RoleDTO();

			//dto.setUsuarios(usuariosToDto(roles.getUsuario()));

			return dto;
		}).collect(Collectors.toList());
	}

	default public List<UsuarioDTO> usuariosWithRolessToDto(List<Usuario> usuarios) {
		return usuarios.stream().map(usuario -> {

			UsuarioDTO dto = usuarioToDto(usuario);
			dto.setRole(roleToDto(usuario.getRole()));

			/*
			 * RolesDTO dto = roleToDto(role);// new RoleDTO();
			 * 
			 * dto.setUsuarios(usuariosToDto(role.getUsuarios()));
			 */

			return dto;
		}).collect(Collectors.toList());
	}

	default public Usuario dtoToUsuarioWithRole(UsuarioDTO dto) {
		Usuario entity = dtoToUsuario(dto);
		Role rol = new Role();
		rol.setId(dto.getRole().getId());

		entity.setRole(rol);

		return entity;

	}

}

/*
 * default public List<UsuariosDTO> usuarioToDto(List<Usuarios> usuarios) {
 * return usuarios.stream().map(usuario -> {
 * 
 * UsuariosDTO dto = usuarioToDto(usuario);
 * dto.setRoles(roleToDto(usuario.getRoles()));
 * 
 * /* RolesDTO dto = roleToDto(role);// new RoleDTO();
 * 
 * dto.setUsuarios(usuariosToDto(role.getUsuarios()));
 *
 * 
 * return dto; }).collect(Collectors.toList()); }
 */

//@Mapping(target = "creationDate", expression = "java(new java.util.Date())")

//
//@Mapping(target = "customerInternalId", ignore = true)
//@Mapping(target = "amount", source = "datosTarjeta.importeQueda", numberFormat = "$#.00")
//@Mapping(target = "income", source = "datosTarjeta.importeProximaLiquidacion", numberFormat = "$#.00")
//@Mapping(target = "expenses", source = "datosTarjeta.importeGastado", numberFormat = "$#.00")
//@Mapping(target = "accountId", source = "datosTarjeta.cuenta")
//@Mapping(target = "status", source = "datosTarjeta.situacionFirmaINE")
//@Mapping(target = "employee", ignore = true)
//@Mapping(target = "contactData", ignore = true)
//Customer toApiDomain(final OutSIHome source);
//
