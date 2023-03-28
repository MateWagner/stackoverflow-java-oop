package com.codecool.stackoverflowtw.client;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    /*@Override
    public List<Client> getClientByID(int id){
        String sql = """
                SELECT *
                FROM client
                WHERE (id = 1);
                """;
        return jdbcTemplate.query(sql, new ClientRowMapper());
    }*/
}
