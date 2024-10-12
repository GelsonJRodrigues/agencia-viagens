package com.agenciaviagens.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Data do pagamento é obrigatória")
    private LocalDate dataPagamento;

    @Min(value = 0, message = "Valor deve ser positivo")
    private BigDecimal valor;

    @NotBlank(message = "Forma de pagamento é obrigatória")
    private String formaPagamento;

    @OneToOne
    @JoinColumn(name = "reserva_id", nullable = false)
    private Reserva reserva;
}
