package com.clientapi.clientapi.controllers;

import com.clientapi.clientapi.entities.Client;
import com.clientapi.clientapi.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientsController {

    @Autowired
    private ClientsService service;

    @PostMapping()
    public void saveClient(@RequestBody Client client) {
        try {
            service.saveClient(client);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("CREATE ERROR");
        }
    }

    @GetMapping()
    public List<Client> readClient() {
        try {
            return service.readClient();
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("READ ALL CLIENTS ERROR");
        }
    }

    @GetMapping("/{id}")
    public Optional<Client> readOneClient(@PathVariable Long id) {
        try {
            return service.readOneClient(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("READ ONE CLIENT ERROR");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody Client updatedClient) {
        try {
            Optional<Client> existingClient = service.readOneClient(id);
            if (existingClient.isPresent()) {
                updatedClient.setId(id); // asegura que el cliente a actualizar tenga el mismo ID
                service.saveClient(updatedClient); // guarda el cliente actualizado
                return ResponseEntity.ok(updatedClient);
            } else {
                return ResponseEntity.notFound().build(); // retorna 404 si no se encuentra el cliente
            }
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("UPDATE CLIENT ERROR");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable("id") Long id) {
        try {
            service.deleteClient(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("DELETE CLIENT ERROR");
        }
    }
}
