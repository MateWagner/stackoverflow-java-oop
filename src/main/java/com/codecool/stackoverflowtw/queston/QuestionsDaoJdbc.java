package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.exception.NotFoundException;
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

    @Override
    public Integer addNewQuestion(NewQuestionDTO newQuestionDTO) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = """
                INSERT INTO question(title, description, client_id, date) VALUES (?,?,?,?)
                """;

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, newQuestionDTO.title());
            statement.setString(2, newQuestionDTO.description());
            statement.setInt(3, newQuestionDTO.clientId());
            statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            return statement;
        }, generatedKeyHolder);

        Map<String, Object> keys = generatedKeyHolder.getKeys();
        if (keys == null) throw new NotFoundException("oops something went wrong");
        return (Integer) keys.get("id");
    }

    @Override
    public int deleteQuestionById(int id) {
        String sql = """
                DELETE FROM question
                WHERE id = ?
                """;
        return  jdbcTemplate.update(sql,id);

    }

}
