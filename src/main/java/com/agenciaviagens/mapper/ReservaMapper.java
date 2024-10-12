package com.agenciaviagens.mapper;

import com.agenciaviagens.dto.ReservaDTO;
import com.agenciaviagens.model.Reserva;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {

    private final ModelMapper modelMapper;

    public ReservaMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public ReservaDTO toDTO(Reserva reserva){
        ReservaDTO dto = modelMapper.map(reserva, ReservaDTO.class);
        dto.setClienteId(reserva.getCliente().getId());
        dto.setPacoteId(reserva.getPacote().getId());
        return dto;
    }

    public Reserva toEntity(ReservaDTO reservaDTO){
        Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
        // As relações com Cliente e Pacote devem ser setadas no serviço
        return reserva;
    }
}
