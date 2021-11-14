package br.com.desafio.builder.cliente.service;

import static org.assertj.core.api.Assertions.assertThat;
import static util.ClienteTesteUtil.getClienteDtoRequestComDataMock;
import static util.ClienteTesteUtil.getClienteDtoRequestComIdMock;
import static util.ClienteTesteUtil.getClienteDtoRequestComNomeMock;
import static util.ClienteTesteUtil.getClienteDtoRequestComNumeroRegistroMock;
import static util.ClienteTesteUtil.getClienteDtoRequestMock;
import static util.ClienteTesteUtil.getPageRequestDTOComNomeDescMock;
import static util.ClienteTesteUtil.getPageRequestDTOMock;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import br.com.desafio.builder.cliente.dto.ClienteDtoResponse;
import util.ClienteTesteUtil;
import util.DatabaseTestManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class EmpresaAclServiceComponentTest {


    @Autowired
    ClienteService clienteService;

    @BeforeAll
    static void setUp() throws FileNotFoundException, SQLException {
        DatabaseTestManager.createDatabase();
    }

    
    @Test
    void testePesquisaNumeroRegistro() {
    	Page<ClienteDtoResponse> obterClientesPage = clienteService
    													.obterClientes(
															getClienteDtoRequestComNumeroRegistroMock(), 
		    												getPageRequestDTOMock());
    	
    	assertThat(obterClientesPage.getTotalPages()).isEqualTo(2);
        assertThat(obterClientesPage.getTotalElements()).isEqualTo(4);
    }
    
    
    @Test
    void testePesquisaId() {
    	Page<ClienteDtoResponse> obterClientesPage = clienteService
    													.obterClientes(
															getClienteDtoRequestComIdMock(), 
		    												getPageRequestDTOMock());
    	
        assertThat(obterClientesPage.getTotalElements()).isEqualTo(1);
    }
    
    @Test
    void testePesquisaNome() {
    	Page<ClienteDtoResponse> obterClientesPage = clienteService
    													.obterClientes(
															getClienteDtoRequestComNomeMock(), 
		    												getPageRequestDTOMock());
    	
        assertThat(obterClientesPage.getTotalElements()).isEqualTo(7);
    }
    
    @Test
    void testePesquisaData() {
    	Page<ClienteDtoResponse> obterClientesPage = clienteService
    													.obterClientes(
															getClienteDtoRequestComDataMock(), 
		    												getPageRequestDTOMock());
    	
        assertThat(obterClientesPage.getTotalElements()).isEqualTo(8);
    }
    
    @Test
    void testePesquisaDataENomeDesc() {
    	Page<ClienteDtoResponse> obterClientesPage = clienteService
    													.obterClientes(
															getClienteDtoRequestComDataMock(), 
															getPageRequestDTOComNomeDescMock());
    	
        assertThat(obterClientesPage.getTotalElements()).isEqualTo(8);
        assertThat(obterClientesPage.getSort().toString()).contains("DESC");
    }
    //ClienteTesteUtil
}
