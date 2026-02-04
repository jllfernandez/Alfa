package com.luke.core.ad.daos;

public enum Operators {

	LIKE("LIKE"), LIKE_SENSITIVE("LIKE_SENSITIVE"), EQUAL("="), MAJOR(">"), MAJOR_EQUAL(">="), MINOR("<"), MINOR_EQUAL("<="), BETWEEN("BETWEEN");

	private final String operador;

	public String getOperador() {
		return this.operador;
	}

	public String toString() {
		return this.operador;
	}

	Operators(String operador) {
		this.operador = operador;
	}
}
