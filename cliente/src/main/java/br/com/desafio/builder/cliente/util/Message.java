package br.com.desafio.builder.cliente.util;

import lombok.Getter;

@Getter
public enum Message {
	
	ERROR_PARAMS_CLIENTE_ID("Erro! Id obrigatório e maior que zero!"),
	ERROR_SORT("Erro! Sort deve ser 'asc' ou 'desc'!"),
	ERROR_ORDER_BY("Erro! Campo Inválido! Ordenação deve ser por 'id' ou 'nome' ou 'dataNascimento' ou 'numeroRegistro'!"),
	ERROR_ORDER_BY_E_SORT("Erro! Ou insere orderBy e sort, ou não insira nenhum!"),
	ERROR_PARAMS_CLIENTE_DATA_NASCIMENTO("Erro no formato da data de nascimento (yyyy-MM-dd)!"),
	ERROR_PARAMS_CLIENTE_INSERT("Erro ao inserir cliente; Obs: (nome e data nascimento (yyyy-MM-dd) são obrigatórios; ClienteDtoRequest = "),
	ERROR_FORMAT_DATA_NASCIMENTO("Erro no formato da data de nascimento; Obs: (data nascimento (yyyy-MM-dd)!"),
	ERROR_INSERIR_CLIENTE("Erro ao inserir cliente; ClienteDtoRequest = "),
	ERROR_ATUALIZAR_CLIENTE("Erro ao atualizar cliente; ClienteDtoRequest = "),
	ADAPTER_CLIENTE_ENTITY_FROM_DTO("Erro em ClienteAdapter.getClienteEntityFrom(); ClienteDtoRequest = "),
	ADAPTER_CLIENTE_DTO_RESPONSE_FROM_ENTITY("Erro em ClienteAdapter.getClienteDtoResponseFrom(); ClienteEntity = "),
	CLIENTE_CRIADO("Cliente criado com sucesso!"),
	CLIENTE_ATUALIZADO("Cliente atualizado com sucesso!"),
	CLIENTE_INEXISTENTE("Não é possível identificar o cliente na base de dados!"),
	CLIENTE_INEXISTENTE_PARA_DELETAR("Não é possível excluir o cliente pois o mesmo não se encontra cadastrado!"),
	CLIENTE_DELETADO("Cliente deletado com sucesso!"),
	ERROR_OBTER_CLIENTES("Erro ao consultar clientes!");
	
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
