package com.agenciaviagens.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PacoteDTO {
    private Long id;

    @NotBlank(message = "Nome do pacote é obrigatório")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @Min(value = 0, message = "Preço deve ser positivo")
    private BigDecimal preco;
}