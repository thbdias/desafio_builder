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
public class ClienteDtoResponse {	
	
	@ApiModelProperty(value = "Nome do cliente")
	private String nome;	
	
	@ApiModelProperty(value = "Data de Nascimento do cliente")
	private String dataNascimento; 
	
	@ApiModelProperty(value = "Numero de registro do cliente")
	private Integer numeroRegistro;
	
	@ApiModelProperty(value = "Idade do cliente")
	private Integer idade;
	
	@ApiModelProperty(value = "Mensagem de Observação")
	private String msg;
}
