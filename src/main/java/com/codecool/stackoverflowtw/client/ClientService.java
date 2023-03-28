package com.codecool.stackoverflowtw.client;

import com.codecool.stackoverflowtw.client.dto.ClientDTO;
import com.codecool.stackoverflowtw.client.dto.NewClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        // TODO
        return clientDAO.getAllClients().get(id);
    }
    public boolean deleteClientById(int id) {
        // TODO
        return false;
    }
    public int addNewClient(NewClientDTO client) {
        // TODO
        int createdId = 0;
        return createdId;
    }
    public List<Client> getAll() {
        return null;
    }
}
