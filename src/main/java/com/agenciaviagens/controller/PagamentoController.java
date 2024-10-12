package com.agenciaviagens.controller;

import com.agenciaviagens.dto.PagamentoDTO;
import com.agenciaviagens.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pagamentos", description = "Operações relacionadas a Pagamentos")
@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService){
        this.pagamentoService = pagamentoService;
    }

    @Operation(summary = "Criar um novo pagamento")
    @PostMapping
    public ResponseEntity<PagamentoDTO> createPagamento(@Valid @RequestBody PagamentoDTO pagamentoDTO){
        PagamentoDTO createdPagamento = pagamentoService.createPagamento(pagamentoDTO);
        return new ResponseEntity<>(createdPagamento, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos os pagamentos")
    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> getAllPagamentos(){
        List<PagamentoDTO> pagamentos = pagamentoService.getAllPagamentos();
        return new ResponseEntity<>(pagamentos, HttpStatus.OK);
    }

    @Operation(summary = "Obter pagamento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> getPagamentoById(@PathVariable Long id){
        PagamentoDTO pagamento = pagamentoService.getPagamentoById(id);
        return new ResponseEntity<>(pagamento, HttpStatus.OK);
    }

    @Operation(summary = "Atualizar pagamento existente")
    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> updatePagamento(@PathVariable Long id, @Valid @RequestBody PagamentoDTO pagamentoDTO){
        PagamentoDTO updatedPagamento = pagamentoService.updatePagamento(id, pagamentoDTO);
        return new ResponseEntity<>(updatedPagamento, HttpStatus.OK);
    }

    @Operation(summary = "Deletar pagamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePagamento(@PathVariable Long id){
        pagamentoService.deletePagamento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
