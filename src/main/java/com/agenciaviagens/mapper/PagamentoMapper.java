package com.agenciaviagens.mapper;

import com.agenciaviagens.dto.PagamentoDTO;
import com.agenciaviagens.model.Pagamento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    private final ModelMapper modelMapper;

    public PagamentoMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public PagamentoDTO toDTO(Pagamento pagamento){
        PagamentoDTO dto = modelMapper.map(pagamento, PagamentoDTO.class);
        dto.setReservaId(pagamento.getReserva().getId());
        return dto;
    }

    public Pagamento toEntity(PagamentoDTO pagamentoDTO){
        Pagamento pagamento = modelMapper.map(pagamentoDTO, Pagamento.class);
        // A relação com Reserva deve ser setada no serviço
        return pagamento;
    }
}
