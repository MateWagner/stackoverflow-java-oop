package com.codecool.stackoverflowtw.client;

import com.codecool.stackoverflowtw.client.dto.LoginClientDTO;
import com.codecool.stackoverflowtw.client.dto.NewClientDTO;
import com.codecool.stackoverflowtw.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientDAO clientDAO;
    @Autowired
    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
    public List<Client> getAllClients() {
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

    public LoginClientDTO getClient(LoginData loginData) {
        Optional<LoginClientDTO> optionalUser = clientDAO.loginUser(loginData);
        if (optionalUser.isEmpty()) throw new NotFoundException("There was no client with email or Pw");
        return optionalUser.get();
    }
}
