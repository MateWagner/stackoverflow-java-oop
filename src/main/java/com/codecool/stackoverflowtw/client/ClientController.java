package com.codecool.stackoverflowtw.client;

import com.codecool.stackoverflowtw.client.dto.ClientDTO;
import com.codecool.stackoverflowtw.client.dto.NewClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public List<Client> getAllClient() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable int id) {
        return null;
    }

    @PostMapping("/")
    public int addNewClient(@RequestBody NewClientDTO client) {
        return 0;
    }

    @DeleteMapping("/{id}")
    public boolean deleteClientById(@PathVariable int id) {
        return false;
    }
}
