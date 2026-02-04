package com.luke.core.ad.daos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
*/

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.luke.core.ad.filters.Paged;

/**
 * 
 */
public class HqlHandlerDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * searchByCriteria.
	 * 
	 * @param sqlQuery
	 * @param filter
	 * @param parametersMap
	 * @param operatorsMap
	 * @return Map<String, Object>
	 */
	protected Map<String, Object> searchByCriteria(String sqlQuery, Paged paged, Map<String, Object> parametersMap,
			Map<String, Operators> operatorsMap) {

		Map<String, Object> result = new HashMap<String, Object>();

		ArrayList<Object> content = new ArrayList<Object>();

		// Update Objeto Paged
		paged = updatePaged(paged);

		Map<String, Object> objs = generateFinalSql(sqlQuery, parametersMap, operatorsMap);

		String finalSql = (String) objs.get("finalSql");

		@SuppressWarnings("unchecked")
		ArrayList<Object> params = (ArrayList<Object>) objs.get("params");

		@SuppressWarnings("unchecked")
		ArrayList<Object> paramsDates = (ArrayList<Object>) objs.get("paramsDates");

		final Query query = this.entityManager.createQuery(finalSql);

		String main = "main";
		if (finalSql.contains("distinct(main)")) {
			main = "distinct(main)";
		}

		// -> Contar
		final String countSql = "SELECT count(".concat(main).concat(") from ").concat(finalSql.split("from")[1]);

		final Query queryContar = this.entityManager.createQuery(countSql.replace(" fetch ", " "));

		int cont = 0;
		for (Object obj : params) {
			queryContar.setParameter("param".concat(String.valueOf(cont)), obj);
			query.setParameter("param".concat(String.valueOf(cont)), obj);
			cont++;
		}

		int contDates = 0;
		for (int pos = 0; pos < paramsDates.size(); pos++) {
			queryContar.setParameter("paramInf".concat(String.valueOf(contDates)), paramsDates.get(pos));
			query.setParameter("paramInf".concat(String.valueOf(contDates)), paramsDates.get(pos));
			pos++;
			queryContar.setParameter("paramSup".concat(String.valueOf(contDates)), paramsDates.get(pos));
			query.setParameter("paramSup".concat(String.valueOf(contDates)), paramsDates.get(pos));

			contDates++;
		}

		// -> Paginacion
		if (paged.getPageSize() > 0) {
			final Long total = (Long) queryContar.getSingleResult();

			query.setFirstResult((paged.getPage()) * paged.getPageSize());
			query.setMaxResults(paged.getPageSize());

			int numero = total.intValue();
			int pag = paged.getPage();
			paged.setPage(++pag);

			paged.setTotalPages(numero / paged.getPageSize());
			if ((numero % paged.getPageSize()) > 0) {
				paged.setTotalPages(paged.getTotalPages() + 1);
			}
			paged.setBef(pag - 1);
			paged.setAft(pag + 1);
		}

		List<?> listado = (List<?>) query.getResultList();

		if (null != listado) {
			for (Object entity : listado) {
				content.add(entity);
			}
		}

		result.put("content", content);
		result.put("paginacion", paged);

		return result;

	}

	/**
	 * executeQuery.
	 * 
	 * @param sqlQuery
	 * @param parametersMap
	 * @return ArrayList<Object>
	 */
	protected ArrayList<Object> executeQuery(String sqlQuery, Map<String, Object> parametersMap) {

		ArrayList<Object> content = new ArrayList<Object>();

		final Query query = this.entityManager.createQuery(sqlQuery);

		if (parametersMap != null) {
			for (Map.Entry<String, Object> entry : parametersMap.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}

		}

		List<?> listado = (List<?>) query.getResultList();

		if (null != listado) {
			for (Object entity : listado) {
				content.add(entity);
			}
		}

		return content;
	}

	/**
	 * getSingleResult.
	 * 
	 * @param sqlQuery
	 * @param parametersMap
	 * @return Object
	 */
	protected Object getSingleResult(String sqlQuery, Map<String, Object> parametersMap) {

		final Query query = this.entityManager.createQuery(sqlQuery);

		if (parametersMap != null) {
			for (Map.Entry<String, Object> entry : parametersMap.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}

		}

		Object result = (Object) query.getSingleResult();

		return result;
	}

	/**
	 * getArrObjectsResult.
	 * 
	 * @param sqlQuery
	 * @param parametersMap
	 * @return List<Object[]>
	 */
	@SuppressWarnings("unchecked")
	protected List<Object[]> getArrObjectsResult(String sqlQuery, Map<String, Object> parametersMap) {

		final Query query = this.entityManager.createQuery(sqlQuery);

		if (parametersMap != null) {
			for (Map.Entry<String, Object> entry : parametersMap.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}

		}

		List<Object[]> result = (List<Object[]>) query.getResultList();

		return result;
	}

	/**
	 * notBlank.
	 * 
	 * @param s String
	 * @return String
	 */
	protected String notBlank(String s) {
		String result = null;
		if (!"".equals(s)) {
			result = s;
		}
		return result;
	}

	/**
	 * toLocalDate.
	 * 
	 * @param s String
	 * @return LocalDate
	 */
	protected LocalDate toLocalDate(String s) {
		LocalDate datetime = null;
		if (null != s && !"".equals(s)) {
			DateTimeFormatter f = new DateTimeFormatterBuilder().parseCaseInsensitive()
					.append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toFormatter();
			try {
				datetime = LocalDate.parse(s, f);
				return datetime;
			} catch (Exception e) {
			}
		}

		return datetime;
	}

	/**
	 * prepareToLike.
	 * 
	 * @param like String
	 * @return String
	 */
	private String prepareToLike(String like) {
		return "%" + like + "%";
	}

	/**
	 * getOperation.
	 * 
	 * @param operations Map<String, Operadores>
	 * @param key        String
	 * @return Operadores
	 */
	private Operators getOperation(Map<String, Operators> operations, String key) {
		Operators operation = Operators.EQUAL;
		if (operations != null) {
			if (null != operations.get(key))
				operation = (Operators) operations.get(key);
		}
		return operation;
	}

	/**
	 * updatePaged.
	 * 
	 * @param paged
	 * @return Paged
	 */
	private Paged updatePaged(Paged paged) {
		Paged result = paged;
		if (null == result) {
			// Cuando no se requiere paginacion
			result = new Paged();
			result.setPage(1);
			result.setPageSize(0);
		} else {
			if (result.getPageSize() < 1) {
				result.setPageSize(Constants.REGISTROS_POR_PAGINA);
			}
		}

		int pag = result.getPage();
		result.setPage(--pag);

		return result;
	}

	/**
	 * generateFinalSql.
	 * 
	 * @param sqlQuery      sqlQuery
	 * @param parametersMap Map<String, Object>
	 * @param operatorsMap  Map<String, Operators>
	 * @return Map<String, Object>
	 */
	private Map<String, Object> generateFinalSql(String sqlQuery, Map<String, Object> parametersMap,
			Map<String, Operators> operatorsMap) {

		Map<String, Object> result = new HashMap<String, Object>();

		String finalSql = sqlQuery.concat(" where 1=1 ");
		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<Object> paramsDates = new ArrayList<Object>();

		int cont = 0;
		int contDates = 0;

		if (parametersMap != null) {
			Operators operation = null;
			for (Map.Entry<String, Object> entry : parametersMap.entrySet()) {
				if (null != entry.getValue()) {
					operation = getOperation(operatorsMap, entry.getKey());

					switch (operation) {
					case LIKE:
						finalSql += " AND UPPER (".concat(entry.getKey()).concat(") ").concat(operation.getOperador())
								.concat(" : param").concat(String.valueOf(cont));
						params.add(prepareToLike(entry.getValue().toString().toUpperCase()));
						cont++;

						break;
					case LIKE_SENSITIVE:
						finalSql += " AND ".concat(entry.getKey()).concat(operation.getOperador()).concat(" : param")
								.concat(String.valueOf(cont));
						params.add(prepareToLike(entry.getValue().toString()));
						cont++;

						break;
					case BETWEEN:
						finalSql += " AND ".concat(entry.getKey()).concat(" ").concat(operation.getOperador())
								.concat(" :paramInf").concat(String.valueOf(contDates));
						paramsDates.add(((LocalDate[]) entry.getValue())[0]);

						finalSql += " AND ".concat(":paramSup").concat(String.valueOf(contDates));
						paramsDates.add(((LocalDate[]) entry.getValue())[1]);
						contDates++;
						break;
					default:
						finalSql += " AND ".concat(entry.getKey()).concat(operation.getOperador()).concat(" :")
								.concat("param").concat(String.valueOf(cont));
						params.add(entry.getValue());
						cont++;
					}

				}
			}

		}

		result.put("finalSql", finalSql);
		result.put("params", params);
		result.put("paramsDates", paramsDates);

		return result;
	}
}
