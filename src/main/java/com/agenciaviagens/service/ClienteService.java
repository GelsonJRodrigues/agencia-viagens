package com.agenciaviagens.service;

import com.agenciaviagens.dto.ClienteDTO;
import com.agenciaviagens.exception.ResourceNotFoundException;
import com.agenciaviagens.mapper.ClienteMapper;
import com.agenciaviagens.model.Cliente;
import com.agenciaviagens.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper){
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public ClienteDTO createCliente(ClienteDTO clienteDTO){
        if(clienteRepository.existsByEmail(clienteDTO.getEmail())){
            throw new IllegalArgumentException("Email já está em uso");
        }
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente savedCliente = clienteRepository.save(cliente);
        return clienteMapper.toDTO(savedCliente);
    }

    public List<ClienteDTO> getAllClientes(){
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO getClienteById(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id " + id));
        return clienteMapper.toDTO(cliente);
    }

    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id " + id));

        if(!cliente.getEmail().equals(clienteDTO.getEmail()) && clienteRepository.existsByEmail(clienteDTO.getEmail())){
            throw new IllegalArgumentException("Email já está em uso");
        }

        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEndereco(clienteDTO.getEndereco());

        Cliente updatedCliente = clienteRepository.save(cliente);
        return clienteMapper.toDTO(updatedCliente);
    }

    public void deleteCliente(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id " + id));
        clienteRepository.delete(cliente);
    }
}
