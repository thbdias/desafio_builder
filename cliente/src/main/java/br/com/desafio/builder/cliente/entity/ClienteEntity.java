package br.com.desafio.builder.cliente.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cliente")
public class ClienteEntity {
	
	@ApiModelProperty(value = "Id do cliente")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    private Integer id;
	
	@ApiModelProperty(value = "Nome do cliente")
	@NotNull(message = "required")
	private String nome;	
	
	@ApiModelProperty(value = "Data de Nascimento do cliente")
	@NotNull(message = "required")
	private LocalDate dataNascimento;
	
	@ApiModelProperty(value = "Numero de registro do cliente")
	private Integer numeroRegistro;

}
