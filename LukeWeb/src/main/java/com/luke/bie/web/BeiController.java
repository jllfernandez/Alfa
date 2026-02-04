package com.luke.bie.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.luke.bie.entitys.Capabilityes;
import com.luke.bie.entitys.Roles;
import com.luke.bie.entitys.Usuarios;
import com.luke.bie.mapstruct.mappers.UsuariosMapper;
import com.luke.bie.services.RolesServiceImpl;
import com.luke.bie.services.TreeService;
import com.luke.bie.services.UsuariosService;
import com.luke.bie.utils.Constants;
import com.luke.bie.web.dto.SolicitudDTO;
import com.luke.bie.web.dto.UsuariosDTO;
import com.luke.bie.web.dto.filters.UsuarioFilter;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(path = Constants.BIE_MAPPING)
public class BeiController extends CommonController {

	final String INDEX = "index";

	final String DEFAULT = "default";

	final String JSON = "json";

	final String METODO = "Metodo";

	final String CLEAR = "clear";

	final String LIMPIA = "Limpia";

	private static final Logger logger = LoggerFactory.getLogger(BeiController.class);

	@Autowired
	TreeService treeSvc;

	@Autowired
	RolesServiceImpl rolesService;

	@Autowired
	UsuariosService usuariosService;

	@Autowired
	UsuariosMapper mapper;

	@GetMapping("")
	public String entrada(Model model) {
		log.info("Entrada por OK /");

		List<Roles> lista = rolesService.findAll();
		System.out.println("  --->" + lista.size());
		List<Capabilityes> caps = lista.get(0).getCapabilities();
		System.out.println("  --->" + caps.size());

		List<Usuarios> usuarios = usuariosService.findAll();
		System.out.println("  --->" + usuarios.size());

		return INDEX;
	}

	@GetMapping("/default")
	public String entrada2(Model model) {
		log.info("Entrada por /");

		if (null != getUsuario()) {
			// getUsuario().getUsuario();
			System.out.println("--->" + getUsuario().getLogin());
		}

		model.addAttribute(Constants.DOCUMENTO_FILTER, treeSvc.getTree());

		return DEFAULT;
	}

	@GetMapping("/json")
	public String json(Model model) {
		log.info("Entrada por /");

		// model.addAttribute(Constants.DOCUMENTO_FILTER, treeSvc.getTreeString());

		Gson gson = new Gson();
		String json = gson.toJson(treeSvc.getTree());

		model.addAttribute(Constants.DOCUMENTO_FILTER, json);

		return JSON;
	}

	@GetMapping(value = "/metodo", produces = MediaType.APPLICATION_JSON_VALUE)
	public String metodo(Model model) {
		log.info("Entrada por /");

		return METODO;
	}

	@GetMapping(value = "/clear", produces = MediaType.APPLICATION_JSON_VALUE)
	public String clear(Model model) {
		log.info("Entrada por /");

		return CLEAR;
	}

	@GetMapping(value = "/limpia", produces = MediaType.APPLICATION_JSON_VALUE)
	public String limpia(Model model) {
		log.info("Entrada por /");

		return LIMPIA;
	}

	@GetMapping(value = "/cargaTree", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> cargaTree() {
		log.info("Entrada cargaTree/");
		Map<String, Object> rtn = new HashMap<String, Object>();

//		rtn.put("result", new Gson().toJson(treeSvc.getTree()));
		rtn.put("result", treeSvc.getTree());

		return rtn;
	}

	@GetMapping(value = "/cargaTree2", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> cargaTree2() {
		log.info("Entrada cargaTree/");
		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("result", treeSvc.getTree2());

		return rtn;
	}

	// cargaRaiz
	@GetMapping(value = "/cargaRaiz", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> cargaRaiz() {
		log.info("Entrada cargaRaiz/");
		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("result", treeSvc.getTree3());

		return rtn;
	}

	@GetMapping("/{usuario}")
	public String entrada2(@PathVariable String usuario, Model model) {
		log.info("Entrada2 por /usuario");
		// HttpSession session = getSession();
		UsuariosDTO usu = new UsuariosDTO();
		usu.setLogin(usuario);

		setUsuario(usu);

		return INDEX;
	}

	@GetMapping(value = "/validaSia/{sia}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, String> validaSia(@PathVariable String sia, Model model) {
		log.info("Entrada validaSia/");
		Map<String, String> rtn = new HashMap<String, String>();

		if ("15".equals(sia))
			rtn.put("result", "true");
		else
			rtn.put("result", "false");

		return rtn;
	}

	@PostMapping(value = "/validaSia", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, String> validaSia(@RequestBody SolicitudDTO solicitud, Model model) {
		Map<String, String> rtn = new HashMap<String, String>();

		if ("15".equals(solicitud.getNumeroSIA().trim()))
			rtn.put("result", "true");
		else
			rtn.put("result", "false");

		return rtn;
	}

	@PostMapping(value = "/findAll", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<?> findAll(@Valid @RequestBody UsuarioFilter filter) throws Exception {
		logger.debug("Data received at create: {}", filter);

		Map<String, Object> filtrado = usuariosService.searchByCriteria(filter);
		ArrayList<Usuarios> entitys = (ArrayList<Usuarios>) filtrado.get("content");
		// Paged paginacion = (Paged) filtrado.get("paginacion");
		List<UsuariosDTO> lista = mapper.usuariosFlat(entitys);

		return ResponseEntity.ok(lista);

		// return ResponseEntity.ok(mapper.usuariosToDto(entitys));
	}

}
