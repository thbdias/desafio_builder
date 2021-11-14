package util;

import java.time.LocalDate;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequestInsert;
import br.com.desafio.builder.cliente.dto.ClienteDtoResponse;
import br.com.desafio.builder.cliente.entity.ClienteEntity;

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
	
	public static ClienteEntity getClienteEntityMock() {
		return ClienteEntity
				.builder()
					.nome("abc")
					.dataNascimento(LocalDate.parse("2005-02-05"))
					.numeroRegistro(3)
				.build();
	}
	
	public static ClienteEntity getClienteEntityFullMock() {
		return ClienteEntity
				.builder()
					.id(7)
					.nome("abc")
					.dataNascimento(LocalDate.parse("2005-02-05"))
					.numeroRegistro(3)
				.build();
	}
	
	public static ClienteDtoResponse getClienteDtoResponseSemMsgMock() {
		return ClienteDtoResponse
				.builder()					
					.nome("abc")
					.dataNascimento("2005-02-05")
					.numeroRegistro(3)
					.idade(16)
				.build();
	}

}
