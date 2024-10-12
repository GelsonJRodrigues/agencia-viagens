package com.agenciaviagens.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PagamentoDTO {
    private Long id;

    @NotNull(message = "Data do pagamento é obrigatória")
    private LocalDate dataPagamento;

    @Min(value = 0, message = "Valor deve ser positivo")
    private BigDecimal valor;

    @NotBlank(message = "Forma de pagamento é obrigatória")
    private String formaPagamento;

    @NotNull(message = "ID da reserva é obrigatório")
    private Long reservaId;
}