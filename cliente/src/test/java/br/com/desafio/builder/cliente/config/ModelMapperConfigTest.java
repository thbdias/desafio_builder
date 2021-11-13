package br.com.desafio.builder.cliente.config;

import static org.assertj.core.api.Assertions.assertThat;
import static util.ClienteTesteUtil.getClienteDtoRequestInsertComDataNullMock;
import static util.ClienteTesteUtil.getClienteDtoRequestInsertMock;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.desafio.builder.cliente.entity.ClienteEntity;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ModelMapperConfig.class)
class ModelMapperConfigTest {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Test
	void testClienteEntityNotNull() throws IOException {
		ClienteEntity entity = modelMapper.map(getClienteDtoRequestInsertMock(), ClienteEntity.class);		
		assertThat(entity).isNotNull();
	}

	
	@Test
	void testTypes() throws IOException {
		LocalDate localDateEsperado = LocalDate.parse("2005-02-05", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		ClienteEntity entity = modelMapper.map(getClienteDtoRequestInsertMock(), ClienteEntity.class);		
		
		assertThat(entity.getNome()).isNotNull();
		assertThat(entity.getNumeroRegistro()).isEqualTo(3);
		assertThat(entity.getDataNascimento()).isEqualTo(localDateEsperado);
	}
	
	
	@Test
	void testException() throws IOException {
		ClienteEntity entity = modelMapper.map(getClienteDtoRequestInsertComDataNullMock(), ClienteEntity.class);
		
		assertThat(entity.getDataNascimento()).isNull();
	}
}

