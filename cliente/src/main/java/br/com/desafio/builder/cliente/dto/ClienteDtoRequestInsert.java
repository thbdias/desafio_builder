package br.com.desafio.builder.cliente.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClienteDtoRequestInsert {	
	
	@ApiModelProperty(value = "Nome do cliente")
	private String nome;
	
	@ApiModelProperty(value = "Data de Nascimento do cliente")
	private String dataNascimento; 
	
	@ApiModelProperty(value = "Numero de registro do cliente")
	private Integer numeroRegistro;
}
