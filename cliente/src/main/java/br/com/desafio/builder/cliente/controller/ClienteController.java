package br.com.desafio.builder.cliente.controller;

import static br.com.desafio.builder.cliente.util.ClienteUtil.validarOrderByAndSort;
import static br.com.desafio.builder.cliente.util.ClienteUtil.validarParamDataNascimento;
import static br.com.desafio.builder.cliente.util.ClienteUtil.validarParamId;
import static br.com.desafio.builder.cliente.util.ClienteUtil.validarParams;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping()
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@ApiOperation(value = "Cria um cliente")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Cadastro efetivado"),
		    @ApiResponse(code = 400, message = "Foi gerada uma exceção"),
		})
	@PostMapping(value = "/cliente", produces="application/json", consumes="application/json")
	public ResponseEntity<Object> inserirCliente(@RequestBody ClienteDtoRequestInsert clienteDtoRequestInsert) {
		try {
			validarParams(clienteDtoRequestInsert);
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.inserirCliente(clienteDtoRequestInsert));
		} catch (ParamsException e) {
			log.error(e.getMessage());
			throw new ClienteException(clienteDtoRequestInsert, e.getMessage());
		}		
	}
	
	
	@ApiOperation(value = "Deleta um cliente")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Cliente deletado"),
		    @ApiResponse(code = 400, message = "Foi gerada uma exceção"),
		})
	@Transactional
	@DeleteMapping(value = "/cliente", produces="application/json")	
	public ResponseEntity<Object> deletarCliente(@RequestParam(required = true) Integer id){
		return ResponseEntity.ok(clienteService.deletarCliente(id));
	}
		
	
	@ApiOperation(value = "Retorna uma lista de clientes")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Busca efetivada"),
		    @ApiResponse(code = 400, message = "Foi gerada uma exceção"),
		})
	@GetMapping(value = "/clientes", produces="application/json")
	public ResponseEntity<Object> obterClientes(ClienteDtoRequest clienteDtoRequest, PageRequestDTO pageRequestDTO) {
		try {
			validarParamDataNascimento(clienteDtoRequest);
			validarOrderByAndSort(pageRequestDTO);
			return ResponseEntity.ok(clienteService.obterClientes(clienteDtoRequest, pageRequestDTO));
		} catch (ParamsException e) {
			log.error(e.getMessage());
			throw new ClienteException(e.getMessage());
		}
	}
	
	
	@ApiOperation(value = "Atualiza um cliente")
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = "Atualização efetivada"),
		    @ApiResponse(code = 400, message = "Foi gerada uma exceção"),
		})
	@PutMapping(value = "/cliente", produces="application/json", consumes="application/json")
	public ResponseEntity<Object> atuaizarCliente(@RequestBody ClienteDtoRequest clienteDtoRequest) {
		try {
			validarParamId(clienteDtoRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.atualizarCliente(clienteDtoRequest));
		} catch (ParamsException e) {
			log.error(e.getMessage());
			throw new ClienteException(clienteDtoRequest, e.getMessage());
		}		
	}
}
