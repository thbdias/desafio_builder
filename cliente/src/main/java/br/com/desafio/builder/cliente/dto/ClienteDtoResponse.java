package br.com.desafio.builder.cliente.dto;

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
	
	private String nome;	
	private String dataNascimento; 
	private Integer numeroRegistro;
	private Integer idade;
	private String msg;
}
