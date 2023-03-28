package com.codecool.stackoverflowtw.client;

import java.util.List;
import java.util.Optional;

public interface ClientDAO {
    List<Client> getAllClients();
    Optional<Client> getClientByID(int id);
    Boolean deleteClientByID(int id);
}
