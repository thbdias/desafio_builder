package br.com.desafio.builder.cliente.service;

import static br.com.desafio.builder.cliente.util.Message.ADAPTER_CLIENTE_DTO_RESPONSE_FROM_ENTITY;
import static br.com.desafio.builder.cliente.util.Message.ADAPTER_CLIENTE_ENTITY_FROM_DTO;
import static br.com.desafio.builder.cliente.util.Message.CLIENTE_ATUALIZADO;
import static br.com.desafio.builder.cliente.util.Message.CLIENTE_INEXISTENTE;
import static br.com.desafio.builder.cliente.util.Message.CLIENTE_INEXISTENTE_PARA_DELETAR;
import static br.com.desafio.builder.cliente.util.Message.ERROR_ATUALIZAR_CLIENTE;
import static br.com.desafio.builder.cliente.util.Message.ERROR_INSERIR_CLIENTE;
import static br.com.desafio.builder.cliente.util.Message.ERROR_OBTER_CLIENTES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static util.ClienteTesteUtil.getClienteDtoRequestInsertMock;
import static util.ClienteTesteUtil.getClienteDtoRequestMock;
import static util.ClienteTesteUtil.getClienteDtoResponseSemMsgMock;
import static util.ClienteTesteUtil.getClienteEntityFullMock;
import static util.ClienteTesteUtil.getClienteEntityMock;
import static util.ClienteTesteUtil.getOptionalClienteEntityMock;
import static util.ClienteTesteUtil.getPageRequestMock;
import static util.ClienteTesteUtil.getSpecificationFindAllMock;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequest;
import br.com.desafio.builder.cliente.dto.ClienteDtoRequestInsert;
import br.com.desafio.builder.cliente.dto.ClienteDtoResponse;
import br.com.desafio.builder.cliente.entity.ClienteEntity;
import br.com.desafio.builder.cliente.exception.AdapterException;
import br.com.desafio.builder.cliente.repository.ClienteRepository;
import br.com.desafio.builder.cliente.util.ClienteAdapter;

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
	
	
	@Test
	public void testeClientExceptionDeletarCliente()  {
		
		when(clienteRepositoryMock.findById(7)).thenReturn(Optional.empty());
		
		try {
			clienteService.deletarCliente(7);
			fail("Deveria ter lancado uma excecao");
		} catch (Exception e) {
			assertThat(e.getMessage()).contains(CLIENTE_INEXISTENTE_PARA_DELETAR.getMensagem());
		}
	}
	
	
	@Test
	public void testeAdapterExceptionDeletarCliente() throws AdapterException  {		
		ClienteEntity entityFullMock = getClienteEntityFullMock();
		
		when(clienteRepositoryMock.findById(7)).thenReturn(getOptionalClienteEntityMock());
		when(clienteAdapterMock.getClienteDtoResponseFrom(entityFullMock))
			.thenThrow(new AdapterException(ADAPTER_CLIENTE_DTO_RESPONSE_FROM_ENTITY.getMensagem()));
		
		try {
			clienteService.deletarCliente(7);
			fail("Deveria ter lancado uma excecao");
		} catch (Exception e) {
			assertThat(e.getMessage()).contains(ADAPTER_CLIENTE_DTO_RESPONSE_FROM_ENTITY.getMensagem());
		}		
	}
	
	
	@Test
	public void testeMsgDeletarCliente() throws AdapterException  {		
		ClienteEntity entityFullMock = getClienteEntityFullMock();		
		ClienteDtoResponse dtoResponseMock = getClienteDtoResponseSemMsgMock();
		
		when(clienteRepositoryMock.findById(7)).thenReturn(getOptionalClienteEntityMock());
		when(clienteAdapterMock.getClienteDtoResponseFrom(entityFullMock)).thenReturn(dtoResponseMock);
		
		ClienteDtoResponse deletarCliente = clienteService.deletarCliente(7);
		
		assertThat(deletarCliente.getMsg()).isEqualTo("Cliente deletado com sucesso!");
	}
	
	
	@Test
	public void testeClientExceptionAtualizarCliente()  {
		
		when(clienteRepositoryMock.findById(7)).thenReturn(Optional.empty());
		
		try {
			clienteService.atualizarCliente(getClienteDtoRequestMock());
			fail("Deveria ter lancado uma excecao");
		} catch (Exception e) {
			assertThat(e.getMessage()).contains(CLIENTE_INEXISTENTE.getMensagem());
		}
	}
	
	
	@Test
	public void testeAdapterExceptionAtualizarCliente() throws AdapterException  {		
		ClienteDtoRequest clienteDtoRequestMock = getClienteDtoRequestMock();
		
		when(clienteRepositoryMock.findById(7)).thenReturn(getOptionalClienteEntityMock());
		when(clienteAdapterMock.getClienteEntityFrom(clienteDtoRequestMock))
			.thenThrow(new AdapterException(ADAPTER_CLIENTE_ENTITY_FROM_DTO.getMensagem()));
		
		try {
			clienteService.atualizarCliente(clienteDtoRequestMock);
			fail("Deveria ter lancado uma excecao");
		} catch (Exception e) {
			assertThat(e.getMessage()).contains(ADAPTER_CLIENTE_ENTITY_FROM_DTO.getMensagem());
		}		
	}
	
	
	@Test
	public void testeExceptionAtualizarCliente() throws AdapterException {
		ClienteDtoRequest clienteDtoRequestMock = getClienteDtoRequestMock();
		
		when(clienteRepositoryMock.findById(7)).thenReturn(getOptionalClienteEntityMock());
		when(clienteAdapterMock.getClienteEntityFrom(clienteDtoRequestMock)).thenReturn(null);
		
		try {
			clienteService.atualizarCliente(clienteDtoRequestMock);
			fail("Deveria ter lancado uma excecao");
		} catch (Exception e) {
			assertThat(e.getMessage()).contains(ERROR_ATUALIZAR_CLIENTE.getMensagem());
		}
	}
	
	
	@Test
	public void testeMsgAtualizarCliente() throws AdapterException {
		Optional<ClienteEntity> optionalClienteEntityMock = getOptionalClienteEntityMock();
		ClienteDtoRequest clienteDtoRequestMock = getClienteDtoRequestMock();
		ClienteEntity clienteEntityFullMock = getClienteEntityFullMock();
		ClienteDtoResponse clienteDtoResponseSemMsgMock = getClienteDtoResponseSemMsgMock();
		
		
		when(clienteRepositoryMock.findById(7)).thenReturn(optionalClienteEntityMock);
		when(clienteAdapterMock.getClienteEntityFrom(clienteDtoRequestMock)).thenReturn(clienteEntityFullMock);
		when(clienteRepositoryMock.save(clienteEntityFullMock)).thenReturn(clienteEntityFullMock);
		when(clienteAdapterMock.getClienteDtoResponseFrom(clienteEntityFullMock)).thenReturn(clienteDtoResponseSemMsgMock);
		
		clienteService.atualizarCliente(clienteDtoRequestMock);
		assertThat(clienteDtoResponseSemMsgMock.getMsg()).contains(CLIENTE_ATUALIZADO.getMensagem());
	}
	
	
	@Test
	public void testeExceptionObterClientes() {
		when(clienteRepositoryMock.findAll(getSpecificationFindAllMock(), getPageRequestMock()))
			.thenThrow(new PersistenceException(ERROR_OBTER_CLIENTES.getMensagem()));
		
		try {
			clienteService.obterClientes(null, null);
			fail("Deveria ter lancado uma excecao");
		} catch (Exception e) {
			assertThat(e.getMessage()).contains(ERROR_OBTER_CLIENTES.getMensagem());
		}
	}
	
}
