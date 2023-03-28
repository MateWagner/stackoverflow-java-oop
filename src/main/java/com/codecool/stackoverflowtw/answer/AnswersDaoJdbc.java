package com.codecool.stackoverflowtw.answer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnswersDaoJdbc implements AnswersDAO {
    private final JdbcTemplate jdbcTemplate;

    public AnswersDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Answer> getAllAnswer() {
        String sql = """
                SELECT * FROM answer;
                """;
        return jdbcTemplate.query(sql,new AnswerRowMapper());
    }
}
