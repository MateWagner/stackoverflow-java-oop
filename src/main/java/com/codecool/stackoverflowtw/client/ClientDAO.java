package com.codecool.stackoverflowtw.client;

import com.codecool.stackoverflowtw.client.dto.LoginClientDTO;
import com.codecool.stackoverflowtw.client.dto.NewClientDTO;

import java.util.List;
import java.util.Optional;

public interface ClientDAO {
    List<Client> getAllClients();
    Optional<Client> getClientByID(int id);
    Integer addNewClient(NewClientDTO newClientDTO);
    Optional<LoginClientDTO> loginUser(LoginData loginData);
}
