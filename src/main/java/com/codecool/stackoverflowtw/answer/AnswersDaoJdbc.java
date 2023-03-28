package com.codecool.stackoverflowtw.answer;

import com.codecool.stackoverflowtw.answer.dto.NewAnswerDTO;
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
        return jdbcTemplate.query(sql, new AnswerRowMapper());
    }

    @Override
    public Integer addNewAnswer(NewAnswerDTO newAnswerDTO) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = """
                INSERT INTO answer(description,date,question_id,answer_to_id,client_id) VALUES (?,?,?,?,?)
                """;
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, newAnswerDTO.desc());
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(3, newAnswerDTO.questionId());
            statement.setObject(4, newAnswerDTO.answerToId().orElse(null));
            statement.setInt(5, newAnswerDTO.clientId());
            return statement;
        }, generatedKeyHolder);

        Map<String, Object> keys = generatedKeyHolder.getKeys();
        if (keys == null) return -1;
        return (Integer) keys.get("id");
    }
}
