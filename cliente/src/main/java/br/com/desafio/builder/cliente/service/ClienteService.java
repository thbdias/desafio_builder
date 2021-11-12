package br.com.desafio.builder.cliente.service;

import static br.com.desafio.builder.cliente.util.ClienteUtil.obterIdade;
import static br.com.desafio.builder.cliente.util.Message.CLIENTE_DELETADO;
import static br.com.desafio.builder.cliente.util.Message.CLIENTE_INEXISTENTE;
import static br.com.desafio.builder.cliente.util.Message.ERROR_INSERIR_CLIENTE;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequestInsert;
import br.com.desafio.builder.cliente.dto.ClienteDtoResponse;
import br.com.desafio.builder.cliente.entity.ClienteEntity;
import br.com.desafio.builder.cliente.exception.AdapterException;
import br.com.desafio.builder.cliente.exception.ClienteException;
import br.com.desafio.builder.cliente.exception.ParamsException;
import br.com.desafio.builder.cliente.repository.ClienteRepository;
import br.com.desafio.builder.cliente.util.ClienteAdapter;
import br.com.desafio.builder.cliente.util.ClienteUtil;
import br.com.desafio.builder.cliente.util.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ClienteAdapter clienteAdapter;
	
	public void inserirCliente(ClienteDtoRequestInsert clienteDtoRequestInsert) {
		try {
			clienteRepository.save(clienteAdapter.getClienteEntityFrom(clienteDtoRequestInsert));
		} catch (AdapterException e) {
			log.error(e.getMessage());
			throw new ClienteException(clienteDtoRequestInsert, e.getMessage());
		} catch (Exception error) {
			log.error(error.getMessage());
			throw new ClienteException(clienteDtoRequestInsert, 
										"Error: ["+ error.getMessage() +"] : " + ERROR_INSERIR_CLIENTE.getMensagem());
		}
	}

	public ClienteDtoResponse deletarCliente(Integer id) {
		Optional<ClienteEntity> clienteOptional = clienteRepository.findById(id);
		
		if (clienteOptional.isPresent()) {	
			
			try {
				ClienteDtoResponse clienteDtoResponse = clienteAdapter.getClienteDtoResponseFrom(clienteOptional.get());
				clienteDtoResponse.setMsg(CLIENTE_DELETADO.getMensagem());				
				clienteDtoResponse.setIdade(obterIdade(clienteDtoResponse.getDataNascimento().toString()));				
				clienteRepository.deleteById(id);
				return clienteDtoResponse;
				
			} catch (AdapterException | ParamsException e) {
				log.error(e.getMessage());
				throw new ClienteException(clienteOptional.get(), e.getMessage());
			}	
			
		} else {
			log.error(CLIENTE_INEXISTENTE.getMensagem());
			throw new ClienteException(CLIENTE_INEXISTENTE.getMensagem());
		}		
	}

}
