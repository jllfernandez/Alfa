package com.luke.bie.mapstruct.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import com.luke.bie.entitys.Capabilityes;
import com.luke.bie.entitys.Roles;
import com.luke.bie.entitys.Usuarios;
import com.luke.bie.web.dto.CapabilityesDTO;
import com.luke.bie.web.dto.RolesDTO;
import com.luke.bie.web.dto.UsuariosDTO;

@Component
@Mapper(componentModel = "spring")

public interface UsuariosMapper {

	@Mappings({ @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesFlat") })
	 List<UsuariosDTO> usuariosFlat( List<Usuarios> usuarios);

	@Named("rolesFlat")
	@Mappings({ @Mapping(target = "usuarios", ignore = true),
			@Mapping(source = "capabilities", target = "capabilities", qualifiedByName = "capabilitiesFlat") })
	RolesDTO rolesFlat(Roles roles);

	@Named("capabilitiesFlat")
	@Mappings({ @Mapping(target = "roles", ignore = true) })
	default public List<CapabilityesDTO> capabilitiesFlat(List<Capabilityes> list) {
		if (list == null) {
			return null;
		}

		List<CapabilityesDTO> list1 = new ArrayList<CapabilityesDTO>(list.size());
		for (Capabilityes capabilityes : list) {
			list1.add(capabilityFlat(capabilityes));
		}

		return list1;
	}

	@Mappings({ @Mapping(target = "roles", ignore = true) })
	CapabilityesDTO capabilityFlat(Capabilityes capabilityes);

	default public List<UsuariosDTO> usuariosToDto(List<Usuarios> usuarios) {
		if (usuarios == null) {
			return null;
		}

		List<UsuariosDTO> list1 = new ArrayList<UsuariosDTO>(usuarios.size());
		for (Usuarios usuario : usuarios) {
			list1.add(usuarioToDto(usuario));
		}

		return list1;
	}

	@Mappings(@Mapping(source = "roles", target = "roles", qualifiedByName = "roleSinUsuariosToDto"))
	UsuariosDTO usuarioToDto(Usuarios usuario);

	@Named("roleSinUsuariosToDto")
	default public RolesDTO roleSinUsuariosToDto(Roles role) {

		RolesDTO dto = roleToDtoConCaps(role);
		// dto.setCapabilities(capabilityesListToCapabilityesDTOListWithOutRoles(role.getCapabilities()));

		return dto;
	}

	@Mappings({ @Mapping(target = "usuarios", ignore = true),
			@Mapping(source = "capabilities", target = "capabilities", qualifiedByName = "capabilityesListToCapabilityesDTOListWithOutRoles") })
	RolesDTO roleToDtoConCaps(Roles roles);

	@Named("capabilityesListToCapabilityesDTOListWithOutRoles")
	default public List<CapabilityesDTO> capabilityesListToCapabilityesDTOListWithOutRoles(List<Capabilityes> list) {
		if (list == null) {
			return null;
		}

		List<CapabilityesDTO> list1 = new ArrayList<CapabilityesDTO>(list.size());
		for (Capabilityes capabilityes : list) {
			//list1.add(capabilityesToCapabilityesDTOSinRoles(capabilityes));
			list1.add(capabilityFlat(capabilityes));
		}

		return list1;
	}

	/*
	default public CapabilityesDTO capabilityesToCapabilityesDTOSinRoles(Capabilityes capabilityes) {
		if (capabilityes == null) {
			return null;
		}

		CapabilityesDTO capabilityesDTO = new CapabilityesDTO();

		capabilityesDTO.setCode(capabilityes.getCode());
		capabilityesDTO.setDescr(capabilityes.getDescr());

		capabilityesDTO.id = capabilityes.getId();

		return capabilityesDTO;
	}*/

//	@Mapping(target = "id", source = "id")
//	@Mapping(target = "code", source = "code")
//	@Mapping(target = "descr", source = "descr")
	@Mapping(target = "usuarios", ignore = true)
	RolesDTO roleToDto(Roles roles);

	// @Mapping(target = "usuarios", ignore = true)
	// List<RolesDTO> rolesToDto(List<Roles> roles);

	// @Mapping(target = "roles", ignore = true)
	// @Mapping(target = "roles", source = "roles")
	Usuarios dtoToUsuario(UsuariosDTO dto);

//	@Mapping(target = "id", source = "id")
//	@Mapping(target = "code", source = "code")
//	@Mapping(target = "descr", source = "descr")
	@Mapping(target = "usuarios", ignore = true)
	@Mapping(target = "capabilities", ignore = true)
	Roles roleToEntity(RolesDTO dto);

	default public List<RolesDTO> rolesWithUsuariosToDto(List<Roles> roles) {

		return roles.stream().map(role -> {

			RolesDTO dto = roleToDto(role);// new RoleDTO();

			dto.setUsuarios(usuariosToDto(role.getUsuarios()));

			return dto;
		}).collect(Collectors.toList());
	}

	default public List<UsuariosDTO> usuariosWithRolessToDto(List<Usuarios> usuarios) {
		return usuarios.stream().map(usuario -> {

			UsuariosDTO dto = usuarioToDto(usuario);
			dto.setRoles(roleToDto(usuario.getRoles()));

			/*
			 * RolesDTO dto = roleToDto(role);// new RoleDTO();
			 * 
			 * dto.setUsuarios(usuariosToDto(role.getUsuarios()));
			 */

			return dto;
		}).collect(Collectors.toList());
	}

	default public Usuarios dtoToUsuarioWithRole(UsuariosDTO dto) {
		Usuarios entity = dtoToUsuario(dto);
		Roles rol = new Roles();
		rol.setId(dto.getRoles().getId());

		entity.setRoles(rol);

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
