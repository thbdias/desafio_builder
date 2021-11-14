package br.com.desafio.builder.cliente.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MessageTest {
	
	@Test
	void testGetMessage() {		
		assertThat(Message.ERROR_PARAMS_CLIENTE_ID.getMensagem()).isEqualTo("Erro! Id obrigatório e maior que zero!");
	}
	
	@Test
	void testValueOfMessage() {		
		assertThat(Message.ERROR_OBTER_CLIENTES.getMensagem()).isEqualTo(Message.valueOfMessage("Erro ao consultar clientes!").getMensagem());
	}
	
	@Test
	void testMensagensNull() {		
		assertThat(Message.valueOfMessage("Mensagem inexistente")).isNull();;
	}	
}
