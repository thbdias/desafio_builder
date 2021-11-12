package br.com.desafio.builder.cliente.controller;

import static br.com.desafio.builder.cliente.util.ClienteUtil.validarParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequest;
import br.com.desafio.builder.cliente.exception.ClienteException;
import br.com.desafio.builder.cliente.exception.ParamsException;
import br.com.desafio.builder.cliente.service.ClienteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping()
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	

	@PostMapping("/cliente")
	public ResponseEntity<Object> inserirCliente(@RequestBody ClienteDtoRequest clienteDtoRequest) {
		try {
			validarParams(clienteDtoRequest);
			clienteService.inserirCliente(clienteDtoRequest);
		} catch (ParamsException e) {
			log.error(e.getMessage());
			throw new ClienteException(clienteDtoRequest, e.getMessage());
		}		
		return ResponseEntity.ok("Hi");
	}
}
