package com.clientapi.clientapi.services;

import com.clientapi.clientapi.entities.Client;
import com.clientapi.clientapi.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsService {
    @Autowired
    private ClientsRepository repository;

    public void saveClient(Client client) {
        repository.save(client);
    }
    public List<Client> readClient(){
        return repository.findAll();
    }
    public Optional<Client> readOneClient(Long id){
        return repository.findById(id);
    }
    //opcional porque no siempre puede encontrar el cliente
    //devuelve null si no lo encuentra
    public void deleteClient(Long id){
        repository.deleteById(id);
    }
}
