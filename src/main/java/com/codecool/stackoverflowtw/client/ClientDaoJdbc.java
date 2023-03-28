package com.codecool.stackoverflowtw.client;

import com.codecool.stackoverflowtw.client.dto.NewClientDTO;
import com.codecool.stackoverflowtw.queston.dto.NewQuestionDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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
    public Optional<Client> getClientByID(int clientID){
        String sql = """
                SELECT *
                FROM client
                WHERE id = ?;
                """;
        return jdbcTemplate.query(sql, new ClientRowMapper(), clientID).stream().findFirst();
    }
    @Override
    public Integer addNewClient(NewClientDTO newClientDTO) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = """
                INSERT INTO client(name, email, password, date) VALUES (?,?,?,?)
                """;

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, newClientDTO.name());
            statement.setString(2, newClientDTO.email());
            statement.setString(3, newClientDTO.password());
            statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            return statement;
        }, generatedKeyHolder);

        Map<String, Object> keys = generatedKeyHolder.getKeys();
        if (keys == null) return -1;
        return (Integer) keys.get("id");
    }
    /*@Override
    public String deleteClientByID(int clientID) {
        String sql = """
                DELETE FROM client
                WHERE (id = ?);
                CASCADE
                """;
        jdbcTemplate.update(sql, clientID);
        return "Delete the client which ID was: " + clientID;
    }*/
}
