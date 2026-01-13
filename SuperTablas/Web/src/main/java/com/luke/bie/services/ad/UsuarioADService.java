package com.luke.bie.services.ad;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.luke.bie.web.dto.filters.UsuarioFilter;
import com.luke.core.ad.daos.HqlHandlerDao;
import com.luke.core.ad.daos.Operators;

@Service
public class UsuarioADService extends HqlHandlerDao {

	public Map<String, Object> searchByCriteria(UsuarioFilter filter) {

		String sqlQuery = "select distinct(main) from Usuarios main join fetch main.roles r join fetch r.capabilities ";
		// String sqlQuery = "select main from ArticuloEntity main join fetch
		// main.normativa n left join fetch main.articulosCns c";

		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("main.login", notBlank(filter.getLogin()));

		Map<String, Operators> operatorsMap = new HashMap<String, Operators>();
		operatorsMap.put("main.login", Operators.LIKE);

		return super.searchByCriteria(sqlQuery, filter.getPaged(), parametersMap, operatorsMap);

	}
}
