package br.com.desafio.builder.cliente.exception;

import java.text.MessageFormat;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ClienteExceptionHandler {
	
	@ExceptionHandler(ClienteException.class)
	public ResponseEntity<ClienteError> clienteExceptionHandlerGeneric (ClienteException e, HttpServletRequest request){
		String msg = "Bad Request Exception";
		ClienteError clienteError = new ClienteError(
									LocalDate.now(), 
									HttpStatus.BAD_REQUEST.value(), 
									msg, 
									e.getMessage(), 
									request.getRequestURI(), 
									request.getMethod());
		log.error(MessageFormat.format("Exception: {0}", e.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clienteError);		
	}

}
