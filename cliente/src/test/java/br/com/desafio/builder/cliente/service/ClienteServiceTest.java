package br.com.desafio.builder.cliente.service;

import static br.com.desafio.builder.cliente.util.Message.ADAPTER_CLIENTE_ENTITY_FROM_DTO;
import static br.com.desafio.builder.cliente.util.Message.ERROR_INSERIR_CLIENTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static util.ClienteTesteUtil.getClienteDtoRequestInsertMock;
import static util.ClienteTesteUtil.getClienteDtoResponseSemMsgMock;
import static util.ClienteTesteUtil.getClienteEntityFullMock;
import static util.ClienteTesteUtil.getClienteEntityMock;

import javax.persistence.PersistenceException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequestInsert;
import br.com.desafio.builder.cliente.dto.ClienteDtoResponse;
import br.com.desafio.builder.cliente.entity.ClienteEntity;
import br.com.desafio.builder.cliente.exception.AdapterException;
import br.com.desafio.builder.cliente.repository.ClienteRepository;
import br.com.desafio.builder.cliente.util.ClienteAdapter;
import br.com.desafio.builder.cliente.util.Message;
import util.ClienteTesteUtil;

public class ClienteServiceTest {

	@InjectMocks
	ClienteService clienteService; 
	
	@Mock
	ClienteRepository clienteRepositoryMock;
	
	@Mock
	ClienteAdapter clienteAdapterMock;
	
	@BeforeEach
	public void init () {
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	public void testeMsgInserirCliente() throws AdapterException {
		ClienteDtoRequestInsert dtoMock = getClienteDtoRequestInsertMock();
		ClienteEntity entityMock = getClienteEntityMock();
		ClienteEntity entityFullMock = getClienteEntityFullMock();
		ClienteDtoResponse dtoResponseMock = getClienteDtoResponseSemMsgMock();
		String msgEsperada = "Cliente criado com sucesso!";
		
		when(clienteAdapterMock.getClienteEntityFrom(dtoMock)).thenReturn(entityMock);
		when(clienteRepositoryMock.save(entityMock)).thenReturn(entityFullMock);
		when(clienteAdapterMock.getClienteDtoResponseFrom(entityFullMock)).thenReturn(dtoResponseMock);
		
		ClienteDtoResponse dtoResponseRetornado = clienteService.inserirCliente(dtoMock);
		
		assertThat(dtoResponseRetornado.getMsg()).isEqualTo(msgEsperada);
		assertThat(clienteService).isNotNull();
	}
	
	
	@Test
	public void testeAdapterExceptionInserirCliente() throws AdapterException {
		ClienteDtoRequestInsert dtoMock = getClienteDtoRequestInsertMock();
		
		when(clienteAdapterMock.getClienteEntityFrom(dtoMock))
			.thenThrow(new AdapterException(ADAPTER_CLIENTE_ENTITY_FROM_DTO.getMensagem()));
		
		try {
			clienteService.inserirCliente(dtoMock);
			fail("Deveria ter lancado uma excecao");
		} catch (Exception e) {
			assertThat(e.getMessage()).contains(ADAPTER_CLIENTE_ENTITY_FROM_DTO.getMensagem());
		}
	}
	
	
	@Test
	public void testeExceptionInserirCliente() throws AdapterException {
		ClienteDtoRequestInsert dtoMock = getClienteDtoRequestInsertMock();
		ClienteEntity entityMock = getClienteEntityMock();
		
		when(clienteAdapterMock.getClienteEntityFrom(dtoMock))
			.thenReturn(entityMock);
		
		when(clienteRepositoryMock.save(entityMock))
			.thenThrow(new PersistenceException(ERROR_INSERIR_CLIENTE.getMensagem()));
		
		try {
			clienteService.inserirCliente(dtoMock);
			fail("Deveria ter lancado uma excecao");
		} catch (Exception e) {
			assertThat(e.getMessage()).contains(ERROR_INSERIR_CLIENTE.getMensagem());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
