package br.com.desafio.builder.cliente.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequest;
import br.com.desafio.builder.cliente.entity.ClienteEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClienteAdapter {
	
	@Autowired
	ModelMapper modelMapper;
	
	public ClienteEntity getClienteEntityFrom(ClienteDtoRequest clienteDtoRequest) {
//		try {
			return modelMapper.map(clienteDtoRequest, ClienteEntity.class);
//		} catch (Exception error) {		
//			log.error(error.getMessage());
//			throw new DocumentoVendaException(pveMcrDtoDb2Request, ADAPTER_DOCUMENTO_VENDA_ENTITY_FROM_DB2.getMensagem());
//		}
	}

}
