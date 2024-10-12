package com.agenciaviagens.service;

import com.agenciaviagens.dto.PagamentoDTO;
import com.agenciaviagens.exception.ResourceNotFoundException;
import com.agenciaviagens.mapper.PagamentoMapper;
import com.agenciaviagens.model.Pagamento;
import com.agenciaviagens.model.Reserva;
import com.agenciaviagens.repository.PagamentoRepository;
import com.agenciaviagens.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;
    private final ReservaRepository reservaRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, PagamentoMapper pagamentoMapper,
                            ReservaRepository reservaRepository){
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoMapper = pagamentoMapper;
        this.reservaRepository = reservaRepository;
    }

    public PagamentoDTO createPagamento(PagamentoDTO pagamentoDTO){
        // Verificar se a reserva existe
        Reserva reserva = reservaRepository.findById(pagamentoDTO.getReservaId())
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada com id " + pagamentoDTO.getReservaId()));

        // Verificar se a reserva já possui um pagamento
        if(reserva.getPagamento() != null){
            throw new IllegalArgumentException("Reserva já possui um pagamento associado");
        }

        // Mapear DTO para entidade
        Pagamento pagamento = pagamentoMapper.toEntity(pagamentoDTO);
        pagamento.setReserva(reserva);

        Pagamento savedPagamento = pagamentoRepository.save(pagamento);
        return pagamentoMapper.toDTO(savedPagamento);
    }

    public List<PagamentoDTO> getAllPagamentos(){
        return pagamentoRepository.findAll()
                .stream()
                .map(pagamentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PagamentoDTO getPagamentoById(Long id){
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado com id " + id));
        return pagamentoMapper.toDTO(pagamento);
    }

    public PagamentoDTO updatePagamento(Long id, PagamentoDTO pagamentoDTO){
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado com id " + id));

        // Atualizar campos
        pagamento.setDataPagamento(pagamentoDTO.getDataPagamento());
        pagamento.setValor(pagamentoDTO.getValor());
        pagamento.setFormaPagamento(pagamentoDTO.getFormaPagamento());

        // Atualizar Reserva, se necessário
        if(!pagamento.getReserva().getId().equals(pagamentoDTO.getReservaId())){
            Reserva reserva = reservaRepository.findById(pagamentoDTO.getReservaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada com id " + pagamentoDTO.getReservaId()));
            if(reserva.getPagamento() != null){
                throw new IllegalArgumentException("Reserva já possui um pagamento associado");
            }
            pagamento.setReserva(reserva);
        }

        Pagamento updatedPagamento = pagamentoRepository.save(pagamento);
        return pagamentoMapper.toDTO(updatedPagamento);
    }

    public void deletePagamento(Long id){
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pagamento não encontrado com id " + id));
        pagamentoRepository.delete(pagamento);
    }
}
