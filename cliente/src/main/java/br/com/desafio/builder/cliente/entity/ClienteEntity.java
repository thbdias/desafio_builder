package br.com.desafio.builder.cliente.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    private Integer id;
	
	@NotNull(message = "required")
	private String nome;	
	
	@NotNull(message = "required")
	private LocalDate dataNascimento;
	
	private Integer numeroRegistro;

}
