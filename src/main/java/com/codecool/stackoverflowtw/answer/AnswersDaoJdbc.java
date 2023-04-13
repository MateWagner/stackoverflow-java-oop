package com.codecool.stackoverflowtw.answer;

import com.codecool.stackoverflowtw.answer.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.exception.NotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AnswersDaoJdbc implements AnswersDAO {
    private final JdbcTemplate jdbcTemplate;

    public AnswersDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Answer> getAllAnswer() {
        String sql = """ 
                SELECT id,description,date,question_id,answered_answer_id,client_id FROM answer;
                """;
        return jdbcTemplate.query(sql, new AnswerRowMapper());
    }

    @Override
    public List<Answer> getAnswersOfQuestion(Integer questionId) {
        String sql = """
                SELECT id,description,date,question_id,answered_answer_id,client_id FROM answer WHERE question_id  = ?;
                """;

        return jdbcTemplate.query(sql, new AnswerRowMapper(), questionId);
    }

    @Override
    public Integer postNewAnswer(NewAnswerDTO newAnswerDTO) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> prepareAddNewAnswerPreparedStatement(connection, newAnswerDTO), generatedKeyHolder);

        Map<String, Object> keys = generatedKeyHolder.getKeys();
        if (keys == null) return -1;
        return (Integer) keys.get("id");
    }

    @Override
    public void deleteAnswer(Integer answerId) throws NotFoundException {
        String sql = """
                DELETE FROM answer WHERE id = ?
                """;
        jdbcTemplate.update(sql, answerId);
    }

    @Override
    public Optional<Answer> getAnswer(Integer answerId) throws NotFoundException {
        String sql = """
                SELECT id,description,date,question_id,answered_answer_id,client_id FROM answer WHERE id = ?
                """;
        return jdbcTemplate.query(sql, new AnswerRowMapper(), answerId).stream()
                .findFirst();
    }

    @Override
    public int updateAnswer(int id, NewAnswerDTO answerDTO) {
        String sql = """
                UPDATE answer
                SET description = ?
                where id = ?
                """;
        return jdbcTemplate.update(sql, answerDTO.desc(), id);
    }

    private PreparedStatement prepareAddNewAnswerPreparedStatement(Connection connection, NewAnswerDTO newAnswerDTO) throws SQLException {
        String sql = """
                INSERT INTO answer(description,date,question_id,answered_answer_id,client_id) VALUES (?,?,?,?,?)
                """;
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, newAnswerDTO.desc());
        statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
        statement.setInt(3, newAnswerDTO.questionId());
        statement.setObject(4, newAnswerDTO.answeredAnswerId().orElse(null));
        statement.setInt(5, newAnswerDTO.clientId());

        return statement;
    }
}
