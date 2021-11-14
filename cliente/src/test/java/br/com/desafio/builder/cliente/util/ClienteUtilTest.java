package br.com.desafio.builder.cliente.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static util.ClienteTesteUtil.getClienteDtoRequestComDataInvalida;
import static util.ClienteTesteUtil.getClienteDtoRequestComDataInvalida2;
import static util.ClienteTesteUtil.getClienteDtoRequestComDataInvalida3;
import static util.ClienteTesteUtil.getClienteDtoRequestComDataInvalida4;
import static util.ClienteTesteUtil.getClienteDtoRequestComDataValida;
import static util.ClienteTesteUtil.getClienteDtoRequestComIdNegativoMock;
import static util.ClienteTesteUtil.getClienteDtoRequestComIdNullMock;
import static util.ClienteTesteUtil.getClienteDtoRequestNullMock;
import static util.ClienteTesteUtil.getPageRequestDTOErroOrderByMock;
import static util.ClienteTesteUtil.getPageRequestDTOErroSortIncompativel2Mock;
import static util.ClienteTesteUtil.getPageRequestDTOErroSortIncompativelMock;
import static util.ClienteTesteUtil.getPageRequestDTOErroSortMock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.desafio.builder.cliente.exception.ParamsException;
import util.ClienteTesteUtil;

public class ClienteUtilTest {
	
	@Test
	public void testSortExceptionValidarOrderByAndSort() {
		String msgEsperada = "Erro! Sort deve ser 'asc' ou 'desc'!";
		try {			
			ClienteUtil.validarOrderByAndSort(getPageRequestDTOErroSortMock());
		} catch (Exception e) {
			assertThat(e.getMessage()).contains(msgEsperada);			
		}
	}

	@Test
	public void testOrderByExceptionValidarOrderByAndSort() {
		String msgEsperada = "Erro! Campo Inválido! Ordenação deve ser por 'id' ou 'nome' ou 'dataNascimento' ou 'numeroRegistro'!";
		try {			
			ClienteUtil.validarOrderByAndSort(getPageRequestDTOErroOrderByMock());
		} catch (Exception e) {
			assertThat(e.getMessage()).contains(msgEsperada);			
		}
	}
	
	@Test
	public void testSortIncompativelExceptionValidarOrderByAndSort() {
		String msgEsperada = "Erro! Ou insere orderBy e sort, ou não insira nenhum!";
		try {			
			ClienteUtil.validarOrderByAndSort(getPageRequestDTOErroSortIncompativelMock());
		} catch (Exception e) {
			assertThat(e.getMessage()).contains(msgEsperada);			
		}
	}
	
	@Test
	public void testSortIncompativel2ExceptionValidarOrderByAndSort() {
		String msgEsperada = "Erro! Ou insere orderBy e sort, ou não insira nenhum!";
		try {			
			ClienteUtil.validarOrderByAndSort(getPageRequestDTOErroSortIncompativel2Mock());
		} catch (Exception e) {
			assertThat(e.getMessage()).contains(msgEsperada);			
		}
	}
	
	@Test
	public void testValidarParamIdClienteDtoRequestNull() {
		String msgEsperada = "Erro! Id obrigatório e maior que zero!";
		try {			
			ClienteUtil.validarParamId(getClienteDtoRequestNullMock());
		} catch (Exception e) {
			assertThat(e.getMessage()).contains(msgEsperada);			
		}
	}
	
	@Test
	public void testValidarParamIdClienteDtoRequestComIdNull() {
		String msgEsperada = "Erro! Id obrigatório e maior que zero!";
		try {			
			ClienteUtil.validarParamId(getClienteDtoRequestComIdNullMock());
		} catch (Exception e) {
			assertThat(e.getMessage()).contains(msgEsperada);			
		}
	}
	
	@Test
	public void testValidarParamIdClienteDtoRequestComIdNegativo() {
		String msgEsperada = "Erro! Id obrigatório e maior que zero!";
		try {			
			ClienteUtil.validarParamId(getClienteDtoRequestComIdNegativoMock());
		} catch (Exception e) {
			assertThat(e.getMessage()).contains(msgEsperada);			
		}
	}
	
	@Test
	public void testeValidarParamDataNascimentoComClienteDtoRequestNull() throws ParamsException {
		String msgEsperada = "Erro no formato da data de nascimento (yyyy-MM-dd)!";
		ClienteUtil.validarParamDataNascimento(getClienteDtoRequestNullMock());
	}
	
	@Test
	public void testeValidarParamDataNascimentoComDataInvalida() {
		String msgEsperada = "Erro no formato da data de nascimento (yyyy-MM-dd)!";
		try {
			ClienteUtil.validarParamDataNascimento(getClienteDtoRequestComDataInvalida());
		} catch (ParamsException e) {
			assertThat(e.getMessage()).contains(msgEsperada);
		}
	}
	
	@Test
	public void testeValidarParamDataNascimentoComDataInvalida2() {
		String msgEsperada = "Erro no formato da data de nascimento (yyyy-MM-dd)!";
		try {
			ClienteUtil.validarParamDataNascimento(getClienteDtoRequestComDataInvalida2());
		} catch (ParamsException e) {
			assertThat(e.getMessage()).contains(msgEsperada);
		}
	}
	
	@Test
	public void testeValidarParamDataNascimentoComDataInvalida3() {
		String msgEsperada = "Erro no formato da data de nascimento (yyyy-MM-dd)!";
		try {
			ClienteUtil.validarParamDataNascimento(getClienteDtoRequestComDataInvalida3());
		} catch (ParamsException e) {
			assertThat(e.getMessage()).contains(msgEsperada);
		}
	}
	
	@Test
	public void testeValidarParamDataNascimentoComDataInvalida4() {
		String msgEsperada = "Erro no formato da data de nascimento (yyyy-MM-dd)!";
		try {
			ClienteUtil.validarParamDataNascimento(getClienteDtoRequestComDataInvalida4());
		} catch (ParamsException e) {
			assertThat(e.getMessage()).contains(msgEsperada);
		}
	}
	
	@Test
	public void testeValidarParamDataNascimentoComDataValida() {
		try {
			ClienteUtil.validarParamDataNascimento(getClienteDtoRequestComDataValida());
		} catch (ParamsException e) {
			fail("Não Deve lançar uma excecao");
		}
	}
}
