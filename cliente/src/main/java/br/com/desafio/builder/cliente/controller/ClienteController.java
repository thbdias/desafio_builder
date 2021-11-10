package br.com.desafio.builder.cliente.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class ClienteController {

	@GetMapping("/clientes")
	public ResponseEntity<Object> obterClientes(){
		return ResponseEntity.ok("Hi");
	}
}
