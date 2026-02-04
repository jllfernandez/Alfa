package com.luke.core.advice;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

	@ExceptionHandler(NoSuchElementException.class)
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ValidationErrorResponse onNoSuchElementException(NoSuchElementException e) {
		ValidationErrorResponse error = new ValidationErrorResponse();
		error.getViolations().add(new Violation(e.getLocalizedMessage(), e.getMessage()));
		return error;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
		ValidationErrorResponse error = new ValidationErrorResponse();
		error.getViolations().add(new Violation(e.getLocalizedMessage(), e.getMessage()));
		return error;
//		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
//			error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
//		}
//		return error;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ValidationErrorResponse error = new ValidationErrorResponse();
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
		}
		return error;
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	ValidationErrorResponse onMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		ValidationErrorResponse error = new ValidationErrorResponse();
		error.getViolations().add(new Violation(e.getName(), e.getMessage()));
		return error;
	}

	@ExceptionHandler(SQLException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	ValidationErrorResponse onSQLException(SQLException e) {
		ValidationErrorResponse error = new ValidationErrorResponse();
		error.getViolations().add(new Violation(e.getSQLState(), e.getMessage()));
		return error;
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	ValidationErrorResponse onNullPointerException(NullPointerException e) {
		ValidationErrorResponse error = new ValidationErrorResponse();
		error.getViolations().add(new Violation(e.getLocalizedMessage(), e.getMessage()));
		return error;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	ValidationErrorResponse onException(Exception e) {
		ValidationErrorResponse error = new ValidationErrorResponse();
		error.getViolations().add(new Violation(e.getLocalizedMessage(), e.getMessage()));
		return error;
	}

}
