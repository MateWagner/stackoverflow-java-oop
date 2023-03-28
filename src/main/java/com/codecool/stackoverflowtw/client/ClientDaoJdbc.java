package com.codecool.stackoverflowtw.client;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientDaoJdbc implements ClientDAO {
    private final JdbcTemplate jdbcTemplate;

    public ClientDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Client> getAllClients() {
        String sql = """
                SELECT *
                FROM client;
                """;
        return jdbcTemplate.query(sql, new ClientRowMapper());
    }

    @Override
    public Boolean deleteClientByID(int clientID) {
        String sql = """
                DELETE FROM client
                WHERE (id = ?);
                """;
        jdbcTemplate.update(sql, clientID);
        return true;
    }
    @Override
    public Optional<Client> getClientByID(int clientID){
        String sql = """
                SELECT *
                FROM client
                WHERE id = ?;
                """;
        return jdbcTemplate.query(sql, new ClientRowMapper(), clientID).stream().findFirst();
    }
}
