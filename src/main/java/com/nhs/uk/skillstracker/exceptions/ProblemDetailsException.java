/**
 * 
 */
package com.nhs.uk.skillstracker.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Custom Exception thrown with proper problem detials.
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class ProblemDetailsException extends Exception implements Serializable {

	private static final long serialVersionUID = 1270965746625837932L;

	private HttpStatus status;
	
	private String reasonPhrase;
	
	public ProblemDetailsException(String reasonPhrase, Throwable cause) {
        super(reasonPhrase, cause);
    }

	public ProblemDetailsException(HttpStatus status, String reasonPhrase, String detail) {
		super(detail);
		this.status = status;
        this.reasonPhrase = reasonPhrase;
	}

}
