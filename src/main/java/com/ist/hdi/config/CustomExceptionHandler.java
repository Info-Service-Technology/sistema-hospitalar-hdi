package com.ist.hdi.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	    MethodArgumentNotValidException ex,
	    HttpHeaders headers,
	    HttpStatusCode status,
	    WebRequest request) {

	    Map<String, Object> body = new LinkedHashMap<>();
	    body.put("status", 400);
	    body.put("error", "Bad Request");
	    body.put("message", "Erro de validação");
	    Map<String, String> fieldErrors = new HashMap<>();
	    ex.getBindingResult().getFieldErrors().forEach(error ->
	        fieldErrors.put(error.getField(), error.getDefaultMessage())
	    );
	    body.put("fieldErrors", fieldErrors);

	    return ResponseEntity.badRequest().body(body);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGenericException(Exception ex) {
	    Map<String, Object> body = new LinkedHashMap<>();
	    body.put("status", 500);
	    body.put("error", "Internal Server Error");
	    body.put("message", "Erro interno do servidor");
	    return ResponseEntity.status(500).body(body);
	}

}
