package com.agenciaviagens.service;

import com.agenciaviagens.dto.PacoteDTO;
import com.agenciaviagens.exception.ResourceNotFoundException;
import com.agenciaviagens.mapper.PacoteMapper;
import com.agenciaviagens.model.Pacote;
import com.agenciaviagens.repository.PacoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PacoteService {

    private final PacoteRepository pacoteRepository;
    private final PacoteMapper pacoteMapper;

    public PacoteService(PacoteRepository pacoteRepository, PacoteMapper pacoteMapper){
        this.pacoteRepository = pacoteRepository;
        this.pacoteMapper = pacoteMapper;
    }

    public PacoteDTO createPacote(PacoteDTO pacoteDTO){
        Pacote pacote = pacoteMapper.toEntity(pacoteDTO);
        Pacote savedPacote = pacoteRepository.save(pacote);
        return pacoteMapper.toDTO(savedPacote);
    }

    public List<PacoteDTO> getAllPacotes(){
        return pacoteRepository.findAll()
                .stream()
                .map(pacoteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PacoteDTO getPacoteById(Long id){
        Pacote pacote = pacoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pacote não encontrado com id " + id));
        return pacoteMapper.toDTO(pacote);
    }

    public PacoteDTO updatePacote(Long id, PacoteDTO pacoteDTO){
        Pacote pacote = pacoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pacote não encontrado com id " + id));

        pacote.setNome(pacoteDTO.getNome());
        pacote.setDescricao(pacoteDTO.getDescricao());
        pacote.setPreco(pacoteDTO.getPreco());

        Pacote updatedPacote = pacoteRepository.save(pacote);
        return pacoteMapper.toDTO(updatedPacote);
    }

    public void deletePacote(Long id){
        Pacote pacote = pacoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pacote não encontrado com id " + id));
        pacoteRepository.delete(pacote);
    }
}
