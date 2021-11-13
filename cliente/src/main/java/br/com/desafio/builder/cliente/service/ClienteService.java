package br.com.desafio.builder.cliente.service;

import static br.com.desafio.builder.cliente.util.Message.CLIENTE_CRIADO;
import static br.com.desafio.builder.cliente.util.Message.CLIENTE_DELETADO;
import static br.com.desafio.builder.cliente.util.Message.CLIENTE_INEXISTENTE;
import static br.com.desafio.builder.cliente.util.Message.ERROR_INSERIR_CLIENTE;
import static br.com.desafio.builder.cliente.util.Message.ERROR_OBTER_CLIENTES;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequest;
import br.com.desafio.builder.cliente.dto.ClienteDtoRequestInsert;
import br.com.desafio.builder.cliente.dto.ClienteDtoResponse;
import br.com.desafio.builder.cliente.dto.PageRequestDTO;
import br.com.desafio.builder.cliente.entity.ClienteEntity;
import br.com.desafio.builder.cliente.exception.AdapterException;
import br.com.desafio.builder.cliente.exception.ClienteException;
import br.com.desafio.builder.cliente.repository.ClienteRepository;
import br.com.desafio.builder.cliente.util.ClienteAdapter;
import br.com.desafio.builder.cliente.util.RepositoryUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ClienteAdapter clienteAdapter;
	
	public ClienteDtoResponse inserirCliente(ClienteDtoRequestInsert clienteDtoRequestInsert) {
		try {			
			ClienteDtoResponse clienteDtoResponse = clienteAdapter.getClienteDtoResponseFrom(
														clienteRepository.save(
																clienteAdapter.getClienteEntityFrom(clienteDtoRequestInsert)));
			
			clienteDtoResponse.setMsg(CLIENTE_CRIADO.getMensagem());
			return clienteDtoResponse;			
			
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
				clienteRepository.deleteById(id);
				return clienteDtoResponse;
				
			} catch (AdapterException e) {
				log.error(e.getMessage());
				throw new ClienteException(clienteOptional.get(), e.getMessage());
			}	
			
		} else {
			log.error(CLIENTE_INEXISTENTE.getMensagem());
			throw new ClienteException(CLIENTE_INEXISTENTE.getMensagem());
		}		
	}

	public Page<ClienteDtoResponse> obterClientes(ClienteDtoRequest clienteDtoRequest, PageRequestDTO pageRequestDTO) {
		try {
			var specification = RepositoryUtil.buildClienteSpecification(clienteDtoRequest);
			var pageRequest = RepositoryUtil.getPageRequestFromPageDTO(pageRequestDTO);
			
			var clientesPage = clienteRepository.findAll(specification, pageRequest);
			
			var listClienteDtoResponse = clienteAdapter.getListClienteDtoFrom(clientesPage);
			
			return new PageImpl<>(
							listClienteDtoResponse, 
							clientesPage.getPageable(), 
							clientesPage.getTotalElements());
			
		} catch (RuntimeException error) {
			log.error(error.getMessage());
			throw new ClienteException(ERROR_OBTER_CLIENTES.getMensagem());
		}
	}

}
