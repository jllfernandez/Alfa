package com.luke.bie.web.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class SolicitudDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Bloque filtro
	private String estado;
	private Number IdSolicitud;
	private String fechaSolicitudOperacionString;
	private LocalDate fechaSolicitudOperacion;
	// Bloque operacion
	private String oficina;
	private String perfil;
	private String oferta;
	private String numeroSIA;
	// Bloque cliente
	private String nombreCliente;
	private String CIF;
	private String direccionActividad;
	private String poblacionActividad;
	private Integer codigoPostalCliente;
	// Bloque segmanetacion cliente
	private Integer numeroEmpleadosUltimoAnyo;
	private Integer numeroEmpleadosAnyoEnCurso;
	private String facturacionUltimoAnyo;
	private String facturacionAnyoCurso;
	private String balanceUltimoAnyo;
	private String balanceAnyoCurso;
	private Integer porcentajePorOtraEntidad;
	private Integer porcentajeEnOtraEntidad;
	private String segmento;
	private Integer CNAE;
	// Bloque operacion
	private String finalidad;
	private String importeFinanciacion;
	private String importeGarantiaBEI;
	private String importeDestinadoCirculante;
	// Bloque informacion report;
	//// Direccion Proyecto de inversion
	private String calleProyectoInversion;
	private String ciudadProyectoInversion;
	private String codigoPostalProyectoInversion;
	private String paisProyectoInversion;
	//// Datos del proyecto
	private String CNAEproyecto;
	private String dentroFuera;
	private String nuts;

	private String fechaPrevistaInicio;
	private LocalDate fechaPrevistaInicioLocalDate;

	private String fechaPrevistaFin;
	private LocalDate fechaPrevistaFinLocalDate;
	private String importeTotal;
	private String descripcion;
	private String comentarios;
	private String garantiasAdicionales;
	private String valorGarantiaAdicional;
	private String tipoGarantiaAdicional;

	private String inversionActividadesAutorizacionEsp;
	private String inversionEnRegadio;

	private Integer vencimientoNoPyme;
	private Integer vencimientoPyme;

	private Integer carencia;
	private String rating;
	private String referencia;
	private Integer pagosPeriodo;

	private Integer tasaGarantiaPyme;
	private Integer tasaGarantiaNoPyme;
	private Double totalPrestamoPyme;
	private Double totalPrestamoNoPyme;
	private Double margenPyme;
	private Integer carenciaPyme;
	private String ratingPyme;
	private Double tipoReferenciaPyme;
	private Integer pagosPorPeriodoPyme;
	private String esbPyme;
	private String esbNoPyme;
	private String observacionesBo;
	private String observacionesOficina;

	// Campo de Calculadora en html
	private String totalPrestamo;

	// Bloque calculos de ayudas
	private String importeAyudasUltimosTresAnyos;
	private String otros;

	private String resultadoValidacion;

	// Indicadores Update
	private String updateSolicitud;
	private String updateObservaciones;
	private String updateEstado;
	private String updateCalculadora;
	private String enviarBO;
	private String fechaAlta;
	private LocalDate fechaAltaLocalDate;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Number getIdSolicitud() {
		return IdSolicitud;
	}

	public void setIdSolicitud(Number idSolicitud) {
		IdSolicitud = idSolicitud;
	}

	public String getFechaSolicitudOperacionString() {
		return fechaSolicitudOperacionString;
	}

	public void setFechaSolicitudOperacionString(String fechaSolicitudOperacionString) {
		this.fechaSolicitudOperacionString = fechaSolicitudOperacionString;
	}

	public LocalDate getFechaSolicitudOperacion() {
		return fechaSolicitudOperacion;
	}

	public void setFechaSolicitudOperacion(LocalDate fechaSolicitudOperacion) {
		this.fechaSolicitudOperacion = fechaSolicitudOperacion;
	}

	public String getOficina() {
		return oficina;
	}

	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getOferta() {
		return oferta;
	}

	public void setOferta(String oferta) {
		this.oferta = oferta;
	}

	public String getNumeroSIA() {
		return numeroSIA;
	}

	public void setNumeroSIA(String numeroSIA) {
		this.numeroSIA = numeroSIA;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getCIF() {
		return CIF;
	}

	public void setCIF(String cIF) {
		CIF = cIF;
	}

	public String getDireccionActividad() {
		return direccionActividad;
	}

	public void setDireccionActividad(String direccionActividad) {
		this.direccionActividad = direccionActividad;
	}

	public String getPoblacionActividad() {
		return poblacionActividad;
	}

	public void setPoblacionActividad(String poblacionActividad) {
		this.poblacionActividad = poblacionActividad;
	}

	public Integer getCodigoPostalCliente() {
		return codigoPostalCliente;
	}

	public void setCodigoPostalCliente(Integer codigoPostalCliente) {
		this.codigoPostalCliente = codigoPostalCliente;
	}

	public Integer getNumeroEmpleadosUltimoAnyo() {
		return numeroEmpleadosUltimoAnyo;
	}

	public void setNumeroEmpleadosUltimoAnyo(Integer numeroEmpleadosUltimoAnyo) {
		this.numeroEmpleadosUltimoAnyo = numeroEmpleadosUltimoAnyo;
	}

	public Integer getNumeroEmpleadosAnyoEnCurso() {
		return numeroEmpleadosAnyoEnCurso;
	}

	public void setNumeroEmpleadosAnyoEnCurso(Integer numeroEmpleadosAnyoEnCurso) {
		this.numeroEmpleadosAnyoEnCurso = numeroEmpleadosAnyoEnCurso;
	}

	public String getFacturacionUltimoAnyo() {
		return facturacionUltimoAnyo;
	}

	public void setFacturacionUltimoAnyo(String facturacionUltimoAnyo) {
		this.facturacionUltimoAnyo = facturacionUltimoAnyo;
	}

	public String getFacturacionAnyoCurso() {
		return facturacionAnyoCurso;
	}

	public void setFacturacionAnyoCurso(String facturacionAnyoCurso) {
		this.facturacionAnyoCurso = facturacionAnyoCurso;
	}

	public String getBalanceUltimoAnyo() {
		return balanceUltimoAnyo;
	}

	public void setBalanceUltimoAnyo(String balanceUltimoAnyo) {
		this.balanceUltimoAnyo = balanceUltimoAnyo;
	}

	public String getBalanceAnyoCurso() {
		return balanceAnyoCurso;
	}

	public void setBalanceAnyoCurso(String balanceAnyoCurso) {
		this.balanceAnyoCurso = balanceAnyoCurso;
	}

	public Integer getPorcentajePorOtraEntidad() {
		return porcentajePorOtraEntidad;
	}

	public void setPorcentajePorOtraEntidad(Integer porcentajePorOtraEntidad) {
		this.porcentajePorOtraEntidad = porcentajePorOtraEntidad;
	}

	public Integer getPorcentajeEnOtraEntidad() {
		return porcentajeEnOtraEntidad;
	}

	public void setPorcentajeEnOtraEntidad(Integer porcentajeEnOtraEntidad) {
		this.porcentajeEnOtraEntidad = porcentajeEnOtraEntidad;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public Integer getCNAE() {
		return CNAE;
	}

	public void setCNAE(Integer cNAE) {
		CNAE = cNAE;
	}

	public String getFinalidad() {
		return finalidad;
	}

	public void setFinalidad(String finalidad) {
		this.finalidad = finalidad;
	}

	public String getImporteFinanciacion() {
		return importeFinanciacion;
	}

	public void setImporteFinanciacion(String importeFinanciacion) {
		this.importeFinanciacion = importeFinanciacion;
	}

	public String getImporteGarantiaBEI() {
		return importeGarantiaBEI;
	}

	public void setImporteGarantiaBEI(String importeGarantiaBEI) {
		this.importeGarantiaBEI = importeGarantiaBEI;
	}

	public String getImporteDestinadoCirculante() {
		return importeDestinadoCirculante;
	}

	public void setImporteDestinadoCirculante(String importeDestinadoCirculante) {
		this.importeDestinadoCirculante = importeDestinadoCirculante;
	}

	public String getCalleProyectoInversion() {
		return calleProyectoInversion;
	}

	public void setCalleProyectoInversion(String calleProyectoInversion) {
		this.calleProyectoInversion = calleProyectoInversion;
	}

	public String getCiudadProyectoInversion() {
		return ciudadProyectoInversion;
	}

	public void setCiudadProyectoInversion(String ciudadProyectoInversion) {
		this.ciudadProyectoInversion = ciudadProyectoInversion;
	}

	public String getCodigoPostalProyectoInversion() {
		return codigoPostalProyectoInversion;
	}

	public void setCodigoPostalProyectoInversion(String codigoPostalProyectoInversion) {
		this.codigoPostalProyectoInversion = codigoPostalProyectoInversion;
	}

	public String getPaisProyectoInversion() {
		return paisProyectoInversion;
	}

	public void setPaisProyectoInversion(String paisProyectoInversion) {
		this.paisProyectoInversion = paisProyectoInversion;
	}

	public String getCNAEproyecto() {
		return CNAEproyecto;
	}

	public void setCNAEproyecto(String cNAEproyecto) {
		CNAEproyecto = cNAEproyecto;
	}

	public String getDentroFuera() {
		return dentroFuera;
	}

	public void setDentroFuera(String dentroFuera) {
		this.dentroFuera = dentroFuera;
	}

	public String getNuts() {
		return nuts;
	}

	public void setNuts(String nuts) {
		this.nuts = nuts;
	}

	public String getFechaPrevistaInicio() {
		return fechaPrevistaInicio;
	}

	public void setFechaPrevistaInicio(String fechaPrevistaInicio) {
		this.fechaPrevistaInicio = fechaPrevistaInicio;
	}

	public String getFechaPrevistaFin() {
		return fechaPrevistaFin;
	}

	public void setFechaPrevistaFin(String fechaPrevistaFin) {
		this.fechaPrevistaFin = fechaPrevistaFin;
	}

	public LocalDate getFechaPrevistaInicioLocalDate() {
		return fechaPrevistaInicioLocalDate;
	}

	public void setFechaPrevistaInicioLocalDate(LocalDate fechaPrevistaInicioLocalDate) {
		this.fechaPrevistaInicioLocalDate = fechaPrevistaInicioLocalDate;
	}

	public LocalDate getFechaPrevistaFinLocalDate() {
		return fechaPrevistaFinLocalDate;
	}

	public void setFechaPrevistaFinLocalDate(LocalDate fechaPrevistaFinLocalDate) {
		this.fechaPrevistaFinLocalDate = fechaPrevistaFinLocalDate;
	}

	public String getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(String importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getGarantiasAdicionales() {
		return garantiasAdicionales;
	}

	public void setGarantiasAdicionales(String garantiasAdicionales) {
		this.garantiasAdicionales = garantiasAdicionales;
	}

	public String getValorGarantiaAdicional() {
		return valorGarantiaAdicional;
	}

	public void setValorGarantiaAdicional(String valorGarantiaAdicional) {
		this.valorGarantiaAdicional = valorGarantiaAdicional;
	}

	public String getTipoGarantiaAdicional() {
		return tipoGarantiaAdicional;
	}

	public String getInversionActividadesAutorizacionEsp() {
		return inversionActividadesAutorizacionEsp;
	}

	public void setInversionActividadesAutorizacionEsp(String inversionActividadesAutorizacionEsp) {
		this.inversionActividadesAutorizacionEsp = inversionActividadesAutorizacionEsp;
	}

	public String getInversionEnRegadio() {
		return inversionEnRegadio;
	}

	public void setInversionEnRegadio(String inversionEnRegadio) {
		this.inversionEnRegadio = inversionEnRegadio;
	}

	public void setTipoGarantiaAdicional(String tipoGarantiaAdicional) {
		this.tipoGarantiaAdicional = tipoGarantiaAdicional;
	}

	public String getImporteAyudasUltimosTresAnyos() {
		return importeAyudasUltimosTresAnyos;
	}

	public void setImporteAyudasUltimosTresAnyos(String importeAyudasUltimosTresAnyos) {
		this.importeAyudasUltimosTresAnyos = importeAyudasUltimosTresAnyos;
	}

	public String getOtros() {
		return otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}

	public Integer getVencimientoPyme() {
		return vencimientoPyme;
	}

	public void setVencimientoPyme(Integer vencimientoPyme) {
		this.vencimientoPyme = vencimientoPyme;
	}

	public Integer getVencimientoNoPyme() {
		return vencimientoNoPyme;
	}

	public void setVencimientoNoPyme(Integer vencimientoNoPyme) {
		this.vencimientoNoPyme = vencimientoNoPyme;
	}

	public Integer getTasaGarantiaPyme() {
		return tasaGarantiaPyme;
	}

	public void setTasaGarantiaPyme(Integer tasaGarantiaPyme) {
		this.tasaGarantiaPyme = tasaGarantiaPyme;
	}

	public Integer getTasaGarantiaNoPyme() {
		return tasaGarantiaNoPyme;
	}

	public void setTasaGarantiaNoPyme(Integer tasaGarantiaNoPyme) {
		this.tasaGarantiaNoPyme = tasaGarantiaNoPyme;
	}

	public Double getTotalPrestamoPyme() {
		return totalPrestamoPyme;
	}

	public void setTotalPrestamoPyme(Double totalPrestamoPyme) {
		this.totalPrestamoPyme = totalPrestamoPyme;
	}

	public Double getTotalPrestamoNoPyme() {
		return totalPrestamoNoPyme;
	}

	public void setTotalPrestamoNoPyme(Double totalPrestamoNoPyme) {
		this.totalPrestamoNoPyme = totalPrestamoNoPyme;
	}

	public Double getMargenPyme() {
		return margenPyme;
	}

	public void setMargenPyme(Double margenPyme) {
		this.margenPyme = margenPyme;
	}

	public Integer getCarenciaPyme() {
		return carenciaPyme;
	}

	public void setCarenciaPyme(Integer carenciaPyme) {
		this.carenciaPyme = carenciaPyme;
	}

	public String getRatingPyme() {
		return ratingPyme;
	}

	public void setRatingPyme(String ratingPyme) {
		this.ratingPyme = ratingPyme;
	}

	public Double getTipoReferenciaPyme() {
		return tipoReferenciaPyme;
	}

	public void setTipoReferenciaPyme(Double tipoReferenciaPyme) {
		this.tipoReferenciaPyme = tipoReferenciaPyme;
	}

	public Integer getPagosPorPeriodoPyme() {
		return pagosPorPeriodoPyme;
	}

	public void setPagosPorPeriodoPyme(Integer pagosPorPeriodoPyme) {
		this.pagosPorPeriodoPyme = pagosPorPeriodoPyme;
	}

	public String getEsbPyme() {
		return esbPyme;
	}

	public void setEsbPyme(String esbPyme) {
		this.esbPyme = esbPyme;
	}

	public String getEsbNoPyme() {
		return esbNoPyme;
	}

	public void setEsbNoPyme(String esbNoPyme) {
		this.esbNoPyme = esbNoPyme;
	}

	public String getTotalPrestamo() {
		return totalPrestamo;
	}

	public void setTotalPrestamo(String totalPrestamo) {
		this.totalPrestamo = totalPrestamo;
	}

	public String getObservacionesBo() {
		return observacionesBo;
	}

	public void setObservacionesBo(String observacionesBo) {
		this.observacionesBo = observacionesBo;
	}

	public String getObservacionesOficina() {
		return observacionesOficina;
	}

	public void setObservacionesOficina(String observacionesOficina) {
		this.observacionesOficina = observacionesOficina;
	}

	public Integer getCarencia() {
		return carencia;
	}

	public void setCarencia(Integer carencia) {
		this.carencia = carencia;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Integer getPagosPeriodo() {
		return pagosPeriodo;
	}

	public void setPagosPeriodo(Integer pagosPeriodo) {
		this.pagosPeriodo = pagosPeriodo;
	}

	public String getResultadoValidacion() {
		return resultadoValidacion;
	}

	public void setResultadoValidacion(String resultadoValidacion) {
		this.resultadoValidacion = resultadoValidacion;
	}

	public String getUpdateSolicitud() {
		return updateSolicitud;
	}

	public void setUpdateSolicitud(String updateSolicitud) {
		this.updateSolicitud = updateSolicitud;
	}

	public String getUpdateObservaciones() {
		return updateObservaciones;
	}

	public void setUpdateObservaciones(String updateObservaciones) {
		this.updateObservaciones = updateObservaciones;
	}

	public String getUpdateEstado() {
		return updateEstado;
	}

	public void setUpdateEstado(String updateEstado) {
		this.updateEstado = updateEstado;
	}

	public String getUpdateCalculadora() {
		return updateCalculadora;
	}

	public void setUpdateCalculadora(String updateCalculadora) {
		this.updateCalculadora = updateCalculadora;
	}

	public String getEnviarBO() {
		return enviarBO;
	}

	public void setEnviarBO(String enviarBO) {
		this.enviarBO = enviarBO;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public LocalDate getFechaAltaLocalDate() {
		return fechaAltaLocalDate;
	}

	public void setFechaAltaLocalDate(LocalDate fechaAltaLocalDate) {
		this.fechaAltaLocalDate = fechaAltaLocalDate;
	}
	
}
