package com.agenciaviagens.mapper;

import com.agenciaviagens.dto.ClienteDTO;
import com.agenciaviagens.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    private final ModelMapper modelMapper;

    public ClienteMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public ClienteDTO toDTO(Cliente cliente){
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public Cliente toEntity(ClienteDTO clienteDTO){
        return modelMapper.map(clienteDTO, Cliente.class);
    }
}
