package br.com.desafio.builder.cliente.util;

import static br.com.desafio.builder.cliente.util.Message.ADAPTER_CLIENTE_DTO_RESPONSE_FROM_ENTITY;
import static br.com.desafio.builder.cliente.util.Message.ADAPTER_CLIENTE_ENTITY_FROM_DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequestInsert;
import br.com.desafio.builder.cliente.dto.ClienteDtoResponse;
import br.com.desafio.builder.cliente.entity.ClienteEntity;
import br.com.desafio.builder.cliente.exception.AdapterException;
import br.com.desafio.builder.cliente.exception.ClienteException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClienteAdapter {
	
	@Autowired
	ModelMapper modelMapper;
	
	public ClienteEntity getClienteEntityFrom(ClienteDtoRequestInsert clienteDtoRequest) throws AdapterException {
		try {
			return modelMapper.map(clienteDtoRequest, ClienteEntity.class);
		} catch (Exception error) {		
			log.error(error.getMessage());			
			throw new AdapterException(clienteDtoRequest, "Error: ["+ error.getMessage() +"] : " + ADAPTER_CLIENTE_ENTITY_FROM_DTO.getMensagem());
		}
	}
	
	public ClienteDtoResponse getClienteDtoResponseFrom(ClienteEntity clienteEntity) throws AdapterException {
		try {
			return modelMapper.map(clienteEntity, ClienteDtoResponse.class);
		} catch (Exception error) {		
			log.error(error.getMessage());			
			throw new AdapterException(clienteEntity, "Error: ["+ error.getMessage() +"] : " + ADAPTER_CLIENTE_DTO_RESPONSE_FROM_ENTITY.getMensagem());
		}
	}

}
