package br.com.desafio.builder.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClienteDtoRequest extends ClienteDtoRequestInsert {	
		
	private Integer id;
}
