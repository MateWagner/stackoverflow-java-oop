package com.codecool.stackoverflowtw.client;

import com.codecool.stackoverflowtw.client.dto.NewClientDTO;
import com.codecool.stackoverflowtw.queston.dto.NewQuestionDTO;

import java.util.List;
import java.util.Optional;

public interface ClientDAO {
    List<Client> getAllClients();
    Optional<Client> getClientByID(int id);
    Integer addNewClient(NewClientDTO newClientDTO);
    //String deleteClientByID(int id);
}