package br.com.desafio.builder.cliente.controller;

import static br.com.desafio.builder.cliente.util.ClienteUtil.validarParamDataNascimento;
import static br.com.desafio.builder.cliente.util.ClienteUtil.validarParams;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequest;
import br.com.desafio.builder.cliente.dto.ClienteDtoRequestInsert;
import br.com.desafio.builder.cliente.dto.PageRequestDTO;
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
	public ResponseEntity<Object> inserirCliente(@RequestBody ClienteDtoRequestInsert clienteDtoRequestInsert) {
		try {
			validarParams(clienteDtoRequestInsert);
			return ResponseEntity.ok(clienteService.inserirCliente(clienteDtoRequestInsert));
		} catch (ParamsException e) {
			log.error(e.getMessage());
			throw new ClienteException(clienteDtoRequestInsert, e.getMessage());
		}		
	}
	
	
	@Transactional
	@DeleteMapping("/cliente")	
	public ResponseEntity<Object> deletarCliente(@RequestParam(required = true) Integer id){
		return ResponseEntity.ok(clienteService.deletarCliente(id));
	}
	
	
	@GetMapping("/clientes")
	public ResponseEntity<Object> obterClientes(ClienteDtoRequest clienteDtoRequest, PageRequestDTO pageRequestDTO) {
		try {
			validarParamDataNascimento(clienteDtoRequest.getDataNascimento());
			return ResponseEntity.ok(clienteService.obterClientes(clienteDtoRequest, pageRequestDTO));
		} catch (ParamsException e) {
			log.error(e.getMessage());
			throw new ClienteException(clienteDtoRequest, e.getMessage());
		}
	}
}
