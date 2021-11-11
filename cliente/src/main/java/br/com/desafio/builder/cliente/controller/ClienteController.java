package br.com.desafio.builder.cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequest;
import br.com.desafio.builder.cliente.service.ClienteService;

@RestController
@RequestMapping()
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	

	//adicionar validador de formato de data nascimento yyyy-MM-dd
	@PostMapping("/cliente")
	public ResponseEntity<Object> inserirCliente(@RequestBody ClienteDtoRequest clienteDtoRequest){
		clienteService.inserirCliente(clienteDtoRequest);
		return ResponseEntity.ok("Hi");
	}
}
