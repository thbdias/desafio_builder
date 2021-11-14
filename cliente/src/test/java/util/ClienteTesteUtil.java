package util;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import br.com.desafio.builder.cliente.dto.ClienteDtoRequest;
import br.com.desafio.builder.cliente.dto.ClienteDtoRequestInsert;
import br.com.desafio.builder.cliente.dto.ClienteDtoResponse;
import br.com.desafio.builder.cliente.dto.PageRequestDTO;
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
	
	public static ClienteDtoRequest getClienteDtoRequestComIdMock() {
		ClienteDtoRequest clienteDtoRequest = new ClienteDtoRequest();
		clienteDtoRequest.setId(7);
		return clienteDtoRequest;
	}
	
	public static ClienteDtoRequest getClienteDtoRequestComDataMock() {
		ClienteDtoRequest clienteDtoRequest = new ClienteDtoRequest();
		clienteDtoRequest.setDataNascimento("2010-01-10");
		return clienteDtoRequest;
	}
	
	public static ClienteDtoRequest getClienteDtoRequestComNomeMock() {
		ClienteDtoRequest clienteDtoRequest = new ClienteDtoRequest();
		clienteDtoRequest.setNome("jose");
		return clienteDtoRequest;
	}
	
	public static ClienteDtoRequest getClienteDtoRequestComNumeroRegistroMock() {
		ClienteDtoRequest clienteDtoRequest = new ClienteDtoRequest();
		clienteDtoRequest.setNumeroRegistro(5);
		return clienteDtoRequest;
	}
	
	public static ClienteDtoRequest getClienteDtoRequestMock() {
		ClienteDtoRequest clienteDtoRequest = new ClienteDtoRequest();
		clienteDtoRequest.setId(7);
		clienteDtoRequest.setNome("abc");
		clienteDtoRequest.setDataNascimento("2005-02-05");
		clienteDtoRequest.setNumeroRegistro(3);
		return clienteDtoRequest;
	}
	
	public static Specification<ClienteEntity> getSpecificationFindAllMock() {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNotNull(root.get("id"));
	}

	public static PageRequest getPageRequestMock() {
		return PageRequest.of(0, 10);
	}
	
	public static PageRequestDTO getPageRequestDTOMock() {
		return PageRequestDTO
				.builder()
					.page(0)
					.size(3)
					.sort("asc")
					.orderBy("nome")
				.build();
	}
	
	public static PageRequestDTO getPageRequestDTOComNomeDescMock() {
		return PageRequestDTO
				.builder()
					.page(0)
					.size(3)
					.sort("desc")
					.orderBy("nome")
				.build();
	}
	
	public static PageRequestDTO getPageRequestDTOErroSortMock() {
		return PageRequestDTO.builder().sort("ascXX").orderBy(null).build();
	}
	
	public static PageRequestDTO getPageRequestDTOErroOrderByMock() {
		return PageRequestDTO.builder().sort(null).orderBy("campoInexistente").build();
	}
	
	public static PageRequestDTO getPageRequestDTOErroSortIncompativelMock() {
		return PageRequestDTO.builder().sort("desc").orderBy(null).build();
	}
	
	public static PageRequestDTO getPageRequestDTOErroSortIncompativel2Mock() {
		return PageRequestDTO.builder().sort(null).orderBy("nome").build();
	}
	
	public static ClienteDtoRequest getClienteDtoRequestNullMock() {
		return null;
	}
	
	public static ClienteDtoRequest getClienteDtoRequestComIdNullMock() {
		ClienteDtoRequest clienteDtoRequest = new ClienteDtoRequest();
		clienteDtoRequest.setId(null);
		return clienteDtoRequest;
	}
	
	public static ClienteDtoRequest getClienteDtoRequestComIdNegativoMock() {
		ClienteDtoRequest clienteDtoRequest = new ClienteDtoRequest();
		clienteDtoRequest.setId(-1);
		return clienteDtoRequest;
	}
	
	public static ClienteDtoRequest getClienteDtoRequestComDataInvalida() {
		ClienteDtoRequest clienteDtoRequest = new ClienteDtoRequest();
		clienteDtoRequest.setDataNascimento("2010/05-17");
		return clienteDtoRequest;
	}
	
	public static ClienteDtoRequest getClienteDtoRequestComDataInvalida2() {
		ClienteDtoRequest clienteDtoRequest = new ClienteDtoRequest();
		clienteDtoRequest.setDataNascimento("2010-13-17");
		return clienteDtoRequest;
	}
	
	public static ClienteDtoRequest getClienteDtoRequestComDataInvalida3() {
		ClienteDtoRequest clienteDtoRequest = new ClienteDtoRequest();
		clienteDtoRequest.setDataNascimento("2010-02-174");
		return clienteDtoRequest;
	}
	
	public static ClienteDtoRequest getClienteDtoRequestComDataInvalida4() {
		ClienteDtoRequest clienteDtoRequest = new ClienteDtoRequest();
		clienteDtoRequest.setDataNascimento("eeee-02-05");
		return clienteDtoRequest;
	}
	
	public static ClienteDtoRequest getClienteDtoRequestComDataValida() {
		ClienteDtoRequest clienteDtoRequest = new ClienteDtoRequest();
		clienteDtoRequest.setDataNascimento("2010-02-05");
		return clienteDtoRequest;
	}
	
	public static ClienteDtoRequestInsert getClienteDtoRequestInsertNull() {
		return null;
	}
	
	public static ClienteDtoRequestInsert getClienteDtoRequestInsertComNomeNull() {
		return ClienteDtoRequestInsert
				.builder()
					.nome(null)
				.build();
	}
	
	public static ClienteDtoRequestInsert getClienteDtoRequestInsertComNomeEmpty() {
		return ClienteDtoRequestInsert
				.builder()
					.nome("")
				.build();
	}
	
	public static ClienteDtoRequestInsert getClienteDtoRequestInsertComDataNull() {
		return ClienteDtoRequestInsert
				.builder()
					.nome("abc")
					.dataNascimento(null)
				.build();
	}
	
	public static ClienteDtoRequestInsert getClienteDtoRequestInsertComDataEmpty() {
		return ClienteDtoRequestInsert
				.builder()
					.nome("abc")
					.dataNascimento("")
				.build();
	}
	
	public static ClienteDtoRequestInsert getClienteDtoRequestInsertComDataInvalida() {
		return ClienteDtoRequestInsert
				.builder()
					.nome("abc")
					.dataNascimento("2010/04-05")
				.build();
	}
	
	public static ClienteDtoRequestInsert getClienteDtoRequestInsertComData() {
		return ClienteDtoRequestInsert
				.builder()
					.nome("abc")
					.dataNascimento("2010-04-05")
				.build();
	}
}
