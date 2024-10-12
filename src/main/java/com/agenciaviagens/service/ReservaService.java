package com.agenciaviagens.service;

import com.agenciaviagens.dto.ReservaDTO;
import com.agenciaviagens.exception.ResourceNotFoundException;
import com.agenciaviagens.mapper.ReservaMapper;
import com.agenciaviagens.model.Cliente;
import com.agenciaviagens.model.Pacote;
import com.agenciaviagens.model.Reserva;
import com.agenciaviagens.repository.ClienteRepository;
import com.agenciaviagens.repository.PacoteRepository;
import com.agenciaviagens.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;
    private final ClienteRepository clienteRepository;
    private final PacoteRepository pacoteRepository;

    public ReservaService(ReservaRepository reservaRepository, ReservaMapper reservaMapper,
                          ClienteRepository clienteRepository, PacoteRepository pacoteRepository){
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
        this.clienteRepository = clienteRepository;
        this.pacoteRepository = pacoteRepository;
    }

    public ReservaDTO createReserva(ReservaDTO reservaDTO){
        // Verificar se o cliente existe
        Cliente cliente = clienteRepository.findById(reservaDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id " + reservaDTO.getClienteId()));

        // Verificar se o pacote existe
        Pacote pacote = pacoteRepository.findById(reservaDTO.getPacoteId())
                .orElseThrow(() -> new ResourceNotFoundException("Pacote não encontrado com id " + reservaDTO.getPacoteId()));

        // Mapear DTO para entidade
        Reserva reserva = reservaMapper.toEntity(reservaDTO);
        reserva.setCliente(cliente);
        reserva.setPacote(pacote);

        Reserva savedReserva = reservaRepository.save(reserva);
        return reservaMapper.toDTO(savedReserva);
    }

    public List<ReservaDTO> getAllReservas(){
        return reservaRepository.findAll()
                .stream()
                .map(reservaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReservaDTO getReservaById(Long id){
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada com id " + id));
        return reservaMapper.toDTO(reserva);
    }

    public ReservaDTO updateReserva(Long id, ReservaDTO reservaDTO){
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada com id " + id));

        // Atualizar campos
        reserva.setDataReserva(reservaDTO.getDataReserva());
        reserva.setDataViagem(reservaDTO.getDataViagem());

        // Atualizar Cliente, se necessário
        if(!reserva.getCliente().getId().equals(reservaDTO.getClienteId())){
            Cliente cliente = clienteRepository.findById(reservaDTO.getClienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id " + reservaDTO.getClienteId()));
            reserva.setCliente(cliente);
        }

        // Atualizar Pacote, se necessário
        if(!reserva.getPacote().getId().equals(reservaDTO.getPacoteId())){
            Pacote pacote = pacoteRepository.findById(reservaDTO.getPacoteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Pacote não encontrado com id " + reservaDTO.getPacoteId()));
            reserva.setPacote(pacote);
        }

        Reserva updatedReserva = reservaRepository.save(reserva);
        return reservaMapper.toDTO(updatedReserva);
    }

    public void deleteReserva(Long id){
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva não encontrada com id " + id));
        reservaRepository.delete(reserva);
    }
}
