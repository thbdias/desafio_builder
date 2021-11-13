package br.com.desafio.builder.cliente.util;

import static br.com.desafio.builder.cliente.util.ClienteUtil.obterIdade;
import static br.com.desafio.builder.cliente.util.Message.ADAPTER_CLIENTE_DTO_RESPONSE_FROM_ENTITY;
import static br.com.desafio.builder.cliente.util.Message.ADAPTER_CLIENTE_ENTITY_FROM_DTO;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequestInsert;
import br.com.desafio.builder.cliente.dto.ClienteDtoResponse;
import br.com.desafio.builder.cliente.entity.ClienteEntity;
import br.com.desafio.builder.cliente.exception.AdapterException;
import br.com.desafio.builder.cliente.exception.ClienteException;
import br.com.desafio.builder.cliente.exception.ParamsException;
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
			ClienteDtoResponse dto = modelMapper.map(clienteEntity, ClienteDtoResponse.class);							
			dto.setIdade(obterIdade(dto.getDataNascimento().toString()));
			return dto;
		} catch (Exception error) {		
			log.error(error.getMessage());			
			throw new AdapterException(clienteEntity, "Error: ["+ error.getMessage() +"] : " + ADAPTER_CLIENTE_DTO_RESPONSE_FROM_ENTITY.getMensagem());
		}
	}

	public List<ClienteDtoResponse> getListClienteDtoFrom(Iterable<ClienteEntity> clienteEntityItarable) {
		if (nonNull(clienteEntityItarable)) {						
			List<ClienteEntity> listClienteEntity = new ArrayList<>();
			clienteEntityItarable.forEach(listClienteEntity::add);
			return getListClienteDtoFrom(listClienteEntity);
		}
		return new ArrayList<>();
	}
	

	public List<ClienteDtoResponse> getListClienteDtoFrom(List<ClienteEntity> listClienteEntity) {		
		if (nonNull(listClienteEntity)) {		
			try {
				
				List<ClienteDtoResponse> listaClienteDtoResponse = new ArrayList<>();
				
				for (ClienteEntity clienteEntity: listClienteEntity) {
					ClienteDtoResponse clienteDtoResponse = modelMapper.map(clienteEntity, ClienteDtoResponse.class);					
					clienteDtoResponse.setIdade(obterIdade(clienteDtoResponse.getDataNascimento().toString()));
					listaClienteDtoResponse.add(clienteDtoResponse);
				}
				return listaClienteDtoResponse;
				
			} catch (ParamsException e) {				
				log.error(e.getMessage());
				throw new ClienteException(e.getMessage());				
			}
		}
		return new ArrayList<>();
	}

}
