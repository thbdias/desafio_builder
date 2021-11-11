package br.com.desafio.builder.cliente.util;

import lombok.Getter;

@Getter
public enum Message {
	
	ERROR_INSERIR_CLIENTE("Erro ao inserir cliente; ClienteDtoRequest = "),
	ADAPTER_CLIENTE_ENTITY_FROM_DTO("Erro em ClienteAdapter.getClienteEntityFrom(); ClienteDtoRequest = ");
	
	private final String mensagem;
	
	Message (String mensagem) {
		this.mensagem = mensagem;
	}

	public static Message valueOfMessage(String mensagem) {
	    for (Message m : values()) {
	        if (m.mensagem.equals(mensagem)) {
	            return m;
	        }
	    }
	    return null;
	}
}
