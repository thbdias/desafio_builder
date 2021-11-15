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
public class PageRequestDTO {
	
	@ApiModelProperty(value = "Número da página que se deseja")
    private Integer page;
	
	@ApiModelProperty(value = "Tamanho da página que se deseja")
    private Integer size;
	
	@ApiModelProperty(value = "Campo que se deseja ordernar")
    private String orderBy;
	
	@ApiModelProperty(value = "Tipo de ordenação - crescente ou decrescente")
    private String sort;
}
