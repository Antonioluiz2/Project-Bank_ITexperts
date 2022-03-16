package com.itexperts.java.projeto.controller;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerErrorHandler extends ResponseEntityExceptionHandler {
	//TODO mapear Exceptions.
}
