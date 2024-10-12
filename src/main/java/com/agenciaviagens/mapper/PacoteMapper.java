package com.agenciaviagens.mapper;

import com.agenciaviagens.dto.PacoteDTO;
import com.agenciaviagens.model.Pacote;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PacoteMapper {

    private final ModelMapper modelMapper;

    public PacoteMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public PacoteDTO toDTO(Pacote pacote){
        return modelMapper.map(pacote, PacoteDTO.class);
    }

    public Pacote toEntity(PacoteDTO pacoteDTO){
        return modelMapper.map(pacoteDTO, Pacote.class);
    }
}
