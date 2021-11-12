package br.com.desafio.builder.cliente.service;

import static br.com.desafio.builder.cliente.util.Message.ERROR_INSERIR_CLIENTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequest;
import br.com.desafio.builder.cliente.entity.ClienteEntity;
import br.com.desafio.builder.cliente.exception.AdapterException;
import br.com.desafio.builder.cliente.exception.ClienteException;
import br.com.desafio.builder.cliente.repository.ClienteRepository;
import br.com.desafio.builder.cliente.util.ClienteAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ClienteAdapter clienteAdapter;
	
	public void inserirCliente(ClienteDtoRequest clienteDtoRequest) {
		try {
			ClienteEntity clienteEntityFrom = clienteAdapter.getClienteEntityFrom(clienteDtoRequest);
			clienteRepository.save(clienteEntityFrom);
		} catch (AdapterException e) {
			log.error(e.getMessage());
			throw new ClienteException(clienteDtoRequest, e.getMessage());
		} catch (Exception error) {
			log.error(error.getMessage());
			throw new ClienteException(clienteDtoRequest, "Error: ["+ error.getMessage() +"] : " + ERROR_INSERIR_CLIENTE.getMensagem());
		}
	}

}
