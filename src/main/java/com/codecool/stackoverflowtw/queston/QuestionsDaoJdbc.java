package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.queston.QuestionsDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionsDaoJdbc implements QuestionsDAO {
    private final JdbcTemplate jdbcTemplate;

    public QuestionsDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Question> getAllQuestion() {
        String sql = """
                SELECT id, client_id, title, description, date
                FROM question;
                """;
        return jdbcTemplate.query(sql, new QuestionRowMapper());
    }

}
