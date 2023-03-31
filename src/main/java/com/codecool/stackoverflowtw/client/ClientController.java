package com.codecool.stackoverflowtw.client;

import com.codecool.stackoverflowtw.client.dto.ClientDTO;
import com.codecool.stackoverflowtw.client.dto.NewClientDTO;
import com.codecool.stackoverflowtw.queston.dto.NewQuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("/all")
    public List<Client> getAllClient() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable int id) {
        return clientService.getClientById(id);
    }

    @PostMapping("/")
    public int addNewClient(@RequestBody NewClientDTO client) {
        return clientService.addNewClient(client);
    }
}
