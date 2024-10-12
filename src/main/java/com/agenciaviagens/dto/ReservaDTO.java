package com.agenciaviagens.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservaDTO {
    private Long id;

    @NotNull(message = "Data da reserva é obrigatória")
    private LocalDate dataReserva;

    @Future(message = "Data da viagem deve ser futura")
    private LocalDate dataViagem;

    @NotNull(message = "ID do cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "ID do pacote é obrigatório")
    private Long pacoteId;
}