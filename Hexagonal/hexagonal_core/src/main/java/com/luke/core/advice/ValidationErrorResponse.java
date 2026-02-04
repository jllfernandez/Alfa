package com.luke.core.advice;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {

	private List<Violation> violations = new ArrayList<Violation>();

	public List<Violation> getViolations() {
		return violations;
	}

	public void setViolations(List<Violation> violations) {
		this.violations = violations;
	}

}
