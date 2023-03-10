package com.example.dsclient.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.dsclient.dto.ClientDTO;
import com.example.dsclient.entities.Client;
import com.example.dsclient.repositories.ClientRepository;
import com.example.dsclient.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repository;

    private ModelMapper mapper = new ModelMapper();

    /**
     * Metodo que busca todos os clientes de maneira Paginada
     * @param pageRequest que chega como argumento
     * @return Page de ClientDto
     */
    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest){
        Page<Client> clients = repository.findAll(pageRequest);
        return clients.map(client -> mapper.map(client, ClientDTO.class));
    }

    /**
     * Metodo que busca o Cliente por Id
     * @param id do cliente
     * @return clienteDto com o Id passado
     */
    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Optional<Client> client = repository.findById(id);
        if(client.isEmpty()){
            throw new ResourceNotFoundException("Recurso nao encontrado");
        }
        ClientDTO dto = mapper.map(client.get(), ClientDTO.class);
        return dto;
    }

    /**
     * Metodo para inserir um Client
     * @param dto Client
     * @return ClientDto adicionado
     */
    @Transactional
    public ClientDTO insert(ClientDTO dto){
        dto.setId(null);
        Client client = mapper.map(dto, Client.class);
        client = repository.save(client);
        dto.setId(client.getId());
        return dto;
    }

    /**
     * Metodo para atualizar o Client
     * @param id do Client
     * @param dto do Client
     * @return Client ataulizado
     */
    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        dto.setId(id);
        Client client = mapper.map(dto, Client.class);
        Optional<Client> findClient =repository.findById(client.getId());
        if (findClient.isEmpty()){
            throw new ResourceNotFoundException("Recurso nao encontrado");
        }
        client = repository.save(client);
        return dto;
    }

    /**
     * 
     * Metodo para deletar o Client
     * @param id do CLient
     */
      @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        try{
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Recurso nao encontrado");
        }
        
    }

}
