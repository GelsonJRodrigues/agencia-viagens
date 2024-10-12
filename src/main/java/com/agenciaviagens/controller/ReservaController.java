package com.agenciaviagens.controller;

import com.agenciaviagens.dto.ReservaDTO;
import com.agenciaviagens.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Reservas", description = "Operações relacionadas a Reservas")
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService){
        this.reservaService = reservaService;
    }

    @Operation(summary = "Criar uma nova reserva")
    @PostMapping
    public ResponseEntity<ReservaDTO> createReserva(@Valid @RequestBody ReservaDTO reservaDTO){
        ReservaDTO createdReserva = reservaService.createReserva(reservaDTO);
        return new ResponseEntity<>(createdReserva, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todas as reservas")
    @GetMapping
    public ResponseEntity<List<ReservaDTO>> getAllReservas(){
        List<ReservaDTO> reservas = reservaService.getAllReservas();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @Operation(summary = "Obter reserva por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable Long id){
        ReservaDTO reserva = reservaService.getReservaById(id);
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }

    @Operation(summary = "Atualizar reserva existente")
    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> updateReserva(@PathVariable Long id, @Valid @RequestBody ReservaDTO reservaDTO){
        ReservaDTO updatedReserva = reservaService.updateReserva(id, reservaDTO);
        return new ResponseEntity<>(updatedReserva, HttpStatus.OK);
    }

    @Operation(summary = "Deletar reserva")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id){
        reservaService.deleteReserva(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
