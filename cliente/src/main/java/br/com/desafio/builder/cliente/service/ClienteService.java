package br.com.desafio.builder.cliente.service;

import static br.com.desafio.builder.cliente.util.Message.ERROR_INSERIR_CLIENTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequest;
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
			clienteRepository.save(clienteAdapter.getClienteEntityFrom(clienteDtoRequest));
		} catch (Exception error) {
			log.error(error.getMessage());
			throw new ClienteException(clienteDtoRequest, ERROR_INSERIR_CLIENTE.getMensagem());
		}
		
	}

}
