package br.com.desafio.builder.cliente.dto;

import io.swagger.annotations.ApiModelProperty;
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
	
	@ApiModelProperty(value = "Id do cliente")
	private Integer id;
}
