package com.agenciaviagens.controller;

import com.agenciaviagens.dto.PacoteDTO;
import com.agenciaviagens.service.PacoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pacotes", description = "Operações relacionadas a Pacotes")
@RestController
@RequestMapping("/api/pacotes")
public class PacoteController {

    private final PacoteService pacoteService;

    public PacoteController(PacoteService pacoteService){
        this.pacoteService = pacoteService;
    }

    @Operation(summary = "Criar um novo pacote")
    @PostMapping
    public ResponseEntity<PacoteDTO> createPacote(@Valid @RequestBody PacoteDTO pacoteDTO){
        PacoteDTO createdPacote = pacoteService.createPacote(pacoteDTO);
        return new ResponseEntity<>(createdPacote, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos os pacotes")
    @GetMapping
    public ResponseEntity<List<PacoteDTO>> getAllPacotes(){
        List<PacoteDTO> pacotes = pacoteService.getAllPacotes();
        return new ResponseEntity<>(pacotes, HttpStatus.OK);
    }

    @Operation(summary = "Obter pacote por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PacoteDTO> getPacoteById(@PathVariable Long id){
        PacoteDTO pacote = pacoteService.getPacoteById(id);
        return new ResponseEntity<>(pacote, HttpStatus.OK);
    }

    @Operation(summary = "Atualizar pacote existente")
    @PutMapping("/{id}")
    public ResponseEntity<PacoteDTO> updatePacote(@PathVariable Long id, @Valid @RequestBody PacoteDTO pacoteDTO){
        PacoteDTO updatedPacote = pacoteService.updatePacote(id, pacoteDTO);
        return new ResponseEntity<>(updatedPacote, HttpStatus.OK);
    }

    @Operation(summary = "Deletar pacote")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacote(@PathVariable Long id){
        pacoteService.deletePacote(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
