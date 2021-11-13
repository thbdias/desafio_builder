package util;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequestInsert;

public class ClienteTesteUtil {
	
	public static ClienteDtoRequestInsert getClienteDtoRequestInsertMock() {
		return ClienteDtoRequestInsert
				.builder()
					.nome("abc")
					.dataNascimento("2005-02-05")
					.numeroRegistro(3)
				.build();
	}
	
	public static ClienteDtoRequestInsert getClienteDtoRequestInsertComDataNullMock() {
		return ClienteDtoRequestInsert
				.builder()
					.nome("abc")
					.dataNascimento(null)
					.numeroRegistro(3)
				.build();
	}

}
