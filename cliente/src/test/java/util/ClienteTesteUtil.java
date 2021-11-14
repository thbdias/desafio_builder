package util;

import java.time.LocalDate;
import java.util.Optional;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequest;
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
	
	public static Optional<ClienteEntity> getOptionalClienteEntityMock() {
		return Optional.of(
					getClienteEntityFullMock());
	}
	
	public static ClienteDtoRequest getClienteDtoRequestMock() {
		ClienteDtoRequest clienteDtoRequest = new ClienteDtoRequest();
		clienteDtoRequest.setId(7);
		clienteDtoRequest.setNome("abc");
		clienteDtoRequest.setDataNascimento("2005-02-05");
		clienteDtoRequest.setNumeroRegistro(3);
		return clienteDtoRequest;
	}

}
