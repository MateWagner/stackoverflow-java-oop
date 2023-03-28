package com.codecool.stackoverflowtw.client;

import com.codecool.stackoverflowtw.client.dto.ClientDTO;
import com.codecool.stackoverflowtw.client.dto.NewClientDTO;
import com.codecool.stackoverflowtw.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private ClientDAO clientDAO;
    @Autowired
    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
    public List<Client> getAllClients() {
        // TODO
        return clientDAO.getAllClients();
    }
    public Client getClientById(int id) {
        Optional<Client> clientOptional = clientDAO.getClientByID(id);
        if (clientOptional.isEmpty())
            throw new NotFoundException("There was no client with in id: " + id);
        return clientOptional.get();
    }
    public int addNewClient(NewClientDTO client) {
        return clientDAO.addNewClient(client);
    }
    /*public String deleteClientById(int id) {
        return clientDAO.deleteClientByID(id);
    }*/
}
