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
public class PageRequestDTO {
    private Integer page;
    private Integer size;
    private String orderBy;
    private String sort;
}
