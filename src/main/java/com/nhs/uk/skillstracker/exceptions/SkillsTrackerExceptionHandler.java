package com.nhs.uk.skillstracker.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is the exception handler class which handles the custom
 * problemDetailsException and returns ResponseEntity which is returned to
 * client.
 * 
 *
 */
@ControllerAdvice
public class SkillsTrackerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ProblemDetailsException.class })
	public ResponseEntity<Object> handleProblemDetailsException(ProblemDetailsException exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setErrorCode(exception.getStatus().value());
		errorDetails.setReasonPhrase(exception.getReasonPhrase());
		errorDetails.setErrorMessage(exception.getMessage());

		return new ResponseEntity<Object>(errorDetails, exception.getStatus());
	}

}
